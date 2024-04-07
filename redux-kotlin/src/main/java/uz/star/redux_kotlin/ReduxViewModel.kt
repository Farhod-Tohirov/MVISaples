package uz.star.redux_kotlin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.reduxkotlin.Reducer
import org.reduxkotlin.applyMiddleware
import org.reduxkotlin.createStore
import org.reduxkotlin.middleware
import org.reduxkotlin.typedReducer
import uz.star.data.DataRepository

class ReduxViewModel : ViewModel() {

    private val repository = DataRepository

    private val reducer: Reducer<ScreenState> = typedReducer<ScreenState, ScreenEvents> { state, action ->
        when (action) {
            is ScreenEvents.ReadButtonClick -> {
                val posts = state.posts.toMutableList().apply {
                    val post = find { it.id == action.postData.id } ?: return@apply
                    this[this.indexOf(post)] = post.copy(readCount = post.readCount.plus(1))
                }
                state.copy(posts = posts)
            }

            is ScreenEvents.DataSuccess -> {
                state.copy(isLoading = false, posts = action.posts)
            }

            is ScreenEvents.ShowLoader -> {
                state.copy(isLoading = true)
            }
        }
    }

    private val middleWare = middleware<ScreenState> { store, next, action ->
        when (action) {
            is ActionTypes.Init -> store.dispatch(loadPosts())
            is ActionTypes.LoadedPosts -> store.dispatch(ScreenEvents.DataSuccess(action.posts))
            else -> next(action)
        }
    }

    private fun loadPosts() {
        viewModelScope.launch {
            store.dispatch(ScreenEvents.ShowLoader)
            val posts = repository.getPostList()
            store.dispatch(ActionTypes.LoadedPosts(posts))
        }
    }

    val store = createStore(
        reducer = reducer,
        preloadedState = ScreenState(),
        enhancer = applyMiddleware(middleWare)
    )

    init {
        store.dispatch(ActionTypes.Init)
    }
}