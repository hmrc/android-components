/*
 * Copyright 2023 HM Revenue & Customs
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
package uk.gov.hmrc.sample_compose_fragments.presentation.screens.molecules

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import uk.gov.hmrc.components.compose.atom.heading.Heading5
import uk.gov.hmrc.components.compose.molecule.tabbar.TabBarView
import uk.gov.hmrc.components.compose.molecule.tabbar.TabBarViewStyle
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme
import uk.gov.hmrc.sample_compose_components.R

@Composable
fun TabBarViewScreen() {
    var lightSelectedTabIndex by remember { mutableStateOf(0) }
    val lightTabItems = listOf("Light", "Style", "Tabs")

    var darkSelectedTabIndex by remember { mutableStateOf(0) }
    val darkTabItems = listOf("Dark", "Style", "Tabs")

    var longSelectedTabIndex by remember { mutableStateOf(0) }
    val longTabItems = listOf("When fixed width breaks", "options align to left", "and component becomes", "scrollable")

    // Not following the usual pattern with ExamplesSlot/ScreenScrollViewColumn,
    // because unlike other components, we want to demo TabBarView with the full length of the screen.
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
        content = {
            Heading5(
                text = stringResource(id = R.string.heading_example_plural),
                modifier = Modifier.padding(HmrcTheme.dimensions.hmrcSpacing16),
            )

            TabBarView(
                tabItems = lightTabItems,
                selectedIndex = lightSelectedTabIndex,
                onTabSelected = { index ->
                    lightSelectedTabIndex = index
                    // Handle onTabSelected here
                },
                modifier = Modifier.padding(bottom = HmrcTheme.dimensions.hmrcSpacing16)
            )

            TabBarView(
                tabItems = darkTabItems,
                selectedIndex = darkSelectedTabIndex,
                onTabSelected = { index ->
                    darkSelectedTabIndex = index
                    // Handle onTabSelected here
                },
                modifier = Modifier.padding(bottom = HmrcTheme.dimensions.hmrcSpacing16),
                tabBarStyle = TabBarViewStyle.DARK
            )

            TabBarView(
                tabItems = longTabItems,
                selectedIndex = longSelectedTabIndex,
                onTabSelected = { index ->
                    longSelectedTabIndex = index
                    // Handle onTabSelected here
                },
                modifier = Modifier.padding(bottom = HmrcTheme.dimensions.hmrcSpacing16),
            )
        }
    )
}