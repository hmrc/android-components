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
package uk.gov.hmrc.components.sample.compose.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme
import uk.gov.hmrc.components.sample.compose.navigation.ComponentsBottomNavigation
import uk.gov.hmrc.components.sample.compose.navigation.ComponentsNavGraph
import uk.gov.hmrc.components.sample.compose.navigation.NavigationConstants.NAV_ARG_TOP_APP_BAR_TITLE

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        topBar = { ComponentsTopBar(navController) },
        bottomBar = { ComponentsBottomNavigation(navController) },
    ) { innerPadding ->
        ComponentsNavGraph(navController, Modifier.padding(innerPadding))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComponentsTopBar(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val title = navBackStackEntry?.arguments?.getInt(NAV_ARG_TOP_APP_BAR_TITLE)
    TopAppBar(
        title = { Text(text = title?.let { stringResource(id = title) } ?: "") },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = HmrcTheme.colors.hmrcAlwaysBlack,
            titleContentColor = HmrcTheme.colors.hmrcAlwaysWhite
        )
    )
}
