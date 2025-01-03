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
package uk.gov.hmrc.sample_compose_fragments.presentation.screens.colors

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import uk.gov.hmrc.components.compose.organism.HmrcCardView
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme.colors
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme.typography
import uk.gov.hmrc.components.compose.ui.theme.HmrcAllDevicePreview
import uk.gov.hmrc.sample_compose_fragments.data.model.ColorItem
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.HmrcPreview
import uk.gov.hmrc.sample_compose_fragments.presentation.viewModel.ColorsViewModel

@Composable
fun ColorsListScreen(viewModel: ColorsViewModel) {
    val listItems by viewModel.colorItems.collectAsStateWithLifecycle()
    ColorsListScreen(listItems)
}

@Composable
fun ColorsListScreen(
    listItems: List<ColorItem>,
    listState: LazyListState = rememberLazyListState()
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(1F),
        state = listState,
        contentPadding = PaddingValues(
            top = HmrcTheme.dimensions.hmrcSpacing8,
            bottom = HmrcTheme.dimensions.hmrcSpacing8
        )
    ) {
        items(listItems) { item ->
            ListItem(item = item)
        }
    }
}

@Composable
fun ListItem(item: ColorItem) {
    HmrcCardView(modifier = Modifier.padding(HmrcTheme.dimensions.hmrcSpacing16, HmrcTheme.dimensions.hmrcSpacing8)) {
        Row(modifier = Modifier.padding(HmrcTheme.dimensions.hmrcSpacing16)) {
            Box(
                modifier = Modifier
                    .heightIn(min = 42.dp)
                    .widthIn(min = 42.dp)
                    .border(BorderStroke(1.dp, colors.hmrcBlack))
                    .background(item.color)
                    .align(alignment = Alignment.CenterVertically),
            )
            Text(
                modifier = Modifier
                    .padding(start = HmrcTheme.dimensions.hmrcSpacing16)
                    .align(alignment = Alignment.CenterVertically),
                text = "${item.colorName} (${item.color.hexToString()})",
                style = typography.h5
            )
        }
    }
}

private fun Color.hexToString(): String = String.format("#%08X", toArgb())

@HmrcAllDevicePreview
@Composable
fun ColorsListScreenPreview() {
    HmrcPreview {
        ColorsListScreen(listItems = ColorItem.values().asList())
    }
}
