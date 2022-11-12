package agency.five.codebase.android.movieapp.ui.component

import agency.five.codebase.android.movieapp.mock.MoviesMock
import agency.five.codebase.android.movieapp.ui.theme.Typography
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

data class CrewItemViewState(
    val name: String,
    val job: String,
)

@Composable
fun CrewItem(
    crewItemViewState: CrewItemViewState,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
    )
    {
        Text(
            text = crewItemViewState.name,
            style = Typography.h3,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
        Text(
            text = crewItemViewState.job,
            fontSize = 8.sp,
            fontWeight = FontWeight.W300,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CrewItemViewState() {
    val crew = MoviesMock.getCrewman()
    val crewItemViewState = CrewItemViewState(
        name = crew.name,
        job = crew.job,
    )
    CrewItem(crewItemViewState = crewItemViewState)
}
