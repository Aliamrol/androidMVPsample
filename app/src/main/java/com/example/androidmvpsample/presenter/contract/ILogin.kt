package com.example.androidmvpsample.presenter.contract

interface ILogin {

    interface View {
        fun onSuccess(message: String)
        fun onFail(message: String)
    }

    interface Presenter {
        fun login(userName: String, password: String)
    }
}



// contract is connection between view and presenter