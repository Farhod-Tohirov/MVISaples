package uz.star.redux_kotlin

import uz.star.data.PostData

sealed interface ScreenEvents {
    data class DataSuccess(val posts: List<PostData>) : ScreenEvents
    data object ShowLoader: ScreenEvents
    data class ReadButtonClick(val postData: PostData) : ScreenEvents
}