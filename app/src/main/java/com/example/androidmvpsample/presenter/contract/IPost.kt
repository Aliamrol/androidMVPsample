package com.example.androidmvpsample.presenter.contract

import com.example.androidmvpsample.model.Post

interface IPost {

    interface View {
        fun onSuccess(postList: List<Post>)
        fun onError(message: String)
        fun onFail(message: String)
    }

    interface Presenter {
        suspend fun getAllPostsRequests()
    }
}