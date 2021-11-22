package uk.gov.hmrc.components.sample.compose.screens

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import uk.gov.hmrc.components.sample.compose.navigation.NavigationScreen

@Composable
fun ComponentListScreen(screen: NavigationScreen) {
    Text(stringResource(screen.resourceId))
}
