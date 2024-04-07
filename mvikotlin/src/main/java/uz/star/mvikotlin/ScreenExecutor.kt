package uz.star.mvikotlin

import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import kotlinx.coroutines.launch
import uz.star.data.DataRepository
import uz.star.mvikotlin.models.Intent
import uz.star.mvikotlin.models.Label
import uz.star.mvikotlin.models.Message

class ScreenExecutor(private val repository: DataRepository) : CoroutineExecutor<Intent, Nothing, ScreenState, Message, Label>() {

    override fun executeIntent(intent: Intent, getState: () -> ScreenState) {
        val state = getState()
        when (intent) {
            is Intent.ReadButtonClick -> {
                scope.launch {
                    val posts = state.posts.toMutableList().apply {
                        val post = find { it.id == intent.postData.id } ?: return@apply
                        this[this.indexOf(post)] = post.copy(readCount = post.readCount.plus(1))
                    }
                    dispatch(Message.ReadButtonClicked(posts))
                }
            }

            is Intent.PostClicked -> {
                publish(Label.ShowToast("Clicked Post"))
            }

            is Intent.LoadPostList -> {
                scope.launch {
                    dispatch(Message.ShowLoader)
                    val posts = repository.getPostList()
                    dispatch(Message.PostsLoaded(posts))
                }
            }
        }
    }
}