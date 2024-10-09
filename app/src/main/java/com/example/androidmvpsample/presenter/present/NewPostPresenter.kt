package com.example.androidmvpsample.presenter.present

import com.example.androidmvpsample.model.Post
import com.example.androidmvpsample.network.ApiClient
import com.example.androidmvpsample.presenter.contract.INewPost
import com.example.androidmvpsample.presenter.contract.IPost

class NewPostPresenter(private val view: INewPost.View) : INewPost.Presenter {
    override suspend fun createNewPost(newPost: Post) {
        val response = try {
            ApiClient.api.createNewPost(newPost)
        } catch (e: Exception) {
            view.onFail("fail request : " + e.message)
            return
        }
        println("status code : " + response.code())
        if (response.isSuccessful && response.body() != null) {
            response.body()?.let { post ->
                view.onSuccess("post with id : ${post.id} created!")
            }
        } else {
            view.onError("error")
        }
    }

}