package uk.gov.hmrc.components.sample.compose.screens

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme
import uk.gov.hmrc.components.sample.compose.navigation.ComponentsBottomNavigation
import uk.gov.hmrc.components.sample.compose.navigation.ComponentsNavGraph
import uk.gov.hmrc.components.sample.compose.navigation.NavigationConstants.NAV_ARG_TOP_APP_BAR_TITLE

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        topBar = { ComponentsTopBar(navController) },
        bottomBar = { ComponentsBottomNavigation(navController) },
    ) {
        ComponentsNavGraph(navController)
    }
}

@Composable
fun ComponentsTopBar(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val title = navBackStackEntry?.arguments?.getInt(NAV_ARG_TOP_APP_BAR_TITLE)
    TopAppBar(
        title = { Text(text = title?.let { stringResource(id = title) } ?: "") },
        backgroundColor = HmrcTheme.colors.hmrcAlwaysBlack,
        contentColor = HmrcTheme.colors.hmrcAlwaysWhite
    )
}
