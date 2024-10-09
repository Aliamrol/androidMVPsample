package com.example.androidmvpsample.presenter.present

import com.example.androidmvpsample.model.Login
import com.example.androidmvpsample.presenter.contract.ILogin
import kotlin.properties.Delegates

class LoginPresenter(private var view: ILogin.View) : ILogin.Presenter {

    override fun login(userName: String, password: String) {
        val loginModel = Login()
        if(loginModel.userName == userName && loginModel.password == password){
            view.onSuccess("Login is ok (from presenter)")
        }else{
            view.onFail("Login is fail (from presenter)")
        }
    }

}


// business login in presenter
