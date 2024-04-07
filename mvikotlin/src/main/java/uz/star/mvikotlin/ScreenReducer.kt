package uz.star.mvikotlin

import com.arkivanov.mvikotlin.core.store.Reducer
import uz.star.mvikotlin.models.Message

internal class ScreenReducer : Reducer<ScreenState, Message> {
    override fun ScreenState.reduce(msg: Message): ScreenState {
        return when (msg) {
            is Message.ReadButtonClicked -> copy(posts = msg.postList)
            is Message.PostsLoaded -> copy(isLoading = false, posts = msg.postList)
            is Message.ShowLoader -> copy(isLoading = true)
        }
    }
}