package com.borissoto.mobiletest.data

import com.borissoto.mobiletest.data.datasource.ILocalDataSource
import com.borissoto.mobiletest.data.datasource.IRemoteDataSource
import com.borissoto.mobiletest.domain.Post
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.argThat
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class PostsRepositoryTest {

    @Mock
    lateinit var localDataSource: ILocalDataSource

    @Mock
    lateinit var remoteDataSource: IRemoteDataSource

    lateinit var postsRepository: PostsRepository

    val localPosts = flowOf(listOf(samplePost.copy(1)))

    @Before
    fun setUp(){
        whenever(localDataSource.posts).thenReturn(localPosts)
        postsRepository = PostsRepository(localDataSource, remoteDataSource)
    }

    @Test
    fun `Posts are taken from local data source if available`(): Unit = runBlocking{

        val result = postsRepository.allPosts

        assertEquals(localPosts, result)
    }

    @Test
    fun `Switching favorite marks as favorite an not favorite post`(): Unit = runBlocking {
        val post = samplePost.copy(favorite = false)

        postsRepository.switchFavorite(post)

        verify(localDataSource).save(argThat {
            get(0).favorite
        })
    }

    @Test
    fun `Switching favorite mark as not favorite an favorite post`(): Unit = runBlocking {
        val post = samplePost.copy(favorite = true)

        postsRepository.switchFavorite(post)

        verify(localDataSource).save(argThat {
            !get(0).favorite
        })
    }
}

private val samplePost = Post(
    1, "Body of post", "Title of post", 1, true
)