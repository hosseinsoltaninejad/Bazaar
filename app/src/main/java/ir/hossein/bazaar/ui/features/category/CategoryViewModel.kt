package ir.hossein.bazaar.ui.features.category

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ir.hossein.bazaar.model.data.Product
import ir.hossein.bazaar.model.repository.product.ProductRepository
import kotlinx.coroutines.launch

class CategoryViewModel(
    private val productRepository: ProductRepository,
) : ViewModel() {

    val dataProducts = mutableStateOf<List<Product>>(listOf())

    fun loadDataByCategory(category: String) {

        viewModelScope.launch {

            val dataFromLocal = productRepository.getAllProductsByCategory(category)
            dataProducts.value = dataFromLocal

        }

    }

}