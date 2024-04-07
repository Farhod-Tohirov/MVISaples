package uz.star.flow_mvi

import pro.respawn.flowmvi.api.MVIState
import uz.star.data.PostData

data class ScreenState(
    val isLoading: Boolean = false,
    val posts: List<PostData> = emptyList()
) : MVIState