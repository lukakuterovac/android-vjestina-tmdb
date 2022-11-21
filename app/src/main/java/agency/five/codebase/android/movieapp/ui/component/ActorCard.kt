package agency.five.codebase.android.movieapp.ui.component

import agency.five.codebase.android.movieapp.mock.MoviesMock
import agency.five.codebase.android.movieapp.ui.theme.Typography
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

data class ActorCardViewState(
    val id: Int,
    val imageUrl: String?,
    val name: String,
    val character: String,
)

@Composable
fun ActorCard(
    actorCardViewState: ActorCardViewState,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(10.dp),
        elevation = 10.dp,
    ) {
        Column {
            AsyncImage(
                model = actorCardViewState.imageUrl,
                contentDescription = actorCardViewState.name,
                modifier = Modifier.weight(0.7F),
                contentScale = ContentScale.Crop,
            )
            Text(
                text = actorCardViewState.name,
                modifier = Modifier
                    .padding(
                        start = 5.dp,
                        top = 3.dp,
                        end = 10.dp
                    ),
                style = Typography.h3,
            )
            Text(
                text = actorCardViewState.character,
                modifier = Modifier
                    .padding(
                        start = 5.dp,
                        top = 3.dp,
                        bottom = 3.dp
                    ),
                fontSize = 8.sp,
                fontWeight = FontWeight.W300,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ActorCardPreview() {
    val actor = MoviesMock.getActor()
    val actorCardViewState = ActorCardViewState(
        id = actor.id,
        name = actor.name,
        imageUrl = actor.imageUrl,
        character = actor.character,
    )
    ActorCard(
        actorCardViewState = actorCardViewState,
        modifier = Modifier.size(
            width = 100.dp,
            height = 150.dp
        )
    )
}
