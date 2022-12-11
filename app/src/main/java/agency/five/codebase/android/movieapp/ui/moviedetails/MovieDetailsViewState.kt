package agency.five.codebase.android.movieapp.ui.moviedetails

import agency.five.codebase.android.movieapp.ui.component.ActorCardViewState
import agency.five.codebase.android.movieapp.ui.component.CrewItemViewState
import java.util.Collections.emptyList


data class MovieDetailsViewState(
    val id: Int,
    val imageUrl: String?,
    val voteAverage: Float,
    val title: String,
    val overview: String,
    val isFavorite: Boolean,
    val crew: List<CrewItemViewState>,
    val cast: List<ActorCardViewState>,
) {
    companion object {
        val EMPTY: MovieDetailsViewState = MovieDetailsViewState(
            id = 1,
            imageUrl = null,
            voteAverage = 0.0f,
            title = "",
            overview = "",
            isFavorite = false,
            crew = emptyList(),
            cast = emptyList()
        )
    }
}
