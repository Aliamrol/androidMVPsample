package com.example.androidmvpsample.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.androidmvpsample.presenter.contract.ILogin
import com.example.androidmvpsample.presenter.present.LoginPresenter
import com.example.androidmvpsample.ui.theme.AndroidMVPSampleTheme
import kotlin.math.log

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidMVPSampleTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                    login("username", "1234")
                }
            }
        }
    }
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