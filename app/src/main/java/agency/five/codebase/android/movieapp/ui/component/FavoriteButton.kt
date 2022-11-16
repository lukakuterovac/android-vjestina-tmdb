package agency.five.codebase.android.movieapp.ui.component

import agency.five.codebase.android.movieapp.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun FavoriteButton(
    isFavorite: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Image(
        painter = painterResource(id = if (isFavorite) R.drawable.ic_favorite_filled else R.drawable.ic_favorite),
        contentDescription = null,
        modifier = modifier
            .clickable { onClick() }
            .size(32.dp)
    )
}

@Preview(showBackground = true)
@Composable
private fun FavoriteButtonPreview() {
    val favoriteButtonModifier = Modifier.padding(5.dp)
    val isFavoriteState = remember { mutableStateOf(false) }
    FavoriteButton(
        isFavorite = isFavoriteState.value,
        modifier = favoriteButtonModifier,
        onClick = { isFavoriteState.value = !(isFavoriteState.value) }
    )
}
