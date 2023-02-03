package uk.gov.hmrc.sample_compose_fragments.presentation.screens

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

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import uk.gov.hmrc.components.compose.ui.theme.HmrcBlack
import uk.gov.hmrc.components.compose.ui.theme.HmrcGrey3
import uk.gov.hmrc.components.compose.ui.theme.HmrcWhite
import uk.gov.hmrc.components.compose.ui.theme.hmrc_spacing_16
import uk.gov.hmrc.components.compose.ui.theme.hmrc_spacing_8
import uk.gov.hmrc.sample_compose_fragments.domain.model.ColorItem
import uk.gov.hmrc.sample_compose_fragments.presentation.viewModel.ColorsViewModel

@Composable
fun ColorsListScreen(viewModel: ColorsViewModel) {
    DisplayList(viewModel)
}

@Composable
fun DisplayList(viewModel: ColorsViewModel) {
    val listItems by viewModel.colorItems.collectAsState()
    val listState = rememberLazyListState()

    LazyColumn(
        modifier = Modifier.fillMaxSize(1F).background(HmrcGrey3), state = listState
    ) {
        items(listItems) { item ->
            ListItem(item = item)
        }
    }
}

@Composable
fun ListItem(item: ColorItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth().padding(hmrc_spacing_16, hmrc_spacing_8),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        colors = CardDefaults.cardColors(containerColor = HmrcWhite,), shape = RoundedCornerShape(0)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(hmrc_spacing_16)) {
            Box(
                modifier = Modifier
                    .heightIn(min = 40.dp).widthIn(min = 40.dp).border(BorderStroke(1.dp, HmrcBlack))
                    .background(item.color).align(alignment = Alignment.CenterVertically),
            )
            Text(
                modifier = Modifier
                    .padding(horizontal = hmrc_spacing_16).align(alignment = Alignment.CenterVertically),
                text = item.colorName, color = HmrcBlack, textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold
            )
        }
    }
}