package pl.kkapps.railways.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import dagger.hilt.android.AndroidEntryPoint
import pl.kkapps.railways.ui.home.AppScreen
import pl.kkapps.railways.ui.theme.AppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                Surface {
                    AppScreen()
                }
            }
        }
    }
}