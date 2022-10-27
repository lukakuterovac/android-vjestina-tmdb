package agency.five.codebase.android.movieapp.ui.component

import agency.five.codebase.android.movieapp.mock.MoviesMock
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
    Column{
        Text(
            text = crewItemViewState.name,
            fontSize = 10.sp,
            fontWeight = FontWeight.ExtraBold
        )
        Text(
            text = crewItemViewState.job,
            fontSize = 8.sp,
            fontWeight = FontWeight.W300,
        )
    }
}

@Preview (showBackground = true)
@Composable
private fun CrewItemViewState() {
    val crew = MoviesMock.getCrewman()
    val crewItemViewState = CrewItemViewState(
        name = crew.name,
        job = crew.job,
    )
    CrewItem(crewItemViewState = crewItemViewState)
}