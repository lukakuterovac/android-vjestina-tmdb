package agency.five.codebase.android.movieapp.ui.home

import agency.five.codebase.android.movieapp.data.repository.MovieRepository
import agency.five.codebase.android.movieapp.model.MovieCategory
import agency.five.codebase.android.movieapp.ui.home.mapper.HomeScreenMapper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

private const val STOP_TIMEOUT_MILLIS = 5000L

class HomeViewModel(
    private val movieRepository: MovieRepository,
    private val homeScreenMapper: HomeScreenMapper,
) : ViewModel() {
    private val popularCategories = listOf(
        MovieCategory.POPULAR_STREAMING,
        MovieCategory.POPULAR_ON_TV,
        MovieCategory.POPULAR_FOR_RENT,
        MovieCategory.POPULAR_IN_THEATRES
    )

    private val nowPlayingCategories = listOf(
        MovieCategory.NOW_PLAYING_MOVIES,
        MovieCategory.NOW_PLAYING_TV
    )

    private val upcomingCategories = listOf(
        MovieCategory.UPCOMING_TODAY,
        MovieCategory.UPCOMING_THIS_WEEK
    )

    private val popularSelectedCategory = MutableStateFlow(MovieCategory.POPULAR_STREAMING)
    private val nowPlayingSelectedCategory = MutableStateFlow(MovieCategory.NOW_PLAYING_MOVIES)
    private val upcomingSelectedCategory = MutableStateFlow(MovieCategory.UPCOMING_TODAY)

    private val initialHomeMovieCategoryState = HomeMovieCategoryViewState(emptyList(), emptyList())

    val popularCategoryViewState = popularSelectedCategory.flatMapLatest { category ->
        movieRepository.movies(category)
            .map { movies ->
                homeScreenMapper.toHomeMovieCategoryViewState(popularCategories, category, movies)
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(STOP_TIMEOUT_MILLIS),
                initialValue = initialHomeMovieCategoryState,
            )
    }

    val nowPlayingCategoryViewState = nowPlayingSelectedCategory.flatMapLatest { category ->
        movieRepository.movies(category)
            .map { movies ->
                homeScreenMapper.toHomeMovieCategoryViewState(nowPlayingCategories, category, movies)
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(STOP_TIMEOUT_MILLIS),
                initialValue = initialHomeMovieCategoryState,
            )
    }

    val upcomingCategoryViewState = upcomingSelectedCategory.flatMapLatest { category ->
        movieRepository.movies(category)
            .map { movies ->
                homeScreenMapper.toHomeMovieCategoryViewState(upcomingCategories, category, movies)
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(STOP_TIMEOUT_MILLIS),
                initialValue = initialHomeMovieCategoryState,
            )
    }

    fun toggleFavorite(id: Int) {
        viewModelScope.launch {
            movieRepository.toggleFavorite(id)
        }
    }

    fun changeCategory(categoryId: Int) {
        when (categoryId) {
            MovieCategory.POPULAR_STREAMING.ordinal,
            MovieCategory.POPULAR_FOR_RENT.ordinal,
            MovieCategory.POPULAR_ON_TV.ordinal,
            MovieCategory.POPULAR_IN_THEATRES.ordinal
            -> {
                popularSelectedCategory.update { MovieCategory.values()[categoryId] }
            }

            MovieCategory.NOW_PLAYING_MOVIES.ordinal,
            MovieCategory.NOW_PLAYING_TV.ordinal
            -> {
                nowPlayingSelectedCategory.update { MovieCategory.values()[categoryId] }
            }

            else -> {
                upcomingSelectedCategory.update { MovieCategory.values()[categoryId] }
            }
        }
    }
}
