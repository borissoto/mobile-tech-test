package com.borissoto.mobiletest.ui.detail

import com.borissoto.mobiletest.data.database.CommentItem
import com.borissoto.mobiletest.data.database.UserItem
import com.borissoto.mobiletest.domain.AuthorRepository
import com.borissoto.mobiletest.domain.CommentsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class DetailPresenter(
    private val authorRepository: AuthorRepository,
    private val commentsRepository: CommentsRepository,
    private val coroutineScope: CoroutineScope
) {

    interface View{
        fun updateComments(allComments: List<CommentItem>)
        fun updateAuthor(author: UserItem)
    }

    private var view: View? = null

    fun onCreate(view: View, postId: Int, userId: Int){
        this.view = view
        coroutineScope.launch {
                view.updateAuthor(authorRepository.getAuthorById(userId))
                view.updateComments(commentsRepository.getCommentsByPostId(postId))
        }
    }

    fun onDestroy(){
        this.view = null
    }

}