package com.borissoto.mobiletest.ui.detail

import androidx.lifecycle.*
import com.borissoto.mobiletest.data.database.CommentItem
import com.borissoto.mobiletest.data.database.PostsItem
import com.borissoto.mobiletest.data.database.UserItem
import com.borissoto.mobiletest.domain.AuthorRepository
import com.borissoto.mobiletest.domain.CommentsRepository
import kotlinx.coroutines.launch

class DetailViewModel(
    private val post: PostsItem
) : ViewModel() {
    data class UiDetailState(val post: PostsItem)
    data class UiAuthorState(
        val loading: Boolean = false,
        val author: UserItem? = null
    )
    data class UiCommentsState(
        val loading: Boolean = false,
        val comments: List<CommentItem>? = null,
    )

    private val authorRepository by lazy { AuthorRepository() }
    private val commentsRepository by lazy { CommentsRepository() }

    private val _state = MutableLiveData(UiDetailState(post))
    val state: LiveData<UiDetailState> get() = _state

    private val _stateAuthor = MutableLiveData(UiAuthorState())
    val stateAuthor : LiveData<UiAuthorState> get() {
        if (_stateAuthor.value?.author == null){
            getAuthor()
        }
        return _stateAuthor
    }

    private val _stateComments = MutableLiveData(UiCommentsState())
    val stateComments: LiveData<UiCommentsState> get() {
        if (_stateComments.value?.comments == null){
            getComments()
        }
        return _stateComments
    }
    private fun getAuthor() {
        viewModelScope.launch {
            _stateAuthor.value = UiAuthorState(loading = true)
            _stateAuthor.value = UiAuthorState(author = authorRepository.getAuthorById(post.userId))
            _stateAuthor.value = UiAuthorState(loading = false)
        }
    }
    private fun getComments() {
        viewModelScope.launch {
            _stateComments.value = UiCommentsState(loading = true)
            _stateComments.value = UiCommentsState(comments = commentsRepository.getCommentsByPostId(post.id))
            _stateComments.value = UiCommentsState(loading = false)
        }
    }
}

@Suppress("UNCHECKED_CAST")
class DetailViewModelFactory(private val post: PostsItem) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailViewModel(post) as T
    }
}
