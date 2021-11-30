package com.example.todo_compose.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todo_compose.ui.theme.TodoComposeTheme

@Composable
fun LoginScreen() {
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
                value = "",
                onValueChange = {},
                label = {
                    Text(text = "メールアドレス")
                }
            )
            Spacer(modifier = Modifier.height(12.dp))
            TextField(modifier = Modifier
                .fillMaxWidth(0.8f),
                value = "",
                onValueChange = {},
                label = {
                    Text(text = "パスワード")
                }
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = { /*TODO*/ },
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