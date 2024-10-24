package me.getreach.myapplication.features.news.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import me.getreach.myapplication.features.news.domain.model.NewsEntity
import me.getreach.myapplication.features.news.viewmodel.NewsListViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import me.getreach.myapplication.features.news.viewmodel.NewsListState

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NewsListScreen(navController: NavController, viewModel: NewsListViewModel = hiltViewModel()) {
    val listState by viewModel.listState.collectAsState()

    LaunchedEffect("us") { viewModel.fetchNewsList("us") }

    when (val state = listState) {
        is NewsListState.Loading -> CircularProgressIndicator()
        is NewsListState.Success -> NewsList(state.newsList, navController)
        is NewsListState.Error -> Text(text = state.message)
    }
}

@Composable
fun NewsList(newsList: List<NewsEntity>, navController: NavController) {
    LazyColumn {
        items(newsList) { news ->
            NewsListItem(source = news) {
                navController.navigate("newsDetail/${news.id}")
            }
        }
    }
}

@Composable
fun NewsListItem(source: NewsEntity, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color.LightGray, shape = RoundedCornerShape(8.dp))
            .clickable { onClick() }
            .padding(16.dp)
    ) {
        Text(text = source.name, style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = source.description, style = MaterialTheme.typography.bodyMedium)
    }
}