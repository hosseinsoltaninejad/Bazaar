package ir.hosseinsoltani.bazaar.model.data

data class SubmitOrder(
    val success: Boolean ,
    val orderId: Int,
    val paymentLink :String
)