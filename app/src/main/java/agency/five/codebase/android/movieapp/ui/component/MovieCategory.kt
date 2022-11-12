package agency.five.codebase.android.movieapp.ui.component

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight.Companion.ExtraBold
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class MovieCategoryLabelViewState(
    val itemId: Int,
    val isSelected: Boolean,
    val categoryText: MovieCategoryLabelTextViewState
)

sealed class MovieCategoryLabelTextViewState {
    class InputText(val text: String) : MovieCategoryLabelTextViewState()
    class ResourceText(@StringRes val textRes: Int) : MovieCategoryLabelTextViewState()
}

@Composable
fun getTextSource(movieCategoryLabelViewState: MovieCategoryLabelViewState): String {
    return when (val categoryText = movieCategoryLabelViewState.categoryText) {
        is MovieCategoryLabelTextViewState.InputText ->
            categoryText.text
        is MovieCategoryLabelTextViewState.ResourceText ->
            stringResource(id = categoryText.textRes)
    }
}

@Composable
fun MovieCategoryLabel(
    labelViewState: MovieCategoryLabelViewState,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .clickable { onClick() }
            .padding(5.dp)
            .width(intrinsicSize = IntrinsicSize.Max)
    ) {
        if (labelViewState.isSelected) {
            Text(
                text = getTextSource(movieCategoryLabelViewState = labelViewState),
                style = TextStyle(textDecoration = TextDecoration.Underline),
                fontSize = 16.sp,
                fontWeight = ExtraBold,
            )
        } else {
            Text(
                text = getTextSource(movieCategoryLabelViewState = labelViewState),
                fontSize = 16.sp,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MovieCategoryLabelPreview() {
    val textFromString = MovieCategoryLabelTextViewState.InputText("Movies")
    val movieCategoryLabelViewState = MovieCategoryLabelViewState(1, false, textFromString)

    MovieCategoryLabel(
        labelViewState = movieCategoryLabelViewState,
        modifier = Modifier,
        onClick = {}
    )
}
