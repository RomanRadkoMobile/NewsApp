package me.getreach.myapplication.features.news.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import me.getreach.myapplication.features.news.ui.NewsListScreen
import me.getreach.myapplication.features.news.ui.NewsDetailsScreen

@Composable
fun NewsNavHost(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = "newsList") {
        composable("newsList") {
            NewsListScreen(navController = navController)
        }
        composable("newsDetail/{newsId}") { backStackEntry ->
            val newsId = backStackEntry.arguments?.getString("newsId")
            newsId?.let {
                NewsDetailsScreen(newsId = it)
            }
        }
    }
}