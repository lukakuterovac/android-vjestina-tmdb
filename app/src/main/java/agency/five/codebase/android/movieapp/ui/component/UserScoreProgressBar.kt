package agency.five.codebase.android.movieapp.ui.component

import agency.five.codebase.android.movieapp.ui.theme.GreenProgressBar
import agency.five.codebase.android.movieapp.ui.theme.GreenProgressBarBackground
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private const val DEGREES_IN_CIRCLE = 360f
private const val PERCENTAGE_FACTOR = 10f

@Composable
fun UserScoreProgressBar(
    score: Float,
    modifier: Modifier = Modifier
        .size(50.dp)
        .padding(5.dp),
) {
    Box(
        modifier = modifier
    ) {
        Canvas(
            modifier = Modifier.fillMaxSize()
        ) {
            drawArc(
                color = GreenProgressBarBackground,
                startAngle = (score * DEGREES_IN_CIRCLE) - 90,
                sweepAngle = (1 - score) * DEGREES_IN_CIRCLE,
                style = Stroke(
                    width = 8f,
                ),
                useCenter = false
            )
            drawArc(
                color = GreenProgressBar,
                startAngle = -90F,
                sweepAngle = score * DEGREES_IN_CIRCLE,
                style = Stroke(
                    width = 8f
                ),
                useCenter = false
            )
        }
        Text(
            text = (score * PERCENTAGE_FACTOR).toString(),
            color = Color.Black,
            fontSize = 15.sp,
            modifier = Modifier
                .align(Alignment.Center)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun UserScoreProgressBarPreview() {
    UserScoreProgressBar(
        score = 0.75F
    )
}
