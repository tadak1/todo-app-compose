package com.example.todo_compose.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.todo_compose.ui.theme.TodoComposeTheme

@Composable
fun AccountScreen(nestedNavController: NavController?) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                horizontal = 12.dp,
            )
            .padding(top = 20.dp)
    ) {
        Text(
            text = "アカウント",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(vertical = 12.dp)
        )
        Box(
            Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp)
                .clickable(onClick = {
                    nestedNavController?.navigate("login")
                }),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "サインイン",
                fontSize = 16.sp,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
        Divider(modifier = Modifier.height(2.dp))
        Box(
            Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp)
                .clickable(onClick = {}),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "ライセンス",
                fontSize = 16.sp,
                modifier = Modifier
                    .fillMaxWidth()

            )
        }
        Box(
            Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp)
                .clickable(onClick = {}),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Version 0.0.0",
                fontSize = 16.sp,
                modifier = Modifier
                    .fillMaxWidth()

            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AccountScreenPreview() {
    TodoComposeTheme {
        AccountScreen(null)
    }
}