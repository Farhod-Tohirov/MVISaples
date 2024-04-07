package uz.star.flow_mvi

import android.widget.Toast
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import cafe.adriel.voyager.core.screen.Screen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import pro.respawn.flowmvi.compose.dsl.subscribe
import uz.star.ui.LoaderDialog
import uz.star.ui.PostItem

class FlowMVIScreen : Screen {

    val store = ScreenContainer().store

    @Composable
    override fun Content() {
        val context = LocalContext.current
        LaunchedEffect(Unit) {
            store.start(CoroutineScope(Dispatchers.Main))
        }

        val state by store.subscribe { action ->
            when (action) {
                is ScreenAction.ShowToast -> Toast.makeText(context, action.message, Toast.LENGTH_SHORT).show()
            }
        }

        LazyColumn {
            items(state.posts) {
                PostItem(
                    title = it.title,
                    subtitle = it.subtitle,
                    readCount = it.readCount,
                    onReadClick = { store.intent(ScreenIntent.ReadButtonClick(it)) },
                    onClick = { store.intent(ScreenIntent.PostClick) }
                )
            }
        }

        if (state.isLoading) {
            LoaderDialog()
        }
    }
}