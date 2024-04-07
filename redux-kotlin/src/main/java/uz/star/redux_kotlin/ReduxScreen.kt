package uz.star.redux_kotlin

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import cafe.adriel.voyager.core.screen.Screen
import org.reduxkotlin.compose.StoreProvider
import org.reduxkotlin.compose.rememberDispatcher
import org.reduxkotlin.compose.selectState
import uz.star.ui.LoaderDialog
import uz.star.ui.PostItem

class ReduxScreen : Screen {

    @Composable
    override fun Content() {
        val viewModel: ReduxViewModel = viewModel()
        StoreProvider(viewModel.store) {
            val isLoading by selectState { isLoading }
            val posts by selectState { posts }
            val dispatch = rememberDispatcher()

            LazyColumn {
                items(posts) {
                    PostItem(
                        title = it.title,
                        subtitle = it.subtitle,
                        readCount = it.readCount,
                        onReadClick = { dispatch(ScreenEvents.ReadButtonClick(it)) },
                        onClick = {  }
                    )
                }
            }

            if (isLoading) {
                LoaderDialog()
            }
        }
    }
}