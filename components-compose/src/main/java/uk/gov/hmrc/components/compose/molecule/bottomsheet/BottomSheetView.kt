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
package uk.gov.hmrc.components.compose.molecule.bottomsheet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme.colors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetView(
    modifier: Modifier = Modifier,
    showBottomSheet: Boolean,
    skipPartiallyExpanded: Boolean = false,
    enableFullScreenExpansion: Boolean = false,
    onDismissRequest: () -> Unit,
    sheetContent: @Composable ColumnScope.() -> Unit,
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = skipPartiallyExpanded)
    val scope = rememberCoroutineScope()

    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = onDismissRequest,
            sheetState = sheetState,
            containerColor = colors.hmrcWhiteBackground,
            modifier = modifier,
        ) {
            Row(
                Modifier.fillMaxWidth().padding(horizontal = HmrcTheme.dimensions.hmrcSpacing16),
                horizontalArrangement = Arrangement.End
            ) {
                FilledIconButton(
                    colors = IconButtonDefaults.filledIconButtonColors(containerColor = colors.hmrcGrey3),
                    onClick = {
                    scope
                        .launch { sheetState.hide() }
                        .invokeOnCompletion { if (!sheetState.isVisible) { onDismissRequest() } }
                }) {
                    Icon(Icons.Default.Close, contentDescription = "Close sheet", tint = colors.hmrcGrey2)
                }
            }

            if (enableFullScreenExpansion) {
                Column(Modifier
                    .padding(
                        start = HmrcTheme.dimensions.hmrcSpacing16,
                        end = HmrcTheme.dimensions.hmrcSpacing16,
                        bottom = 48.dp
                    )
                    .fillMaxHeight()
                    .verticalScroll(rememberScrollState())
                ) {
                    sheetContent()
                }
            } else {
                Column(Modifier
                    .padding(
                        start = HmrcTheme.dimensions.hmrcSpacing16,
                        end = HmrcTheme.dimensions.hmrcSpacing16,
                        bottom = 48.dp
                    )
                    .verticalScroll(rememberScrollState())
                ) {
                    sheetContent()
                }
            }
        }
    }
}
