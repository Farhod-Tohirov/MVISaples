package uz.star.mavericks

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.screen.Screen
import com.airbnb.mvrx.compose.collectAsState
import com.airbnb.mvrx.compose.mavericksViewModel
import uz.star.ui.LoaderDialog
import uz.star.ui.PostItem


class MavericksScreen : Screen {

    @Composable
    override fun Content() {
        val viewModel: ScreenViewModel = mavericksViewModel()

        val state by viewModel.collectAsState()

        LazyColumn {
            items(state.postList) {
                PostItem(
                    title = it.title,
                    subtitle = it.subtitle,
                    readCount = it.readCount,
                    onReadClick = { viewModel.onReadButtonClick(it) },
                    onClick = viewModel::onClickPost
                )
            }
        }

        if (state.isLoading) {
            LoaderDialog()
        }
    }
}