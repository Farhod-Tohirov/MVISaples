package uz.star.mvikotlin.models

import uz.star.data.PostData

sealed interface Message {
    data class ReadButtonClicked(val postList: List<PostData>) : Message
    data class PostsLoaded(val postList: List<PostData>) : Message
    data object ShowLoader: Message
}