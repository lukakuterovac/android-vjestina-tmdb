package agency.five.codebase.android.movieapp.ui.component

import agency.five.codebase.android.movieapp.R
import agency.five.codebase.android.movieapp.mock.MoviesMock
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun FavoriteButton(
    modifier: Modifier = Modifier,
    isFavorite: Boolean,
){
    var isFavoriteState by remember { mutableStateOf(isFavorite) }
    Image(
        painter = painterResource(id = if (isFavoriteState) R.drawable.ic_favorite_filled else R.drawable.ic_favorite),
        contentDescription = null,
        modifier = modifier
            .clickable {
                isFavoriteState = isFavoriteState.not()
            }
            .size(20.dp)
            .background(Blue, CircleShape)
            .clip(CircleShape)
            .padding(3.dp)
    )
}

@Preview(showBackground = true)
@Composable
private fun FavoriteButtonPreview() {
    val movieDetails = MoviesMock.getMovieDetails()
    FavoriteButton(isFavorite = movieDetails.movie.isFavorite)
}