package uz.star.mvispamples

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import uz.star.flow_mvi.FlowMVIScreen
import uz.star.mavericks.MavericksScreen
import uz.star.mvikotlin.MVIKotlinScreen
import uz.star.orbit.OrbitScreen
import uz.star.redux_kotlin.ReduxScreen

object InitialScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            ChooseLibText(title = "Mavericks") { navigator.push(MavericksScreen()) }
            ChooseLibText(title = "MVIKotlin") { navigator.push(MVIKotlinScreen()) }
            ChooseLibText(title = "Orbit Multiplatform") { navigator.push(OrbitScreen()) }
            ChooseLibText(title = "ReduxKotlin") { navigator.push(ReduxScreen()) }
            ChooseLibText(title = "FlowMVI") { navigator.push(FlowMVIScreen()) }
        }
    }

    @Composable
    fun ChooseLibText(title: String, onClick: () -> Unit) {
        Text(
            text = title,
            fontSize = 24.sp,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onClick.invoke() }
                .padding(8.dp)
        )
    }
}