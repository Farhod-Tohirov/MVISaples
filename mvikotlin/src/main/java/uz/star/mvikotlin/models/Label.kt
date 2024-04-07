package uz.star.mvikotlin.models

sealed interface Label {
    data class ShowToast(val message: String) : Label
}