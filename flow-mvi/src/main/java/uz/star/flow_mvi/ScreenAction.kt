package uz.star.flow_mvi

import pro.respawn.flowmvi.api.MVIAction

sealed interface ScreenAction : MVIAction {
    data class ShowToast(val message: String) : ScreenAction
}