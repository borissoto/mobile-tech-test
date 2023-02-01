package com.borissoto.mobiletest.domain

import androidx.appcompat.app.AppCompatActivity
import com.borissoto.mobiletest.data.datasource.RemoteConnection

class PostsRepository() {
    suspend fun getAllPosts() = RemoteConnection.service.listAllPosts()
}