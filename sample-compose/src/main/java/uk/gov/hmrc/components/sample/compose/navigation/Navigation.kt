/*
 * Copyright 2021 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package uk.gov.hmrc.components.sample.compose.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
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
import androidx.navigation.navArgument
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme
import uk.gov.hmrc.components.sample.compose.R
import uk.gov.hmrc.components.sample.compose.navigation.NavigationConstants.NAV_ARG_TOP_APP_BAR_TITLE
import uk.gov.hmrc.components.sample.compose.screens.ComponentListScreen

object NavigationConstants {
    const val NAV_ARG_TOP_APP_BAR_TITLE = "NAV_ARG_TOP_APP_BAR_TITLE"
}

sealed class NavigationScreen(val route: String, @StringRes val resourceId: Int, @DrawableRes val icon: Int) {
    object Organisms : NavigationScreen("organisms", R.string.title_organisms, R.drawable.ic_organisms)
    object Molecules : NavigationScreen("molecules", R.string.title_molecules, R.drawable.ic_molecules)
    object Atoms : NavigationScreen("atoms", R.string.title_atoms, R.drawable.ic_atoms)
    object Colors : NavigationScreen("colors", R.string.title_colors, R.drawable.ic_colors)
}

@Composable
fun ComponentsBottomNavigation(navController: NavHostController) {
    BottomNavigation(backgroundColor = HmrcTheme.colors.hmrcWhiteBackground) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        val navigationItems = listOf(
            NavigationScreen.Organisms,
            NavigationScreen.Molecules,
            NavigationScreen.Atoms,
            NavigationScreen.Colors
        )

        navigationItems.forEach { screen ->
            BottomNavigationItem(
                unselectedContentColor = HmrcTheme.colors.hmrcGrey1,
                selectedContentColor = HmrcTheme.colors.hmrcBlue,
                icon = {
                    Icon(
                        painter = painterResource(id = screen.icon),
                        contentDescription = null,
                    )
                },
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

@Composable
fun ComponentsNavGraph(navController: NavHostController, modifier: Modifier) {
    NavHost(navController, startDestination = NavigationScreen.Organisms.route, modifier) {
        composable(
            NavigationScreen.Organisms.route,
            arguments = listOf(
                navArgument(NAV_ARG_TOP_APP_BAR_TITLE) { defaultValue = NavigationScreen.Organisms.resourceId }
            )
        ) { ComponentListScreen(NavigationScreen.Organisms) }

        composable(
            NavigationScreen.Molecules.route,
            arguments = listOf(
                navArgument(NAV_ARG_TOP_APP_BAR_TITLE) { defaultValue = NavigationScreen.Molecules.resourceId }
            )
        ) { ComponentListScreen(NavigationScreen.Molecules) }

        composable(
            NavigationScreen.Atoms.route,
            arguments = listOf(
                navArgument(NAV_ARG_TOP_APP_BAR_TITLE) { defaultValue = NavigationScreen.Atoms.resourceId }
            )
        ) { ComponentListScreen(NavigationScreen.Atoms) }
        composable(
            NavigationScreen.Colors.route,
            arguments = listOf(
                navArgument(NAV_ARG_TOP_APP_BAR_TITLE) { defaultValue = NavigationScreen.Colors.resourceId }
            )
        ) { ComponentListScreen(NavigationScreen.Colors) }
    }
}
