package agency.five.codebase.android.movieapp.ui.component

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

sealed class MovieCategoryLabelTextViewState
class MovieCategoryLabelTextViewStateInput(val text: String): MovieCategoryLabelTextViewState()
class MovieCategoryLabelTextViewStateResource(@StringRes val textRes: Int): MovieCategoryLabelTextViewState()

data class MovieCategoryLabelViewState(
    val itemId: Int,
    val isSelected: Boolean,
    val categoryText: MovieCategoryLabelTextViewState
)

@Composable
fun MovieCategoryLabel(
    movieCategoryLabelViewState: MovieCategoryLabelViewState
){
    Column(
        modifier = Modifier
            .padding(5.dp)
            .width(intrinsicSize = IntrinsicSize.Max)
    ) {
        var isSelectedState by remember { mutableStateOf(movieCategoryLabelViewState.isSelected) }
        Text(
            text = when (movieCategoryLabelViewState.categoryText)
            {
                is MovieCategoryLabelTextViewStateInput -> movieCategoryLabelViewState.categoryText.text
                is MovieCategoryLabelTextViewStateResource -> stringResource(id = movieCategoryLabelViewState.categoryText.textRes)
            },
            color = Color.Black,
            fontWeight = if(isSelectedState) FontWeight.ExtraBold else FontWeight.W300,
            fontSize = 15.sp,
            modifier = Modifier.clickable{
                isSelectedState = isSelectedState.not()
            }
        )
        if(isSelectedState){
            Divider(
                color = Color.Black,
                thickness = 3.dp,
                modifier = Modifier.padding(top = 2.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MovieCategoryLabelPreview(){
    MovieCategoryLabel(
        movieCategoryLabelViewState = MovieCategoryLabelViewState(
            itemId = 0,
            isSelected = false,
            categoryText = MovieCategoryLabelTextViewStateInput(text="Movies")
        )
    )
}