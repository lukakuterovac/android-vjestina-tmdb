package agency.five.codebase.android.movieapp.ui.component

import agency.five.codebase.android.movieapp.mock.MoviesMock
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

fun onMovieCardClick() {
    TODO("Not yet implemented")
}

fun onFavoriteButtonCardClick() {
    TODO("Not yet implemented")
}

data class MovieCardViewState(
    val imageUrl: String?,
    val isFavorite: Boolean,
)

@Composable
fun MovieCard(
    movieCardViewState: MovieCardViewState,
    modifier: Modifier = Modifier,
    onFavoriteButtonClick: () -> Unit,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier.clickable { onClick() },
        shape = RoundedCornerShape(10.dp),
        elevation = 10.dp,
    ) {
        Box {
            AsyncImage(
                model = movieCardViewState.imageUrl,
                contentDescription = movieCardViewState.imageUrl,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
            )
            FavoriteButton(
                isFavorite = movieCardViewState.isFavorite,
                modifier = Modifier
                    .size(32.dp)
                    .padding(10.dp)
                    .align(Alignment.TopStart),
                onClick = { onFavoriteButtonClick() }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MovieCardPreview() {
    val movieDetails = MoviesMock.getMovieDetails()
    val movieCardViewState = MovieCardViewState(
        imageUrl = movieDetails.movie.imageUrl,
        isFavorite = movieDetails.movie.isFavorite,
    )
    val movieCardModifier = Modifier
        .padding(5.dp)
        .size(
            width = 100.dp,
            height = 150.dp
        )
    MovieCard(
        movieCardViewState = movieCardViewState,
        modifier = movieCardModifier,
        onClick = { onMovieCardClick() },
        onFavoriteButtonClick = { onFavoriteButtonCardClick() }
    )
}
