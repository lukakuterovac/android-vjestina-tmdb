package agency.five.codebase.android.movieapp.ui.moviedetails

import agency.five.codebase.android.movieapp.R
import agency.five.codebase.android.movieapp.mock.MoviesMock
import agency.five.codebase.android.movieapp.ui.component.*
import agency.five.codebase.android.movieapp.ui.moviedetails.mapper.MovieDetailsMapper
import agency.five.codebase.android.movieapp.ui.moviedetails.mapper.MovieDetailsMapperImpl
import agency.five.codebase.android.movieapp.ui.theme.MovieAppTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

private val detailsMapper: MovieDetailsMapper = MovieDetailsMapperImpl()

val movieDetailsViewState = detailsMapper.toMovieDetailsViewState(MoviesMock.getMovieDetails())

@Composable
fun MovieDetailsRoute(
) {
    val movieDetailsViewState by remember { mutableStateOf(movieDetailsViewState) }
    MovieDetailsScreen(
        movieDetailsViewState = movieDetailsViewState,
        modifier = Modifier
    )
}

@Composable
fun MovieDetailsScreen(
    modifier: Modifier,
    movieDetailsViewState: MovieDetailsViewState
) {
    Column() {
        LazyColumn(
            modifier = modifier.fillMaxWidth()
        ) {
            item {
                Box(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    AsyncImage(
                        model = movieDetailsViewState.imageUrl,
                        contentDescription = null,
                        modifier = Modifier.height(350.dp),
                        contentScale = ContentScale.FillWidth
                    )
                    Text(
                        text = movieDetailsViewState.title,
                        modifier = Modifier
                            .padding(horizontal = 10.dp, vertical = 15.dp)
                            .background(
                                Color.Gray.copy(0.75f),
                                shape = RoundedCornerShape(10.dp)
                            )
                            .align(Alignment.BottomStart),
                        fontSize = 20.sp
                    )
                    UserScoreProgressBar(
                        score = movieDetailsViewState.voteAverage,
                        modifier = Modifier
                            .padding(10.dp)
                            .background(
                                Color.Gray.copy(0.75f),
                                shape = CircleShape
                            )
                            .size(50.dp)
                            .align(Alignment.BottomEnd)
                    )
                    FavoriteButton(
                        modifier = Modifier
                            .padding(10.dp)
                            .background(
                                Color.Gray.copy(0.75f),
                                shape = CircleShape
                            )
                            .size(40.dp)
                            .align(Alignment.TopStart),
                        isFavorite = movieDetailsViewState.isFavorite,
                        onClick = {}
                    )
                }
            }
            item {
                Text(
                    text = stringResource(id = R.string.overview),
                    modifier = Modifier.padding(5.dp),
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            item {
                Text(
                    text = movieDetailsViewState.overview,
                    modifier = Modifier.padding(5.dp),
                    fontSize = 15.sp
                )
            }
            item {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    modifier = Modifier.height(100.dp)
                ) {
                    items(movieDetailsViewState.crew) { crewItem ->
                        CrewItem(
                            modifier = Modifier.padding(10.dp),
                            crewItemViewState = CrewItemViewState(
                                id = crewItem.id,
                                name = crewItem.name,
                                job = crewItem.job
                            )
                        )
                    }
                }
            }
            item {
                Text(
                    text = stringResource(id = R.string.top_billed_cast),
                    modifier = Modifier.padding(5.dp),
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            item {
                LazyRow {
                    itemsIndexed(movieDetailsViewState.cast) { index, _ ->
                        ActorCard(
                            actorCardViewState = movieDetailsViewState.cast[index],
                            modifier = Modifier
                                .width(140.dp)
                                .height(200.dp)
                                .padding(5.dp)
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun MovieDetailsScreenPreview() {
    MovieAppTheme {
        MovieDetailsScreen(
            modifier = Modifier,
            movieDetailsViewState = movieDetailsViewState
        )
    }
}
