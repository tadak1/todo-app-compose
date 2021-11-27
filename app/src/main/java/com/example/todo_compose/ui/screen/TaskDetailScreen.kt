package com.example.todo_compose.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.todo_compose.R
import com.example.todo_compose.ui.theme.TodoComposeTheme

// Stateful
@Composable
fun TaskDetailScreen(navController: NavController) {
    //TODO: convert UiState from ViewModel
    TaskDetailScreen(
        task = tasks[0],
    )
}

// Stateless
@Composable
fun TaskDetailScreen(task: Task) {
    val title = remember {
        mutableStateOf(task.title)
    }
    val content = remember {
        mutableStateOf(task.content)
    }
    Column {
        BasicTextField(
            modifier = Modifier.fillMaxWidth(),
            value = title.value,
            textStyle = TextStyle.Default.copy(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            ),
            onValueChange = {
                title.value = it
            },
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier
                        .wrapContentHeight()
                        .padding(horizontal = 8.dp, vertical = 16.dp),
                ) {
                    innerTextField()
                }
            }
        )
        BasicTextField(
            modifier = Modifier.fillMaxWidth(),
            value = content.value,
            textStyle = TextStyle.Default.copy(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            ),
            onValueChange = {
                content.value = it
            },
            decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier
                        .padding(
                            horizontal = 8.dp,
                            vertical = 16.dp
                        )
                ) {
                    Icon(
                        painterResource(id = R.drawable.ic_detail),
                        contentDescription = "detail"
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    innerTextField()
                }
            },
        )
        Row(
            modifier = Modifier
                .padding(
                    horizontal = 8.dp,
                    vertical = 16.dp
                )
                .clickable {}
        ) {
            Icon(
                painterResource(id = R.drawable.ic_calendar_today),
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "日時を入力")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TaskDetailScreenPreview() {
    TodoComposeTheme {
        TaskDetailScreen(task = tasks[1])
    }
}