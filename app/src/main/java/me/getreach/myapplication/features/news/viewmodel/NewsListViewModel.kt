package me.getreach.myapplication.features.news.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import me.getreach.myapplication.features.news.domain.repository.NewsListRepository
import me.getreach.myapplication.features.news.domain.model.NewsEntity

@HiltViewModel
class NewsListViewModel @Inject constructor(
    private val newsListRepository: NewsListRepository
) : ViewModel() {

    private val _listState = MutableStateFlow<NewsListState>(NewsListState.Loading)
    val listState: StateFlow<NewsListState> = _listState

    init {
        fetchNewsList("us")
    }

    internal fun fetchNewsList(country: String) {
        viewModelScope.launch {
            try {
                _listState.value = NewsListState.Loading
                val newsList = newsListRepository.getNewsSources(country)
                _listState.value = NewsListState.Success(newsList)
            } catch (e: Exception) {
                _listState.value = NewsListState.Error("Failed to load news list: ${e.localizedMessage}")
            }
        }
    }
}

sealed class NewsListState {
    object Loading : NewsListState()
    data class Success(val newsList: List<NewsEntity>) : NewsListState()
    data class Error(val message: String) : NewsListState()
}