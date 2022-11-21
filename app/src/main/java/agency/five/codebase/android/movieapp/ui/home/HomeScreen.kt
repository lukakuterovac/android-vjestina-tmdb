package agency.five.codebase.android.movieapp.ui.home

import agency.five.codebase.android.movieapp.mock.MoviesMock
import agency.five.codebase.android.movieapp.model.MovieCategory
import agency.five.codebase.android.movieapp.ui.home.mapper.HomeScreenMapper
import agency.five.codebase.android.movieapp.ui.home.mapper.HomeScreenMapperImpl
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import agency.five.codebase.android.movieapp.R
import agency.five.codebase.android.movieapp.ui.component.*
import androidx.compose.runtime.*

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
