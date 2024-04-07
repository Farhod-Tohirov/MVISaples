package uz.star.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PostItem(
    title: String,
    subtitle: String,
    readCount: Int,
    onReadClick: () -> Unit,
    onClick: () -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick.invoke() }
            .padding(horizontal = 16.dp)
    ) {
        Column {
            Text(text = title, fontSize = 18.sp)
            Text(text = subtitle, fontSize = 14.sp)
        }
        Spacer(Modifier.weight(1f))
        Text(text = readCount.toString(), fontSize = 18.sp)
        IconButton(onClick = onReadClick) {
            Icon(imageVector = Icons.Default.CheckCircle, contentDescription = "Read")
        }
    }
}