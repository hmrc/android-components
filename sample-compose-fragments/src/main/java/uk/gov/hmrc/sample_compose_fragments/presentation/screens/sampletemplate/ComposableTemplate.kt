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
package uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import uk.gov.hmrc.components.compose.atom.heading.Heading5
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme.colors
import uk.gov.hmrc.sample_compose_components.R
import uk.gov.hmrc.sample_compose_fragments.presentation.extension.addPlaceholderModifier

@Composable
fun HmrcSurface(content: @Composable () -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        color = colors.hmrcPageBackground,
        content = content
    )
}

@Composable
fun ScreenScrollViewColumn(content: @Composable ColumnScope.() -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(HmrcTheme.dimensions.hmrcSpacing16),
        content = content
    )
}

@Composable
fun PlaceholderSlot(showHeading: Boolean = true, content: @Composable () -> Unit) {
    if (showHeading) {
        Heading5(
            text = stringResource(id = R.string.heading_placeholder_plural),
            modifier = Modifier.padding(bottom = HmrcTheme.dimensions.hmrcSpacing16)
        )
    }

    Box(modifier = Modifier.addPlaceholderModifier()) {
        content()
    }
    Spacer(modifier = Modifier.padding(bottom = HmrcTheme.dimensions.hmrcSpacing16))
}

@Composable
fun ExamplesSlot(content: @Composable () -> Unit) {
    Heading5(
        text = stringResource(id = R.string.heading_example_plural),
        modifier = Modifier.padding(bottom = HmrcTheme.dimensions.hmrcSpacing16)
    )
    content()
}

@Composable
fun HMRCPreview(content: @Composable () -> Unit) {
    HmrcTheme {
        HmrcSurface(
            content = content
        )
    }
}