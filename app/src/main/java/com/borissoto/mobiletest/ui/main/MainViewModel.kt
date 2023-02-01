package com.borissoto.mobiletest.ui.main

import androidx.lifecycle.*
import com.borissoto.mobiletest.data.database.PostsItem
import com.borissoto.mobiletest.domain.PostsRepository
import kotlinx.coroutines.launch

class MainViewModel(
    private val postsRepository: PostsRepository
): ViewModel() {

    data class UiState(
        val loading: Boolean = false,
        val posts: List<PostsItem>? = null,
        val navigateTo: PostsItem? = null
    )

    private val _state = MutableLiveData(UiState())
    val state: LiveData<UiState> get() {
        if (_state.value?.posts == null){
            refresh()
        }
        return _state
    }
    private fun refresh() {
        viewModelScope.launch {
            _state.value = UiState(loading = true)
            _state.value = UiState(posts = postsRepository.getAllPosts())
            _state.value = UiState(loading = false)
        }
    }
    fun onPostClicked(post: PostsItem){
        _state.value = _state.value?.copy(navigateTo = post)
    }
}

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(private val postsRepository: PostsRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(postsRepository) as T
    }

}