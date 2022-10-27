package agency.five.codebase.android.movieapp.ui.component

import agency.five.codebase.android.movieapp.mock.MoviesMock
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

data class ActorCardViewState(
    val imageUrl: String?,
    val name: String,
    val character: String,
)
@Composable
fun ActorCard(
    actorCardViewState: ActorCardViewState,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .width(100.dp)
            .height(150.dp)
            .padding(5.dp),
        shape = RoundedCornerShape(10.dp),
        elevation = 10.dp,
    ) {
        Column{
            AsyncImage(
                model = actorCardViewState.imageUrl,
                contentDescription = actorCardViewState.name,
                modifier = modifier.height(100.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = actorCardViewState.name,
                modifier = modifier
                    .padding(3.dp)
                    .width(100.dp),
                fontSize = 10.sp,
                color = Color.Black,
                fontWeight = FontWeight.ExtraBold,

            )
            Text(
                text = actorCardViewState.character,
                modifier = modifier
                    .padding(3.dp)
                    .width(100.dp),
                fontSize = 8.sp,
                fontWeight = FontWeight.W300,
            )
        }
    }
}

@Preview (showBackground = true)
@Composable
private fun ActorCardPreview() {
    val actor = MoviesMock.getActor()
    val actorCardViewState = ActorCardViewState(
        name = actor.name,
        imageUrl = actor.imageUrl,
        character = actor.character,
    )
    ActorCard(actorCardViewState = actorCardViewState)
}