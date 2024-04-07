package uz.star.orbit

import android.widget.Toast
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import cafe.adriel.voyager.core.screen.Screen
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect
import uz.star.ui.LoaderDialog
import uz.star.ui.PostItem

class OrbitScreen : Screen {

    @Composable
    override fun Content() {
        val viewModel: OrbitViewModel = viewModel()
        val state by viewModel.collectAsState()
        val context = LocalContext.current

        viewModel.collectSideEffect {
            when (it) {
                is ScreenEvents.ShowToast -> Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
            }
        }

        LazyColumn {
            items(state.posts) {
                PostItem(
                    title = it.title,
                    subtitle = it.subtitle,
                    readCount = it.readCount,
                    onReadClick = { viewModel.onPostReadClick(it) },
                    onClick = { viewModel.showToast() }
                )
            }
        }

        if (state.isLoading) {
            LoaderDialog()
        }
    }
}