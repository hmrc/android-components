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
package uk.gov.hmrc.sample_compose_fragments.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import uk.gov.hmrc.components.compose.organism.HmrcCardView
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme.typography

@Composable
fun <T : ComponentItem> ComponentListScreen(items: List<T>, navigateTo: (item: T) -> Unit) {
    val listState = rememberLazyListState()

    LazyColumn(
        modifier = Modifier.fillMaxSize(1F),
        state = listState,
        contentPadding = PaddingValues(top = HmrcTheme.dimensions.hmrcSpacing8, bottom = HmrcTheme.dimensions.hmrcSpacing8)
    ) {
        items(items) { item ->
            ComponentListItem(item = item, navigateTo)
        }
    }
}

@Composable
fun <T : ComponentItem> ComponentListItem(item: T, navigateTo: (item: T) -> Unit) {
    HmrcCardView(
        modifier = Modifier
            .padding(HmrcTheme.dimensions.hmrcSpacing16, HmrcTheme.dimensions.hmrcSpacing8)
            .fillMaxWidth()
            .clickable(onClick = { navigateTo(item) }),
    ) {
        Row(modifier = Modifier.padding(HmrcTheme.dimensions.hmrcSpacing16).align(alignment = Alignment.CenterHorizontally)) {
            Text(
                modifier = Modifier.padding(start = HmrcTheme.dimensions.hmrcSpacing16).align(alignment = Alignment.CenterVertically),
                text = stringResource(item.titleRes),
                style = typography.h5
            )
        }
    }
}

interface ComponentItem {
    val titleRes: Int
}