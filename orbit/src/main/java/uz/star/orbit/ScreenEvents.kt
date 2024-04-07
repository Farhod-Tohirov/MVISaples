package uz.star.orbit

sealed interface ScreenEvents {
    data class ShowToast(val message: String) : ScreenEvents
}