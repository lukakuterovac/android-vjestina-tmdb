package agency.five.codebase.android.movieapp.ui.favorites

import agency.five.codebase.android.movieapp.ui.component.MovieCardViewState

data class FavoritesMovieViewState(
    val id: Int,
    val movieViewState: MovieCardViewState
)

data class FavoritesViewState(
    val favoriteMovies: List<FavoritesMovieViewState>
) {
    companion object{
        val EMPTY: FavoritesViewState = FavoritesViewState(
            favoriteMovies = emptyList()
        )
    }
}
