package com.example.androidmvpsample.view

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.example.androidmvpsample.model.Comment
import com.example.androidmvpsample.model.Post
import com.example.androidmvpsample.network.ApiClient
import com.example.androidmvpsample.presenter.contract.IComments
import com.example.androidmvpsample.presenter.contract.ILogin
import com.example.androidmvpsample.presenter.contract.INewPost
import com.example.androidmvpsample.presenter.contract.IPost
import com.example.androidmvpsample.presenter.contract.ISinglePost
import com.example.androidmvpsample.presenter.present.CommentsPresenter
import com.example.androidmvpsample.presenter.present.LoginPresenter
import com.example.androidmvpsample.presenter.present.NewPostPresenter
import com.example.androidmvpsample.presenter.present.PostPresenter
import com.example.androidmvpsample.presenter.present.SinglePostPresenter
import com.example.androidmvpsample.ui.theme.AndroidMVPSampleTheme

lateinit var posts: List<Post>

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidMVPSampleTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var postList by remember {
                        mutableStateOf(emptyList<Post>())
                    }

                    lifecycleScope.launchWhenCreated {
                        postList = getAllPostsRequests()
                    }

                    Column() {
                        PostView(postList = postList)
                    }
                }
            }
        }
    }
}


@Composable
fun PostView(postList: List<Post>) {
    LazyColumn() {
        items(postList) { post ->
            Column(
                modifier = Modifier
                    .padding(12.dp)
                    .background(androidx.compose.ui.graphics.Color.Gray)
            ) {
                Text(text = "${post.id} : ${post.title}", color = androidx.compose.ui.graphics.Color.Red)
                Text(text = post.body, color = androidx.compose.ui.graphics.Color.White)
            }
        }
    }
}

suspend fun createNewPost(newPost: Post) {
    val presenter = NewPostPresenter(object : INewPost.View {
        override fun onSuccess(message: String) {
            println(message)
        }

        override fun onError(message: String) {
            println(message)
        }

        override fun onFail(message: String) {
            println(message)
        }
    })
    presenter.createNewPost(newPost)
}

suspend fun getAllPostComments(postId: Int) {
    val presenter = CommentsPresenter(object : IComments.View {
        override fun onSuccess(commentList: List<Comment>) {
            commentList.forEach { comment ->
                println(comment.body)
            }
        }

        override fun onError(message: String) {
            println(message)
        }

        override fun onFail(message: String) {
            println(message)
        }
    })
    presenter.getAllPostComments(postId)
}

suspend fun getPostById(postId: String) {
    val presenter = SinglePostPresenter(object : ISinglePost.View {
        override fun onSuccess(singlePost: Post) {
            println(singlePost.id.toString() + " : " + singlePost.title)
        }

        override fun onError(message: String) {
            println(message)
        }

        override fun onFail(message: String) {
            println(message)
        }
    })
    presenter.getPostById(postId)
}


suspend fun getAllPostsRequests(): List<Post> {
    val presenter = PostPresenter(object : IPost.View {
        override fun onSuccess(postList: List<Post>) {
            posts = postList
        }

        override fun onError(message: String) {
            println(message)
        }

        override fun onFail(message: String) {
            println(message)
        }
    })
    presenter.getAllPostsRequests()
    return posts
}

fun login(userName: String, password: String) {
    val presenter = LoginPresenter(object : ILogin.View {
        override fun onSuccess(message: String) {
            println(message)
        }

        override fun onFail(message: String) {
            println(message)
        }

    })
    presenter.login(userName, password)
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AndroidMVPSampleTheme {
        Greeting("Android")
    }
}