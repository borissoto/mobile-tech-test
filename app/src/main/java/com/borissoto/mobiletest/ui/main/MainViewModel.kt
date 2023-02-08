package com.borissoto.mobiletest.ui.main

import androidx.lifecycle.*
import com.borissoto.mobiletest.domain.PostsRepository
import com.borissoto.mobiletest.model.database.Post
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val postsRepository: PostsRepository
): ViewModel() {

    data class UiState(
        val loading: Boolean = false,
        val posts: List<Post>? = null,
    )

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            postsRepository.allPosts.collect{
                _state.value = UiState(posts = it)
            }
        }
    }
    fun onUiReady() {
        viewModelScope.launch {
//            _state.value = UiState(loading = true)
            postsRepository.requestAllPosts()

        }
    }
}

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(private val postsRepository: PostsRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(postsRepository) as T
    }

}