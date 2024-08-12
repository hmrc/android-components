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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SecondaryScrollableTabRow
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import uk.gov.hmrc.components.compose.atom.text.TabBarText
import uk.gov.hmrc.components.compose.ui.theme.HmrcBlack
import uk.gov.hmrc.components.compose.ui.theme.HmrcGrey1Dark
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
    var useScrollableTabRow: Boolean by rememberSaveable { mutableStateOf(true) }

    var baselineHeight by remember { mutableStateOf(0.dp) }

    Column(modifier = modifier) {
        if (useScrollableTabRow) {
            HmrcScrollableTabRow(tabItems, selectedIndex, onTabSelected, tabBarStyle) { scrollableTabRowHeight ->
                // If there's no baseline height, this will build the scrollable Tab Row and measure the height
                // of the row as the baseline. Once we have the baseline for scrollable Tab Row, we can compare it
                // to the non-scrollable Tab Row's height.
                if (baselineHeight == 0.dp) {
                    baselineHeight = scrollableTabRowHeight
                    useScrollableTabRow = false
                }
            }
        } else {
            HmrcTabRow(tabItems, selectedIndex, onTabSelected, tabBarStyle) { tabRowHeight ->
                // If the non-scrollable Tab Row's height exceeded the baseline, this means the text in the tab has
                // wrapped to create that extra height, therefore, meaning we should use the scrollable Tab Row.
                useScrollableTabRow = tabRowHeight > baselineHeight
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HmrcTabRow(
    tabItems: List<String>,
    selectedIndex: Int,
    onTabSelected: (Int) -> Unit,
    tabBarStyle: TabBarViewStyle,
    onMeasured: (Dp) -> Unit
) {
    var totalTabWidth by remember { mutableStateOf(0.dp) }

    SecondaryTabRow(
        selectedTabIndex = selectedIndex,
        modifier = Modifier
            .onGloballyPositioned { layoutCoordinates ->
                totalTabWidth = layoutCoordinates.size.height.dp
                onMeasured(totalTabWidth)
            },
        containerColor = if (tabBarStyle == TabBarViewStyle.LIGHT) HmrcTheme.colors.hmrcWhite else HmrcBlack,
        divider = {}, // Empty divider, we don't want the grey divider at the bottom of the Tab.
        indicator = {
            TabRowDefaults.SecondaryIndicator(
                Modifier
                    .tabIndicatorOffset(selectedIndex)
                    .height(HmrcTheme.dimensions.hmrcSpacing4),
                color = if (tabBarStyle == TabBarViewStyle.LIGHT) HmrcTheme.colors.hmrcBlue else HmrcWhite,
            )
        },
        tabs = { HmrcTabItems(tabItems, selectedIndex, onTabSelected, tabBarStyle) }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HmrcScrollableTabRow(
    tabItems: List<String>,
    selectedIndex: Int,
    onTabSelected: (Int) -> Unit,
    tabBarStyle: TabBarViewStyle,
    onMeasured: (Dp) -> Unit
) {
    var totalTabWidth by remember { mutableStateOf(0.dp) }

    SecondaryScrollableTabRow(
        selectedTabIndex = selectedIndex,
        modifier = Modifier
            .onGloballyPositioned { layoutCoordinates ->
                totalTabWidth = layoutCoordinates.size.height.dp
                onMeasured(totalTabWidth)
            },
        containerColor = if (tabBarStyle == TabBarViewStyle.LIGHT) HmrcTheme.colors.hmrcWhite else HmrcBlack,
        divider = {}, // Empty divider, we don't want the grey divider at the bottom of the Tab.
        edgePadding = 0.dp,
        indicator = { tabPositions ->
            TabRowDefaults.SecondaryIndicator(
                Modifier
                    .tabIndicatorOffset(tabPositions[selectedIndex])
                    .height(HmrcTheme.dimensions.hmrcSpacing4),
                color = if (tabBarStyle == TabBarViewStyle.LIGHT) HmrcTheme.colors.hmrcBlue else HmrcWhite,
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
                selectedContentColor = if (tabBarStyle == TabBarViewStyle.LIGHT) {
                    HmrcTheme.colors.hmrcBlue
                } else {
                    HmrcWhite
                },
                unselectedContentColor = if (tabBarStyle == TabBarViewStyle.LIGHT) {
                    HmrcTheme.colors.hmrcGrey1
                } else {
                    HmrcGrey1Dark
                }
            ) {
                TabBarText(
                    text = tabText,
                    modifier = Modifier.padding(HmrcTheme.dimensions.hmrcSpacing16),
                )
            }
        }
    }
}

enum class TabBarViewStyle {
    LIGHT,
    DARK,
}
