package com.borissoto.mobiletest.ui.detail

import androidx.lifecycle.*
import com.borissoto.mobiletest.domain.Author
import com.borissoto.mobiletest.domain.Comment
import com.borissoto.mobiletest.usecases.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    findPostUseCase: FindPostUseCase,
    private val switchFavoriteUseCase: SwitchFavoriteUseCase,
    private val requestCommentsUseCase: RequestCommentsUseCase,
    private val authorUseCase: RequestAuthorUseCase
) : ViewModel() {

    private val postId = DetailFragmentArgs.fromSavedStateHandle(savedStateHandle).postId
    private val userId = DetailFragmentArgs.fromSavedStateHandle(savedStateHandle).userId
    data class UiDetailState(val post: com.borissoto.mobiletest.domain.Post? = null)
    data class UiAuthorState(
        val loading: Boolean = false,
        val author: Author? = null
    )

    data class UiCommentsState(
        val loading: Boolean = false,
        val comments: List<Comment>? = null,
    )

    private val _state = MutableStateFlow(UiDetailState())
    val state: StateFlow<UiDetailState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            findPostUseCase(postId).collect {
                _state.value = UiDetailState(it)
            }
        }
    }

    private val _stateAuthor = MutableLiveData(UiAuthorState())
    val stateAuthor: LiveData<UiAuthorState>
        get() {
            if (_stateAuthor.value?.author == null) {
                getAuthor()
            }
            return _stateAuthor
        }

    private fun getAuthor() {
        viewModelScope.launch {
            _stateAuthor.value = UiAuthorState(loading = true)
            _stateAuthor.value = UiAuthorState(author = authorUseCase(userId))
        }
    }

    private val _stateComments = MutableLiveData(UiCommentsState())
    val stateComments: LiveData<UiCommentsState>
        get() {
            if (_stateComments.value?.comments == null) {
                getComments()
            }
            return _stateComments
        }

    private fun getComments() {
        viewModelScope.launch {
            _stateComments.value = UiCommentsState(loading = true)
            _stateComments.value =
                UiCommentsState(comments = requestCommentsUseCase(postId))
        }
    }

    fun onFavoriteClicked() {
        viewModelScope.launch {
            _state.value.post?.let {
                switchFavoriteUseCase(it)
            }
        }
    }
}