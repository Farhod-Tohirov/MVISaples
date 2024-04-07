package uz.star.redux_kotlin

import uz.star.data.PostData

data class ScreenState(
    val isLoading: Boolean = false,
    val posts: List<PostData> = emptyList()
)