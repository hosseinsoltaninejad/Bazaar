package ir.hossein.bazaar.model.repository.cart

import ir.hossein.bazaar.model.data.CheckOut
import ir.hossein.bazaar.model.data.SubmitOrder
import ir.hossein.bazaar.model.data.UserCartInfo

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