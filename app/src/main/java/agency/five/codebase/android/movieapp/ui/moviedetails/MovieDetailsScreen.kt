package agency.five.codebase.android.movieapp.ui.moviedetails

import agency.five.codebase.android.movieapp.R
import agency.five.codebase.android.movieapp.ui.component.ActorCard
import agency.five.codebase.android.movieapp.ui.component.CrewItem
import agency.five.codebase.android.movieapp.ui.component.FavoriteButton
import agency.five.codebase.android.movieapp.ui.component.UserScoreProgressBar
import agency.five.codebase.android.movieapp.ui.theme.MovieAppTheme
import agency.five.codebase.android.movieapp.ui.theme.spacing
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun MovieDetailsRoute(
    viewModel: MovieDetailsViewModel
) {
    val movieDetailsViewState: MovieDetailsViewState by viewModel.movieDetailsViewState.collectAsState()
    MovieDetailsScreen(
        movieDetailsViewState = movieDetailsViewState,
        onFavoriteButtonClick = {
            viewModel.toggleFavorite(movieDetailsViewState.id)
        },
        modifier = Modifier
    )
}

@Composable
fun MovieDetailsScreen(
    movieDetailsViewState: MovieDetailsViewState,
    onFavoriteButtonClick: () -> Unit,
    modifier: Modifier,
) {
    Column(
        modifier = modifier.verticalScroll(
            state = rememberScrollState(),
            enabled = true
        )
    ) {
        MovieImage(
            movieDetailsViewState = movieDetailsViewState,
            onFavoriteButtonClick = onFavoriteButtonClick,
            modifier = Modifier.height(dimensionResource(id = R.dimen.movie_details_image_height))
        )
        MovieOverview(
            movieDetailsViewState = movieDetailsViewState,
            modifier = Modifier
        )
        MovieCrewGrid(
            movieDetailsViewState = movieDetailsViewState,
            modifier = Modifier.height(dimensionResource(id = R.dimen.movie_details_crew_grid_height))
        )
        MovieCast(
            movieDetailsViewState = movieDetailsViewState,
        )
    }
}

private const val BACKGROUND_TRANSPARENCY = 0.75F

@Composable
fun MovieImage(
    movieDetailsViewState: MovieDetailsViewState,
    onFavoriteButtonClick: () -> Unit,
    modifier: Modifier
) {
    Box(
        modifier = modifier
    ) {
        AsyncImage(
            model = movieDetailsViewState.imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.BottomStart)
        ) {
            Spacer(modifier = Modifier.weight(0.5F))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                UserScoreProgressBar(
                    score = movieDetailsViewState.voteAverage,
                    modifier = Modifier
                        .padding(MaterialTheme.spacing.small)
                        .size(50.dp)
                )
                Text(
                    text = stringResource(id = R.string.user_rating),
                    modifier = Modifier,
                    color = colorResource(id = R.color.white),
                    fontSize = 20.sp
                )
            }
            Text(
                text = movieDetailsViewState.title,
                modifier = Modifier
                    .padding(
                        horizontal = MaterialTheme.spacing.small,
                        vertical = MaterialTheme.spacing.medium
                    ),
                color = colorResource(id = R.color.white),
                fontSize = 20.sp
            )
            FavoriteButton(
                modifier = Modifier
                    .padding(MaterialTheme.spacing.medium)
                    .background(
                        Color.Gray.copy(BACKGROUND_TRANSPARENCY),
                        shape = CircleShape
                    )
                    .size(40.dp),
                isFavorite = movieDetailsViewState.isFavorite,
                onClick = onFavoriteButtonClick
            )
        }
    }
}

@Composable
fun MovieOverview(
    movieDetailsViewState: MovieDetailsViewState,
    modifier: Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(id = R.string.overview),
            modifier = Modifier.padding(MaterialTheme.spacing.extraSmall),
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = movieDetailsViewState.overview,
            modifier = Modifier.padding(MaterialTheme.spacing.extraSmall),
            fontSize = 15.sp
        )
    }
}

@Composable
fun MovieCrewGrid(
    movieDetailsViewState: MovieDetailsViewState,
    modifier: Modifier
) {
    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
        modifier = modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceBetween,
        contentPadding = PaddingValues(MaterialTheme.spacing.medium)
    ) {
        items(
            items = movieDetailsViewState.crew,
            key = { crewman ->
                crewman.hashCode()
            }) { crewman ->
            CrewItem(crewItemViewState = crewman)
        }
    }
}

@Composable
fun MovieCast(
    movieDetailsViewState: MovieDetailsViewState,
) {
    Text(
        text = stringResource(id = R.string.top_billed_cast),
        modifier = Modifier.padding(MaterialTheme.spacing.extraSmall),
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold
    )
    LazyRow {
        itemsIndexed(movieDetailsViewState.cast) { index, _ ->
            ActorCard(
                actorCardViewState = movieDetailsViewState.cast[index],
                modifier = Modifier
                    .width(dimensionResource(id = R.dimen.actor_card_wdith))
                    .height(dimensionResource(id = R.dimen.actor_card_height))
                    .padding(MaterialTheme.spacing.extraSmall)
            )
        }
    }
}

@Preview
@Composable
fun MovieDetailsScreenPreview() {
    MovieAppTheme {
        MovieDetailsScreen(
            movieDetailsViewState = MovieDetailsViewState.EMPTY,
            onFavoriteButtonClick = { },
            modifier = Modifier
        )
    }
}
