package uz.star.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog

@Composable
private fun LoaderView(modifier: Modifier = Modifier) = Box(modifier.fillMaxWidth()) {
    CircularProgressIndicator(
        modifier = Modifier.align(Alignment.Center),
    )
}

@Composable
fun LoaderDialog(onDismissRequest: () -> Unit = {}) {
    Dialog(onDismissRequest) {
        LoaderView()
    }
}