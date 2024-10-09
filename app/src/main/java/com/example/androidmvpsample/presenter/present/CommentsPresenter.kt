package com.example.androidmvpsample.presenter.present

import com.example.androidmvpsample.network.ApiClient
import com.example.androidmvpsample.presenter.contract.IComments
import com.example.androidmvpsample.presenter.contract.IPost

class CommentsPresenter(private val view: IComments.View) : IComments.Presenter {

    override suspend fun getAllPostComments(postId: Int) {
        val response = try {
            ApiClient.api.getAllPostComments(postId)
        } catch (e: Exception) {
            view.onFail("fail request : " + e.message)
            return
        }
        if (response.isSuccessful && response.body() != null) {
            response.body()?.let { allPostComments ->
                view.onSuccess(allPostComments)
            }
        } else {
            view.onError("error : " + response.errorBody())
        }
    }

}