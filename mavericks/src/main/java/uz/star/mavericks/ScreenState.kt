package uz.star.mavericks

import com.airbnb.mvrx.MavericksState
import uz.star.data.PostData

data class ScreenState(
    val isLoading: Boolean = false,
    val postList: List<PostData> = emptyList()
) : MavericksState