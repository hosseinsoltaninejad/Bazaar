package ir.hosseinsoltani.bazaar.model.repository.comment

import ir.hosseinsoltani.bazaar.model.data.Comment


interface CommentRepository {

    suspend fun getAllComments(productId :String) :List<Comment>
    suspend fun addNewComment(productId: String , text :String , IsSuccess :(String) -> Unit)

}