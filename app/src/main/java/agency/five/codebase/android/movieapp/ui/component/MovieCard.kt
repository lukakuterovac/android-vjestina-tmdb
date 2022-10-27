package agency.five.codebase.android.movieapp.ui.component

import agency.five.codebase.android.movieapp.mock.MoviesMock
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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

data class MovieCardViewState(
    val imageUrl: String?,
    val isFavorite: Boolean,
)

@Composable
fun MovieCard(
    movieCardViewState: MovieCardViewState,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = Modifier
            .width(100.dp)
            .height(150.dp)
            .padding(5.dp),
        shape = RoundedCornerShape(10.dp),
        elevation = 10.dp,
    ) {
        Box {
            AsyncImage(
                model = movieCardViewState.imageUrl,
                contentDescription = movieCardViewState.imageUrl,
                modifier = Modifier.height(150.dp),
                contentScale = ContentScale.Crop,
            )
            FavoriteButton(
                isFavorite = movieCardViewState.isFavorite,
                modifier = modifier
                    .padding(start = 5.dp, top = 5.dp)
                    .align(alignment = Alignment.TopStart)
                    .alpha(0.5F)
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
    MovieCard(movieCardViewState = movieCardViewState)
}