package agency.five.codebase.android.movieapp.ui.home

import agency.five.codebase.android.movieapp.R
import agency.five.codebase.android.movieapp.mock.MoviesMock
import agency.five.codebase.android.movieapp.model.MovieCategory
import agency.five.codebase.android.movieapp.ui.component.MovieCard
import agency.five.codebase.android.movieapp.ui.component.MovieCardViewState
import agency.five.codebase.android.movieapp.ui.component.MovieCategoryLabel
import agency.five.codebase.android.movieapp.ui.home.mapper.HomeScreenMapper
import agency.five.codebase.android.movieapp.ui.home.mapper.HomeScreenMapperImpl
import agency.five.codebase.android.movieapp.ui.theme.spacing
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val homeScreenMapper: HomeScreenMapper = HomeScreenMapperImpl()

val movies = MoviesMock.getMoviesList()
val popular = listOf(
    MovieCategory.POPULAR_STREAMING,
    MovieCategory.POPULAR_ON_TV,
    MovieCategory.POPULAR_FOR_RENT,
    MovieCategory.POPULAR_IN_THEATRES
)
val nowPlaying = listOf(
    MovieCategory.NOW_PLAYING_MOVIES,
    MovieCategory.NOW_PLAYING_TV
)
val upcoming = listOf(
    MovieCategory.UPCOMING_TODAY,
    MovieCategory.UPCOMING_THIS_WEEK
)

val popularCategoryViewState = homeScreenMapper.toHomeMovieCategoryViewState(
    movies = movies,
    movieCategories = popular,
    selectedMovieCategory = MovieCategory.POPULAR_STREAMING
)
val nowPlayingCategoryViewState = homeScreenMapper.toHomeMovieCategoryViewState(
    movies = movies,
    movieCategories = nowPlaying,
    selectedMovieCategory = MovieCategory.NOW_PLAYING_MOVIES
)
val upcomingCategoryViewState = homeScreenMapper.toHomeMovieCategoryViewState(
    movies = movies,
    movieCategories = upcoming,
    selectedMovieCategory = MovieCategory.UPCOMING_TODAY
)

@Composable
fun HomeRoute(
    onMovieCardClick: () -> Unit,
    onFavoriteButtonClick: () -> Unit,
) {
    var popularCategoryViewState by remember { mutableStateOf(popularCategoryViewState) }
    var nowPlayingCategoryViewState by remember { mutableStateOf(nowPlayingCategoryViewState) }
    var upcomingCategoryViewState by remember { mutableStateOf(upcomingCategoryViewState) }

    HomeScreen(
        popular = popularCategoryViewState,
        nowPlaying = nowPlayingCategoryViewState,
        upcoming = upcomingCategoryViewState,
        onMovieCardClick = onMovieCardClick,
        onFavoriteButtonClick = onFavoriteButtonClick,
        onCategoryClick = { categoryId ->
            when (categoryId) {
                MovieCategory.POPULAR_STREAMING.ordinal,
                MovieCategory.POPULAR_FOR_RENT.ordinal,
                MovieCategory.POPULAR_ON_TV.ordinal,
                MovieCategory.POPULAR_IN_THEATRES.ordinal -> {
                    popularCategoryViewState = mapToViewState(popular, categoryId)
                }
                MovieCategory.NOW_PLAYING_MOVIES.ordinal,
                MovieCategory.NOW_PLAYING_TV.ordinal -> {
                    nowPlayingCategoryViewState = mapToViewState(nowPlaying, categoryId)
                }
                MovieCategory.UPCOMING_TODAY.ordinal,
                MovieCategory.UPCOMING_THIS_WEEK.ordinal -> {
                    upcomingCategoryViewState = mapToViewState(upcoming, categoryId)
                }
            }
        }
    )
}

@Composable
fun HomeScreen(
    popular: HomeMovieCategoryViewState,
    nowPlaying: HomeMovieCategoryViewState,
    upcoming: HomeMovieCategoryViewState,
    onMovieCardClick: () -> Unit,
    onFavoriteButtonClick: () -> Unit,
    onCategoryClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxHeight()
    ) {
        item {
            HomeScreenPart(
                viewState = popular,
                title = stringResource(id = R.string.whats_popular),
                modifier = Modifier,
                onMovieCardClick = { onMovieCardClick() },
                onFavoriteButtonClick = { onFavoriteButtonClick() },
                onCategoryClick = onCategoryClick
            )
            HomeScreenPart(
                viewState = nowPlaying,
                title = stringResource(id = R.string.now_playing),
                modifier = Modifier,
                onMovieCardClick = { onMovieCardClick() },
                onFavoriteButtonClick = { onFavoriteButtonClick() },
                onCategoryClick = onCategoryClick
            )
            HomeScreenPart(
                viewState = upcoming,
                title = stringResource(id = R.string.upcoming),
                modifier = Modifier,
                onMovieCardClick = { onMovieCardClick() },
                onFavoriteButtonClick = { onFavoriteButtonClick() },
                onCategoryClick = onCategoryClick
            )
        }
    }
}

@Composable
fun HomeScreenPart(
    viewState: HomeMovieCategoryViewState,
    title: String,
    onMovieCardClick: () -> Unit,
    onFavoriteButtonClick: () -> Unit,
    onCategoryClick: (Int) -> Unit,
    modifier: Modifier,
) {
    Column(
        modifier = modifier.padding(MaterialTheme.spacing.small)
    ) {
        Text(
            text = title,
            modifier = Modifier.padding(MaterialTheme.spacing.small),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = MaterialTheme.spacing.small),
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
                .fillMaxWidth()
                .padding(MaterialTheme.spacing.small),
        ) {
            items(
                items = viewState.movies,
                key = { movie ->
                    movie.id
                }
            ) { item ->
                MovieCard(
                    modifier = Modifier
                        .width(dimensionResource(id = R.dimen.movie_card_width))
                        .height(dimensionResource(id = R.dimen.movie_card_height))
                        .padding(MaterialTheme.spacing.extraSmall),
                    movieCardViewState = MovieCardViewState(
                        item.imageUrl,
                        item.isFavorite
                    ),
                    onFavoriteButtonClick = onFavoriteButtonClick,
                    onClick = onMovieCardClick
                )
            }
        }
    }
}

private fun mapToViewState(
    categoryList: List<MovieCategory>,
    categoryId: Int
): HomeMovieCategoryViewState {
    return homeScreenMapper.toHomeMovieCategoryViewState(
        movieCategories = categoryList,
        selectedMovieCategory = MovieCategory.values()[categoryId],
        movies = MoviesMock.getMoviesList()
    )
}

@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen(
        popular = popularCategoryViewState,
        nowPlaying = nowPlayingCategoryViewState,
        upcoming = upcomingCategoryViewState,
        onMovieCardClick = { },
        onFavoriteButtonClick = { },
        onCategoryClick = { }
    )
}
