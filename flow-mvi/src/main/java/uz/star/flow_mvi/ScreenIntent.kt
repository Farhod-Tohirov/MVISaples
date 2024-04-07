package uz.star.flow_mvi

import pro.respawn.flowmvi.api.MVIIntent
import uz.star.data.PostData


sealed interface ScreenIntent : MVIIntent {
    data class ReadButtonClick(val postData: PostData) : ScreenIntent
    data object PostClick : ScreenIntent
}