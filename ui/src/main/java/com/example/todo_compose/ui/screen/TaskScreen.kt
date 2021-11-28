package com.example.todo_compose.ui.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.todo_compose.ui.theme.Gray100
import com.example.todo_compose.ui.theme.Gray500
import com.example.todo_compose.ui.theme.TodoComposeTheme
import com.example.todo_compose.TaskScreenRoute

class Task(val id: Long?, val title: String, val content: String)

val tasks = listOf(
    Task(id = 1, title = "12/1 やること", content = "朝一でゴミ出しをする予定です。その後に洗濯機を回して洗濯物を干します。"),
    Task(
        id = 2,
        title = "12/2 やること",
        content = "朝一でゴミ出しをする予定です。その後に洗濯機を回して洗濯物を干します。朝一でゴミ出しをする予定です。その後に洗濯機を回して洗濯物を干します。朝一でゴミ出しをする予定です。その後に洗濯機を回して洗濯物を干します。朝一でゴミ出しをする予定です。その後に洗濯機を回して洗濯物を干します。朝一でゴミ出しをする予定です。その後に洗濯機を回して洗濯物を干します。朝一でゴミ出しをする予定です。その後に洗濯機を回して洗濯物を干します。"
    ),
    Task(id = 3, title = "12/3 やること", content = "朝一でゴミ出しをする予定です。その後に洗濯機を回して洗濯物を干します。"),
    Task(id = 4, title = "12/4 やること", content = "買い物に出かけます"),
    Task(id = 5, title = "12/5 やること", content = "朝一でゴミ出しをする予定です。その後に洗濯機を回して洗濯物を干します。"),
    Task(id = 6, title = "12/6 やること", content = "朝一でゴミ出しをする予定です。"),
    Task(id = 7, title = "12/7 やること", content = "朝一でゴミ出しをする予定です。"),
    Task(id = 8, title = "12/8 やること", content = "朝一でゴミ出しをする予定です。"),
    Task(id = 9, title = "12/9 やること", content = "朝一でゴミ出しをする予定です。"),
)

// Stateful
@Composable
fun TaskScreen(navController: NavController) {
    //TODO: convert UiState from ViewModel
    TaskScreen(
        tasks = tasks,
        onTapCard = {
            navController.navigate(TaskScreenRoute.Detail.routeWithArgument(it))
        }
    )
}

// Stateless
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TaskScreen(tasks: List<Task>, onTapCard: (taskId: Long) -> Unit) {
    val listState = rememberLazyListState()
    LazyColumn(
        state = listState,
        contentPadding = PaddingValues(8.dp),
    ) {
        items(tasks.count()) { index ->
            val task = tasks[index]
            TaskCard(
                modifier = Modifier
                    .fillParentMaxWidth()
                    .padding(4.dp),
                task = task,
                onTapCard = onTapCard
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun TaskScreenPreview() {
    TodoComposeTheme {
        TaskScreen(tasks = tasks, onTapCard = {})
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TaskCard(modifier: Modifier, task: Task, onTapCard: (taskId: Long) -> Unit) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .clickable {
                task.id?.let { onTapCard(it) }
            }
            .background(Gray500)
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
        ) {
            Text(
                text = task.title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Box(modifier = Modifier.height(8.dp))
            Text(
                text = task.content,
                fontSize = 12.sp,
                color = Gray100,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TaskCardPreview() {
    TodoComposeTheme {
        TaskCard(
            modifier = Modifier.width(200.dp),
            task = tasks[0],
            onTapCard = {}
        )
    }
}