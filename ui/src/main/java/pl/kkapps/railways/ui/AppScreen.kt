package pl.kkapps.railways.ui

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import com.airbnb.mvrx.compose.collectAsState
import com.airbnb.mvrx.compose.mavericksViewModel
import pl.kkapps.railways.ui.theme.AppTheme

@Composable
fun AppScreen(viewModel: AppScreenViewModel = mavericksViewModel()) {
    val state by viewModel.collectAsState()
}

@Composable
fun ScreenContent(state: AppScreenState) {
}

@Preview
@Composable
private fun AppContentPreview() {
    AppTheme {
        Surface {
            ScreenContent(
                state = AppScreenState()
            )
        }
    }
}