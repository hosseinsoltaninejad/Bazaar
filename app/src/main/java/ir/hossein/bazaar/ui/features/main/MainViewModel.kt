package ir.hossein.bazaar.ui.features.main

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ir.hossein.bazaar.model.data.Ads
import ir.hossein.bazaar.model.data.CheckOut
import ir.hossein.bazaar.model.data.Product
import ir.hossein.bazaar.model.repository.cart.CartRepository
import ir.hossein.bazaar.model.repository.product.ProductRepository
import ir.hossein.bazaar.util.coroutineExceptionHandler
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MainViewModel(
    private val productRepository: ProductRepository,
    private val cartRepository: CartRepository,
    private val isInternetConnected: Boolean
) : ViewModel() {

    val dataProducts = mutableStateOf<List<Product>>(listOf())
    val dataAds = mutableStateOf<List<Ads>>(listOf())
    val showProgressBar = mutableStateOf(false)
    val badgeNumber = mutableStateOf(0)

    val showPaymentResultDialog = mutableStateOf(false)
    val checkoutData = mutableStateOf(CheckOut(null, null))

    init {
        refreshAllFromNet()
    }

    fun getCheckoutData() {

        viewModelScope.launch(coroutineExceptionHandler) {

            val result = cartRepository.checkOut(cartRepository.getOrderId())
            if (result.success!!) {
                checkoutData.value = result
                showPaymentResultDialog.value = true
            }

        }

    }

    fun getPaymentStatus(): Int {
        return cartRepository.getPurchaseStatus()
    }

    fun setPaymentStatus(status: Int) {
        cartRepository.setPurchaseStatus(status)
    }

    private fun refreshAllFromNet() {

        viewModelScope.launch (coroutineExceptionHandler){

            if (isInternetConnected)
                showProgressBar.value = true

            val newDataProducts = async { productRepository.getAllProducts(isInternetConnected) }
            val newDataAds = async { productRepository.getAllAds(isInternetConnected) }

            updateData(newDataProducts.await(), newDataAds.await())

            showProgressBar.value = false
        }
    }

    private fun updateData(products: List<Product>, ads: List<Ads>) {

        dataProducts.value = products
        dataAds.value = ads
    }

    fun loadBadgeNumber() {

        viewModelScope.launch(coroutineExceptionHandler) {
            badgeNumber.value = cartRepository.getCartSize()
        }

    }

}