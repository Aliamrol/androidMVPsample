package com.example.androidmvpsample.presenter.contract

import com.example.androidmvpsample.model.Post

interface INewPost {

    interface View {
        fun onSuccess(message: String)
        fun onError(message: String)
        fun onFail(message: String)
    }

    interface Presenter {
        suspend fun createNewPost(newPost : Post)
    }
}