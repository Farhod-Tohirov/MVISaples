package uz.star.orbit

import androidx.lifecycle.ViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import uz.star.data.DataRepository
import uz.star.data.PostData

class OrbitViewModel : ContainerHost<ScreenState, ScreenEvents>, ViewModel() {

    override val container = container<ScreenState, ScreenEvents>(ScreenState())
    private val repository = DataRepository

    init {
        loadPosts()
    }

    private fun loadPosts() = intent {
        reduce {
            state.copy(isLoading = true)
        }
        val posts = repository.getPostList()
        reduce {
            state.copy(isLoading = false, posts = posts)
        }
    }

    fun onPostReadClick(postData: PostData) = intent {
        reduce {
            val posts = state.posts.toMutableList().apply {
                val post = find { it.id == postData.id } ?: return@apply
                this[this.indexOf(post)] = post.copy(readCount = post.readCount.plus(1))
            }
            state.copy(posts = posts)
        }
    }

    fun showToast() = intent {
        postSideEffect(ScreenEvents.ShowToast("Toast appeared"))
    }
}