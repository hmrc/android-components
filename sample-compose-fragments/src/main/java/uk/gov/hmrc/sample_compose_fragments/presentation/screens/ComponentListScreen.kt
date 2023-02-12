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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme
import uk.gov.hmrc.components.compose.ui.theme.HmrcTypography
import uk.gov.hmrc.components.compose.ui.theme.hmrc_spacing_16
import uk.gov.hmrc.components.compose.ui.theme.hmrc_spacing_8

@Composable
fun <T : ComponentItem> ComponentListScreen(items: List<T>, navigateTo: (item: T) -> Unit) {
    val listState = rememberLazyListState()

    LazyColumn(
        modifier = Modifier.fillMaxSize(1F), state = listState,
        contentPadding = PaddingValues(top = hmrc_spacing_8, bottom = hmrc_spacing_8)
    ) {
        items(items) { item ->
            ComponentListItem(item = item, navigateTo)
        }
    }
}

@Composable
fun <T : ComponentItem> ComponentListItem(item: T, navigateTo: (item: T) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth().padding(hmrc_spacing_16, hmrc_spacing_8).clickable(onClick = { navigateTo(item) }),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),shape = RoundedCornerShape(0),
        colors = CardDefaults.cardColors(containerColor = HmrcTheme.colors.hmrcWhiteBackground)
    ) {
        Row(
            modifier = Modifier.padding(hmrc_spacing_16).align(alignment = Alignment.CenterHorizontally)
        ) {
            Text(
                modifier = Modifier.padding(start = hmrc_spacing_16).align(alignment = Alignment.CenterVertically),
                text = item.title, style = HmrcTypography.typography.h5
            )
        }
    }
}

interface ComponentItem {
    val title: String
}