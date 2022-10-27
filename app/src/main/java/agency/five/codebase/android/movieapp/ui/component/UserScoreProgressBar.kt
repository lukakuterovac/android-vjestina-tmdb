package agency.five.codebase.android.movieapp.ui.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun UserScoreProgressBar (
    score: Float,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = Modifier
            .size(50.dp)
            .padding(2.dp)
    ) {
        Canvas(
            modifier = Modifier.size(50.dp)
        ) {
            drawArc(
                Color(red=212, green =255,blue=212),
                startAngle = (score*360)-90,
                sweepAngle = (1-score)*360,
                style=Stroke(
                    width = 8F
                ),
                useCenter=false
            )
            drawArc(
                Color(red=0, green =255,blue=0),
                startAngle = -90F,
                sweepAngle = score*360,
                style= Stroke(
                    width = 8F
                ),
                useCenter=false)
        }
        Text(
            text = (score * 10).toString(),
            color = Color.Black,
            fontSize = 15.sp,
            modifier = Modifier
                .align(Alignment.Center)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun UserScoreProgressBarPreview(){
    UserScoreProgressBar(
        score = 0.75F
    )
}
