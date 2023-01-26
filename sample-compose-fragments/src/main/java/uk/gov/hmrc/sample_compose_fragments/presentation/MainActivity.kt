package uk.gov.hmrc.sample_compose_fragments.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme
import uk.gov.hmrc.components.compose.ui.theme.HmrcWhiteBackground
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.MainScreen
import uk.gov.hmrc.sample_compose_fragments.presentation.ui.theme.AndroidcomponentsTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HmrcTheme {
                Surface(color = HmrcWhiteBackground) {
                    MainScreen()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AndroidcomponentsTheme {
        MainScreen()
    }
}