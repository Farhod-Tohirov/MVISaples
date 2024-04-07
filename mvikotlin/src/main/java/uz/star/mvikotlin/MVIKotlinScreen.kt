package uz.star.mvikotlin

import android.widget.Toast
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import cafe.adriel.voyager.core.screen.Screen
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import com.arkivanov.mvikotlin.logging.store.LoggingStoreFactory
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import uz.star.data.DataRepository
import uz.star.mvikotlin.models.Intent
import uz.star.mvikotlin.models.Label
import uz.star.ui.LoaderDialog
import uz.star.ui.PostItem


class MVIKotlinScreen : Screen {

    val store: ScreenStore = ScreenStoreImpl(LoggingStoreFactory(DefaultStoreFactory()), ScreenExecutor(DataRepository), ScreenReducer())

    @OptIn(ExperimentalCoroutinesApi::class)
    @Composable
    override fun Content() {
        LaunchedEffect(Unit) {
            store.accept(Intent.LoadPostList)
        }
        val state by store.stateFlow.collectAsState()
        val context = LocalContext.current
        LaunchedEffect(Unit) {
            store.labels.collect {
                when (it) {
                    is Label.ShowToast -> Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }

        LazyColumn {
            items(state.posts) {
                PostItem(
                    title = it.title,
                    subtitle = it.subtitle,
                    readCount = it.readCount,
                    onReadClick = { store.accept(Intent.ReadButtonClick(it)) },
                    onClick = { store.accept(Intent.PostClicked) }
                )
            }
        }

        if (state.isLoading) {
            LoaderDialog()
        }
    }
}