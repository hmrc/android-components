package uk.gov.hmrc.components.sample.compose.screens

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import uk.gov.hmrc.components.sample.compose.R

@Composable
fun MainScreen() {

    val navController = rememberNavController()

    val navigationItems = listOf(
        NavigationScreen.Organisms,
        NavigationScreen.Molecules,
        NavigationScreen.Atoms,
        NavigationScreen.Colors,
    )

    Scaffold(
        bottomBar = { ComponentsBottomNavigation(navController, navigationItems) },
    ) { innerPadding ->
        NavHost(navController, startDestination = NavigationScreen.Organisms.route, Modifier.padding(innerPadding)) {
            composable(NavigationScreen.Organisms.route) { OrganismsScreen() }
            composable(NavigationScreen.Molecules.route) { OrganismsScreen() }
            composable(NavigationScreen.Atoms.route) { OrganismsScreen() }
            composable(NavigationScreen.Colors.route) { OrganismsScreen() }
        }
    }
}

@Composable
fun ComponentsBottomNavigation(navController: NavHostController, navigationItems: List<NavigationScreen>) {
    BottomNavigation {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        navigationItems.forEach { screen ->
            BottomNavigationItem(
                icon = { Icon(painter = painterResource(id = screen.icon), contentDescription = null) },
                label = { Text(stringResource(screen.resourceId)) },
                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

sealed class NavigationScreen(val route: String, @StringRes val resourceId: Int, @DrawableRes val icon: Int) {
    object Organisms : NavigationScreen("organisms", R.string.title_organisms, R.drawable.ic_organisms)
    object Molecules : NavigationScreen("molecules", R.string.title_molecules, R.drawable.ic_molecules)
    object Atoms : NavigationScreen("atoms", R.string.title_atoms, R.drawable.ic_atoms)
    object Colors : NavigationScreen("colors", R.string.title_colors, R.drawable.ic_colors)
}
