package uz.star.flow_mvi

import pro.respawn.flowmvi.dsl.store
import pro.respawn.flowmvi.plugins.enableLogging
import pro.respawn.flowmvi.plugins.init
import pro.respawn.flowmvi.plugins.reduce
import uz.star.data.DataRepository

class ScreenContainer {
    private val repository = DataRepository
    val store = store<ScreenState, ScreenIntent, ScreenAction>(ScreenState()) {
        enableLogging()

        init {
            updateState { copy(isLoading = true) }
            val posts = repository.getPostList()
            updateState { copy(isLoading = false, posts = posts) }
        }

        reduce { intent ->
            when (intent) {
                ScreenIntent.PostClick -> action(ScreenAction.ShowToast("Show toast"))
                is ScreenIntent.ReadButtonClick -> {
                    updateState {
                        val posts = posts.toMutableList().apply {
                            val post = find { it.id == intent.postData.id } ?: return@apply
                            this[this.indexOf(post)] = post.copy(readCount = post.readCount.plus(1))
                        }
                        copy(posts = posts)
                    }
                }
            }
        }
    }
}