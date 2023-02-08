package com.borissoto.mobiletest.ui.detail

import androidx.lifecycle.*
import com.borissoto.mobiletest.data.database.CommentItem
import com.borissoto.mobiletest.data.database.UserItem
import com.borissoto.mobiletest.domain.AuthorRepository
import com.borissoto.mobiletest.domain.CommentsRepository
import com.borissoto.mobiletest.domain.PostsRepository
import com.borissoto.mobiletest.model.database.Post
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val post: Post,
    private val postsRepository: PostsRepository,
) : ViewModel() {
    data class UiDetailState(val post: Post? = null)
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

    private val _state = MutableStateFlow(UiDetailState())
    val state: StateFlow<UiDetailState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            postsRepository.findPostById(post.id).collect{
                _state.value = UiDetailState(it)
            }
        }
    }

    private val _stateAuthor = MutableLiveData(UiAuthorState())
    val stateAuthor : LiveData<UiAuthorState> get() {
        if (_stateAuthor.value?.author == null){
            getAuthor()
        }
        return _stateAuthor
    }

    private fun getAuthor() {
        viewModelScope.launch {
            _stateAuthor.value = UiAuthorState(loading = true)
            _stateAuthor.value = UiAuthorState(author = authorRepository.getAuthorById(post.userId))
        }
    }

    private val _stateComments = MutableLiveData(UiCommentsState())
    val stateComments: LiveData<UiCommentsState> get() {
        if (_stateComments.value?.comments == null){
            getComments()
        }
        return _stateComments
    }
    private fun getComments() {
        viewModelScope.launch {
            _stateComments.value = UiCommentsState(loading = true)
            _stateComments.value = UiCommentsState(comments = commentsRepository.getCommentsByPostId(post.id))
        }
    }

    fun onFavoriteClicked() {
        viewModelScope.launch {
            _state.value.post?.let {
                postsRepository.switchFavorite(it)
            }
        }
    }
}

@Suppress("UNCHECKED_CAST")
class DetailViewModelFactory(private val post: Post, private val postsRepository: PostsRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailViewModel(post, postsRepository) as T
    }
}
