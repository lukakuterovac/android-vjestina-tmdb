package agency.five.codebase.android.movieapp.ui.home

import agency.five.codebase.android.movieapp.R
import agency.five.codebase.android.movieapp.ui.component.MovieCard
import agency.five.codebase.android.movieapp.ui.component.MovieCardViewState
import agency.five.codebase.android.movieapp.ui.component.MovieCategoryLabel
import agency.five.codebase.android.movieapp.ui.theme.spacing
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun HomeRoute(
    viewModel: HomeViewModel,
    onMovieCardClick: (Int) -> Unit,
) {
    val upcomingMoviesViewState: HomeMovieCategoryViewState by viewModel.upcomingMoviesHomeViewState.collectAsState()
    val nowPlayingMoviesViewState: HomeMovieCategoryViewState by viewModel.nowPlayingMoviesHomeViewState.collectAsState()
    val popularMoviesViewState: HomeMovieCategoryViewState by viewModel.popularMoviesHomeViewState.collectAsState()

    HomeScreen(
        popular = popularMoviesViewState,
        nowPlaying = nowPlayingMoviesViewState,
        upcoming = upcomingMoviesViewState,
        onMovieCardClick = onMovieCardClick,
        onFavoriteButtonClick = { viewModel.toggleFavorite(it) },
        onCategoryClick = { categoryId -> viewModel.changeCategory(categoryId) }
    )
}

@Composable
fun HomeScreen(
    popular: HomeMovieCategoryViewState,
    nowPlaying: HomeMovieCategoryViewState,
    upcoming: HomeMovieCategoryViewState,
    onMovieCardClick: (Int) -> Unit,
    onFavoriteButtonClick: (Int) -> Unit,
    onCategoryClick: (Int) -> Unit,
) {
    LazyColumn(
        modifier = Modifier.fillMaxHeight()
    ) {
        item {
            HomeScreenPart(
                viewState = popular,
                title = stringResource(id = R.string.whats_popular),
                modifier = Modifier,
                onMovieCardClick = onMovieCardClick,
                onFavoriteButtonClick = onFavoriteButtonClick,
                onCategoryClick = onCategoryClick
            )
            HomeScreenPart(
                viewState = nowPlaying,
                title = stringResource(id = R.string.now_playing),
                modifier = Modifier,
                onMovieCardClick = onMovieCardClick,
                onFavoriteButtonClick = onFavoriteButtonClick,
                onCategoryClick = onCategoryClick
            )
            HomeScreenPart(
                viewState = upcoming,
                title = stringResource(id = R.string.upcoming),
                modifier = Modifier,
                onMovieCardClick = onMovieCardClick,
                onFavoriteButtonClick = onFavoriteButtonClick,
                onCategoryClick = onCategoryClick
            )
        }
    }
}

@Composable
fun HomeScreenPart(
    viewState: HomeMovieCategoryViewState,
    title: String,
    onMovieCardClick: (Int) -> Unit,
    onFavoriteButtonClick: (Int) -> Unit,
    onCategoryClick: (Int) -> Unit,
    modifier: Modifier,
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = title,
            modifier = Modifier.padding(MaterialTheme.spacing.small),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        LazyRow(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            items(
                items = viewState.movieCategories,
                key = { category ->
                    category.itemId
                }
            ) {
                MovieCategoryLabel(
                    modifier = Modifier.padding(end = MaterialTheme.spacing.small),
                    labelViewState = it,
                    onClick = {
                        onCategoryClick(it.itemId)
                    }
                )
            }
        }
        LazyRow(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            items(
                items = viewState.movies,
                key = { movie ->
                    movie.id
                }
            ) { movie ->
                MovieCard(
                    modifier = Modifier
                        .width(dimensionResource(id = R.dimen.movie_card_width))
                        .height(dimensionResource(id = R.dimen.movie_card_height))
                        .padding(MaterialTheme.spacing.extraSmall),
                    movieCardViewState = MovieCardViewState(
                        movie.id,
                        movie.imageUrl,
                        movie.isFavorite
                    ),
                    onFavoriteButtonClick = { onFavoriteButtonClick(movie.id) },
                    onClick = { onMovieCardClick(movie.id) }
                )
            }
        }
    }
}
