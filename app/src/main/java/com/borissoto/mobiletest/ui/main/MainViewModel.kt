package com.borissoto.mobiletest.ui.main

import androidx.lifecycle.*
import com.borissoto.mobiletest.domain.Post
import com.borissoto.mobiletest.usecases.GetPostsUseCase
import com.borissoto.mobiletest.usecases.RequestPostsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val getPostsUseCase: GetPostsUseCase,
    private val requestPostsUseCase: RequestPostsUseCase
) : ViewModel() {

    data class UiState(
        val loading: Boolean = false,
        val posts: List<Post>? = null,
    )

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            getPostsUseCase().collect {
                _state.value = UiState(posts = it)
            }
        }
    }

    fun onUiReady() {
        viewModelScope.launch {
//            _state.value = UiState(loading = true)
            requestPostsUseCase()
//            _state.value = UiState(loading = false)
        }
    }
}

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(
    private val getPostsUseCase: GetPostsUseCase,
    private val requestPostsUseCase: RequestPostsUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(getPostsUseCase, requestPostsUseCase) as T
    }

}