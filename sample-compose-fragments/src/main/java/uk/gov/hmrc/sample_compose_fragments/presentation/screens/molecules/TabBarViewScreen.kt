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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import uk.gov.hmrc.components.compose.atom.heading.Heading5
import uk.gov.hmrc.components.compose.molecule.tabbar.TabBarView
import uk.gov.hmrc.components.compose.molecule.tabbar.TabBarViewStyle
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme
import uk.gov.hmrc.components.compose.ui.theme.HmrcAllDevicePreview
import uk.gov.hmrc.sample_compose_components.R
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.HMRCPreview
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.HmrcSurface

@Composable
fun TabBarViewScreen() {
    var lightSelectedTabIndex by remember { mutableIntStateOf(0) }
    val lightTabItems = listOf(
        stringResource(id = R.string.tab_bar_view_light_tab_item_1),
        stringResource(id = R.string.tab_bar_view_light_tab_item_2),
        stringResource(id = R.string.tab_bar_view_light_tab_item_3),
    )

    var darkSelectedTabIndex by remember { mutableIntStateOf(0) }
    val darkTabItems = listOf(
        stringResource(id = R.string.tab_bar_view_dark_tab_item_1),
        stringResource(id = R.string.tab_bar_view_dark_tab_item_2),
        stringResource(id = R.string.tab_bar_view_dark_tab_item_3),
    )

    var longSelectedTabIndex by remember { mutableIntStateOf(0) }
    val longTabItems = listOf(
        stringResource(id = R.string.tab_bar_view_long_tab_item_1),
        stringResource(id = R.string.tab_bar_view_long_tab_item_2),
        stringResource(id = R.string.tab_bar_view_long_tab_item_3),
        stringResource(id = R.string.tab_bar_view_long_tab_item_4),
    )

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

@HmrcAllDevicePreview
@Composable
internal fun TabBarViewScreenPreview() {
    HMRCPreview {
        TabBarViewScreen()
    }
}
