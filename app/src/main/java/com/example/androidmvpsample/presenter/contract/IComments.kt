package com.example.androidmvpsample.presenter.contract

import com.example.androidmvpsample.model.Comment

interface IComments {

    interface View {
        fun onSuccess(commentList: List<Comment>)
        fun onError(message: String)
        fun onFail(message: String)
    }

    interface Presenter {
        suspend fun getAllPostComments(postId: Int)
    }
}