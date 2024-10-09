package com.example.androidmvpsample.presenter.present

import com.example.androidmvpsample.network.ApiClient
import com.example.androidmvpsample.presenter.contract.IPost

class PostPresenter(private val view: IPost.View) : IPost.Presenter {
    override suspend fun getAllPostsRequests() {
        val response = try {
            ApiClient.api.getAllPosts()
        } catch (e: Exception) {
            view.onFail("fail request : " + e.message)
            return
        }
        if (response.isSuccessful && response.body() != null) {
            response.body()?.let { allPost ->
                view.onSuccess(allPost)
            }
        } else {
            view.onError("error")
        }
    }

}