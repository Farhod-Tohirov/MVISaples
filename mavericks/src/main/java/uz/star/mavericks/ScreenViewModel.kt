package uz.star.mavericks

import com.airbnb.mvrx.MavericksViewModel
import uz.star.data.DataRepository
import uz.star.data.PostData

class ScreenViewModel : MavericksViewModel<ScreenState>(ScreenState()) {

    init {
        loadPosts()
    }

    private fun loadPosts() = withState { state ->
        setState { copy(isLoading = true) }
        suspend {
            DataRepository.getPostList()
        }.execute { async ->
            async()?.let { copy(isLoading = false, postList = it) } ?: state
        }
    }

    fun onReadButtonClick(postData: PostData) = setState { copy() }

    fun onClickPost() {}
}