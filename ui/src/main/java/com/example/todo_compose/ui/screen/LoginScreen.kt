package com.example.todo_compose.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.repsository.AuthManager
import com.example.todo_compose.ui.theme.TodoComposeTheme
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val authManager: AuthManager) : ViewModel() {
    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            authManager.signIn(email, password)
        }
    }
}

data class LoginScreenInputState(val email: String = "", val password: String = "")

@Composable
fun LoginScreen() {
    val loginViewModel = hiltViewModel<LoginViewModel>()
    val inputState = remember {
        mutableStateOf(LoginScreenInputState())
    }
    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            TextField(modifier = Modifier
                .fillMaxWidth(0.8f),
                value = inputState.value.email,
                onValueChange = {
                    inputState.value = inputState.value.copy(email = it)
                },
                label = {
                    Text(text = "メールアドレス")
                }
            )
            Spacer(modifier = Modifier.height(12.dp))
            TextField(modifier = Modifier
                .fillMaxWidth(0.8f),
                value = inputState.value.password,
                onValueChange = {
                    inputState.value = inputState.value.copy(password = it)
                },
                label = {
                    Text(text = "パスワード")
                }
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = {
                    val state = inputState.value
                    loginViewModel.signIn(state.email, state.password)
                },
                Modifier
                    .fillMaxWidth(0.8f)
                    .clip(RoundedCornerShape(10))
            ) {
                Text(text = "ログイン")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    TodoComposeTheme {
        LoginScreen()
    }
}