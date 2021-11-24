package com.example.todo_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.todo_compose.ui.theme.TodocomposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val nestedNavController = rememberNavController()
            TodocomposeTheme {
                Scaffold(
                    bottomBar = {
                        BottomNavigationBar(navController = nestedNavController)
                    }
                ) { innerPadding ->
                    NavHost(
                        nestedNavController,
                        startDestination = TabScreen.Todo.route,
                        Modifier.padding(innerPadding)
                    ) {
                        composable(TabScreen.Todo.route) {
                            Column {
                                Text(text = "Home")
                                Button(onClick = { nestedNavController.navigate(TodoScreen.Detail.routeWithArgument(1)) }) {}
                            }
                        }
                        composable(TodoScreen.Detail.routeName(), listOf(
                            navArgument("detailId") { type = NavType.LongType }
                        )) { backStackEntry ->
                            Box {
                                val detailId = backStackEntry.arguments?.getLong("detailId")
                                Text(text = "detail $detailId")
                            }
                        }
                        composable(TabScreen.Account.route) {
                            Text(text = "Account")
                        }
                    }
                }
            }
        }
    }
}

sealed class TabScreen(
    val title: String,
    val iconId: Int,
    val route: String
) {
    object Todo : TabScreen(title = "Todo", iconId = R.drawable.ic_home, route = "todo")
    object Account : TabScreen(title = "Account", iconId = R.drawable.ic_account, route = "account")
}

sealed class TodoScreen(
    val title: String,
    val route: String,
    val argument: String
) {
    object Detail : TodoScreen(title = "Detail", route = "todo", argument = "detailId") {
        fun routeName(): String {
            return "${route}/{$argument}"
        }

        fun routeWithArgument(id: Long): String {
            return "${route}/$id"
        }
    }
}


@Composable
fun BottomNavigationBar(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val items = listOf(
        TabScreen.Todo,
        TabScreen.Account,
    )
    BottomNavigation(
        backgroundColor = Color.Black,
        contentColor = Color.White
    ) {
        items.forEach { screen ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        painterResource(id = screen.iconId),
                        contentDescription = screen.title
                    )
                },
                label = { Text(text = screen.title) },
                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TodocomposeTheme {

    }
}