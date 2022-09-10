package ir.hosseinsoltani.bazaar.model.repository.cart

import ir.hosseinsoltani.bazaar.model.data.CheckOut
import ir.hosseinsoltani.bazaar.model.data.SubmitOrder
import ir.hosseinsoltani.bazaar.model.data.UserCartInfo

interface CartRepository {

   suspend fun addToCart(productId: String): Boolean
   suspend fun removeFromCart(productId: String): Boolean
   suspend fun getCartSize(): Int
   suspend fun getUserCartInfo(): UserCartInfo

   suspend fun submitOrder(address: String, postalCode: String): SubmitOrder
   suspend fun checkOut(orderId: String): CheckOut

   fun setOrderId(orderId: String)
   fun getOrderId(): String

   fun setPurchaseStatus(status: Int)
   fun getPurchaseStatus(): Int

}