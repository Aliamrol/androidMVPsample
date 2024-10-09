package com.example.androidmvpsample.presenter.present

import com.example.androidmvpsample.network.ApiClient
import com.example.androidmvpsample.presenter.contract.ISinglePost

class SinglePostPresenter(var view: ISinglePost.View) : ISinglePost.Presenter {
    override suspend fun getPostById(postId: String) {
        val response = try {
            ApiClient.api.getPostById(postId)
        } catch (e: Exception) {
            view.onFail("fail request : " + e.message)
            return
        }
        if (response.isSuccessful && response.body() != null) {
            response.body()?.let { singlePost ->
                view.onSuccess(singlePost)
            }
        } else {
            view.onError("error")
        }
    }

}