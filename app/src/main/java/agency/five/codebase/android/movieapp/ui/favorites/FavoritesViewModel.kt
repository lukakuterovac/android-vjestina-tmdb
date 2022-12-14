package agency.five.codebase.android.movieapp.ui.favorites

import agency.five.codebase.android.movieapp.data.repository.MovieRepository
import agency.five.codebase.android.movieapp.ui.favorites.mapper.FavoritesMapper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val movieRepository: MovieRepository,
    private val favoritesMapper: FavoritesMapper,
) : ViewModel() {
    val favoritesViewState: StateFlow<FavoritesViewState> =
        movieRepository.favoriteMovies()
            .map(favoritesMapper::toFavoritesViewState)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = FavoritesViewState.EMPTY,
            )

    fun removeFavorite(movieId: Int) {
        viewModelScope.launch {
            movieRepository.removeMovieFromFavorites(movieId)
        }
    }
}
