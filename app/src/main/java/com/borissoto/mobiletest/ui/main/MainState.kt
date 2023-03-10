package com.borissoto.mobiletest.ui.main

import androidx.navigation.NavController
import com.borissoto.mobiletest.domain.Post
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainState(
    private val scope: CoroutineScope,
    private val navController: NavController
) {

    fun onPostClicked(post: Post) {
        val navAction = MainFragmentDirections.actionMainFragmentToDetailFragment(post.id, post.userId)
         navController.navigate(navAction)
    }

    fun uiReady(afterRequest: (Boolean) -> Unit) {
        scope.launch {
            afterRequest(true)
        }
    }
}