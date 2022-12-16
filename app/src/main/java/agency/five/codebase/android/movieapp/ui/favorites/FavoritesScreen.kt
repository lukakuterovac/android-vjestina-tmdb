package agency.five.codebase.android.movieapp.ui.favorites

import agency.five.codebase.android.movieapp.R
import agency.five.codebase.android.movieapp.ui.component.MovieCard
import agency.five.codebase.android.movieapp.ui.theme.MovieAppTheme
import agency.five.codebase.android.movieapp.ui.theme.spacing
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp


private const val COLUMN_COUNT = 3

@Composable
fun FavoritesRoute(
    viewModel: FavoritesViewModel,
    onMovieCardClick: (Int) -> Unit
) {
    val favoritesViewState: FavoritesViewState by viewModel.favoritesViewState.collectAsState()
    FavoritesScreen(
        modifier = Modifier
            .fillMaxSize()
            .padding(MaterialTheme.spacing.small),
        favoritesViewState = favoritesViewState,
        onFavoriteButtonClick = { viewModel.removeFavorite(it) },
        onMovieCardClick = onMovieCardClick
    )
}

@Composable
fun FavoritesScreen(
    favoritesViewState: FavoritesViewState,
    onFavoriteButtonClick: (Int) -> Unit,
    onMovieCardClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Text(
            text = stringResource(id = R.string.favorites),
            modifier = Modifier
                .padding(MaterialTheme.spacing.medium),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(COLUMN_COUNT),
            content = {
                items(
                    items = favoritesViewState.favoriteMovies,
                    key = { movie -> movie.id }
                ) { card ->
                    MovieCard(
                        modifier = Modifier
                            .width(dimensionResource(id = R.dimen.movie_card_width))
                            .height(dimensionResource(id = R.dimen.movie_card_height))
                            .padding(MaterialTheme.spacing.small),
                        movieCardViewState = card.movieViewState,
                        onFavoriteButtonClick = { onFavoriteButtonClick(card.movieViewState.id) },
                        onClick = { onMovieCardClick(card.id) }
                    )
                }
            }
        )
    }
}

@Preview
@Composable
fun FavoritesScreenPreview() {
    MovieAppTheme {
        FavoritesScreen(
            modifier = Modifier,
            favoritesViewState = FavoritesViewState.EMPTY,
            onFavoriteButtonClick = { },
            onMovieCardClick = { }
        )
    }
}
