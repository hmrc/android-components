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
package uk.gov.hmrc.components.compose.molecule.tabbar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SecondaryScrollableTabRow
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import uk.gov.hmrc.components.compose.ui.theme.HmrcBlack
import uk.gov.hmrc.components.compose.ui.theme.HmrcBlue
import uk.gov.hmrc.components.compose.ui.theme.HmrcGrey1
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme
import uk.gov.hmrc.components.compose.ui.theme.HmrcWhite

@Composable
fun TabBarView(
    tabItems: List<String>,
    selectedIndex: Int,
    onTabSelected: (Int) -> Unit,
    modifier: Modifier = Modifier,
    tabBarStyle: TabBarViewStyle = TabBarViewStyle.LIGHT,
) {

    // Getting the screen's width in DP.
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    // Getting each of the tab's width in DP.
    val tabWidths = tabItems.map { tabText ->
        (tabText.length * HmrcTheme.typography.tabBarText.fontSize.value).dp
    }

    // Sum of all tab's width.
    val totalTabWidth = remember(tabWidths) {
        tabWidths.sumOf { it.value.toInt() }.dp
    }

    Column(modifier = modifier) {
        if (totalTabWidth > screenWidth) {
            HmrcScrollableTabRow(tabItems, selectedIndex, onTabSelected, tabBarStyle)
        } else {
            HmrcTabRow(tabItems, selectedIndex, onTabSelected, tabBarStyle)
        }
    }
}

@Composable
private fun HmrcTabRow(
    tabItems: List<String>,
    selectedIndex: Int,
    onTabSelected: (Int) -> Unit,
    tabBarStyle: TabBarViewStyle,
) {
    SecondaryTabRow(
        selectedTabIndex = selectedIndex,
        containerColor = tabBarStyle.backgroundColour,
        divider = {}, // Empty divider, we don't want the grey divider at the bottom of the Tab.
        indicator = { tabPositions ->
            TabRowDefaults.SecondaryIndicator(
                Modifier
                    .tabIndicatorOffset(tabPositions[selectedIndex])
                    .height(HmrcTheme.dimensions.hmrcSpacing4),
                color = tabBarStyle.selectedTextColour
            )
        },
        tabs = { HmrcTabItems(tabItems, selectedIndex, onTabSelected, tabBarStyle) }
    )
}

@Composable
private fun HmrcScrollableTabRow(
    tabItems: List<String>,
    selectedIndex: Int,
    onTabSelected: (Int) -> Unit,
    tabBarStyle: TabBarViewStyle,
) {
    SecondaryScrollableTabRow(
        selectedTabIndex = selectedIndex,
        containerColor = tabBarStyle.backgroundColour,
        divider = {}, // Empty divider, we don't want the grey divider at the bottom of the Tab.
        edgePadding = 0.dp,
        indicator = { tabPositions ->
            TabRowDefaults.SecondaryIndicator(
                Modifier
                    .tabIndicatorOffset(tabPositions[selectedIndex])
                    .height(HmrcTheme.dimensions.hmrcSpacing4),
                color = tabBarStyle.selectedTextColour
            )
        },
        tabs = { HmrcTabItems(tabItems, selectedIndex, onTabSelected, tabBarStyle) }
    )
}

@Composable
private fun HmrcTabItems(
    tabItems: List<String>,
    selectedIndex: Int,
    onTabSelected: (Int) -> Unit,
    tabBarStyle: TabBarViewStyle,
) {
    run {
        tabItems.forEachIndexed { index, tabText ->
            Tab(
                selected = index == selectedIndex,
                onClick = { onTabSelected(index) },
                selectedContentColor = tabBarStyle.selectedTextColour,
                unselectedContentColor = HmrcGrey1
            ) {
                Text(
                    text = tabText,
                    style = HmrcTheme.typography.tabBarText,
                    modifier = Modifier.padding(HmrcTheme.dimensions.hmrcSpacing16),
                )
            }
        }
    }
}

enum class TabBarViewStyle(val backgroundColour: Color, val selectedTextColour: Color) {
    LIGHT(backgroundColour = HmrcWhite, selectedTextColour = HmrcBlue),
    DARK(backgroundColour = HmrcBlack, selectedTextColour = HmrcWhite),
}
