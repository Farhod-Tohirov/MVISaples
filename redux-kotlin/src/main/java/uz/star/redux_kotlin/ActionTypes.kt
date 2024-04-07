package uz.star.redux_kotlin

import uz.star.data.PostData

sealed interface ActionTypes {
    data object Init : ActionTypes
    data class LoadedPosts(val posts: List<PostData>) : ActionTypes
}