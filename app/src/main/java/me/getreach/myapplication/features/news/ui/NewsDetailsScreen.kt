package me.getreach.myapplication.features.news.ui

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import me.getreach.myapplication.features.news.viewmodel.NewsDetailsViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import me.getreach.myapplication.features.news.domain.model.NewsEntity
import me.getreach.myapplication.features.news.viewmodel.NewsDetailState
import timber.log.Timber

@Composable
fun NewsDetailsScreen(newsId: String, viewModel: NewsDetailsViewModel = hiltViewModel()) {
    val newsDetailState by viewModel.newsDetailState.collectAsState()
    val TAG = "NewsDetailsScreen"

    LaunchedEffect(newsId) {
        Timber.tag(TAG).d("Fetching details for newsId: $newsId")
        viewModel.getNewsDetails(newsId)
    }

    when (newsDetailState) {
        is NewsDetailState.Loading -> {
            Timber.tag(TAG).d("NewsDetailState.Loading")
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(
                    modifier = Modifier.size(48.dp), // Adjust the size if needed
                    color = Color.Black
                )
            }
        }

        is NewsDetailState.Success -> {
            Timber.tag(TAG).d("NewsDetailState.Success")
            val newsDetails = (newsDetailState as NewsDetailState.Success).newsDetails
            NewsDetailsContent(newsDetails)
        }

        is NewsDetailState.Error -> {
            Timber.tag(TAG).d("NewsDetailState.Error")
            Text(
                text = "Error loading details: ${(newsDetailState as NewsDetailState.Error).message}",
                modifier = Modifier.fillMaxSize(),
                color = Color.Red,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun NewsDetailsContent(newsDetails: NewsEntity) {
    Timber.tag("NewsDetailsScreen").d("NewsDetailsContent rendering, newsDetails: $newsDetails")
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = newsDetails.name,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = newsDetails.description,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start
        )

        Spacer(modifier = Modifier.height(16.dp))

        WebViewWithLoading(newsDetails)
    }
}

@Composable
fun WebViewWithLoading(newsDetails: NewsEntity) {
    var isLoading by remember { mutableStateOf(true) }

    Box(modifier = Modifier.fillMaxSize()) {
        AndroidView(factory = { context ->
            WebView(context).apply {
                webViewClient = object : WebViewClient() {
                    override fun onPageStarted(view: WebView?, url: String?, favicon: android.graphics.Bitmap?) {
                        super.onPageStarted(view, url, favicon)
                        isLoading = true
                    }

                    override fun onPageFinished(view: WebView?, url: String?) {
                        super.onPageFinished(view, url)
                        isLoading = false
                    }
                }
                loadUrl(newsDetails.url)
            }
        }, modifier = Modifier.fillMaxSize())

        if (isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }
}