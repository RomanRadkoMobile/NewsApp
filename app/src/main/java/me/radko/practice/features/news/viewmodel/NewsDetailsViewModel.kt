package me.radko.practice.features.news.viewmodel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import me.radko.practice.features.news.domain.repository.NewsDetailsRepository
import me.radko.practice.features.news.domain.model.NewsEntity
import javax.inject.Inject

@HiltViewModel
class NewsDetailsViewModel @Inject constructor(
    private val newsDetailsRepository: NewsDetailsRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _newsDetailState = MutableStateFlow<NewsDetailState>(NewsDetailState.Loading)
    val newsDetailState: StateFlow<NewsDetailState> = _newsDetailState

    init {
        val newsId: String? = savedStateHandle["newsId"]
        newsId?.let {
            getNewsDetails(it)
        } ?: run {
            _newsDetailState.value = NewsDetailState.Error("Invalid news ID")
        }
    }

    fun getNewsDetails(newsId: String) {
        viewModelScope.launch {
            _newsDetailState.value = NewsDetailState.Loading
            try {
                val newsDetails = newsDetailsRepository.getNewsDetails(newsId)
                _newsDetailState.value = NewsDetailState.Success(newsDetails)
            } catch (e: Exception) {
                _newsDetailState.value =
                    NewsDetailState.Error("Failed to load news details: ${e.localizedMessage}")
            }
        }
    }

}


sealed class NewsDetailState {
    data object Loading : NewsDetailState()
    data class Success(val newsDetails: NewsEntity) : NewsDetailState()
    data class Error(val message: String) : NewsDetailState()
}