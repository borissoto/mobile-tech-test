package com.borissoto.mobiletest.ui.main

import com.borissoto.mobiletest.data.database.PostsItem
import com.borissoto.mobiletest.domain.PostsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainPresenter(
    private val postsRepository: PostsRepository,
    private val scope: CoroutineScope
) {

    interface View{
        fun updateData(allPosts: List<PostsItem>)
        fun navigateTo(post: PostsItem)
    }

    private var view: View? = null

    fun onCreate(view: View) {
        this.view = view
        scope.launch {
            view.updateData(postsRepository.getAllPosts())
        }
    }

    fun onPostClicked(post: PostsItem){
        view?.navigateTo(post)
    }

    fun onDestroy(){
        this.view = null
    }
}