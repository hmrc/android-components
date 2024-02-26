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
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import uk.gov.hmrc.components.compose.R
import uk.gov.hmrc.components.compose.molecule.bottomsheet.BottomSheetView.SheetBottomTokens.DockedDragHandleColor
import uk.gov.hmrc.components.compose.molecule.bottomsheet.BottomSheetView.SheetBottomTokens.DockedDragHandleHeight
import uk.gov.hmrc.components.compose.molecule.bottomsheet.BottomSheetView.SheetBottomTokens.DockedDragHandleWidth
import uk.gov.hmrc.components.compose.molecule.bottomsheet.BottomSheetView.SheetBottomTokens.DragHandleVerticalPadding
import uk.gov.hmrc.components.compose.ui.theme.HmrcGrey2
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme.colors

object BottomSheetView {

    @OptIn(ExperimentalMaterial3Api::class)
    @Suppress("LongMethod")
    @Composable
    operator fun invoke(
        modifier: Modifier = Modifier,
        showBottomSheet: Boolean,
        skipPartiallyExpanded: Boolean = false,
        enableFullScreenExpansion: Boolean = false,
        topWindowInset: Dp = HmrcTheme.dimensions.hmrcSpacing48,
        onDismissRequest: () -> Unit,
        sheetContent: @Composable ColumnScope.() -> Unit,
    ) {
        val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = skipPartiallyExpanded)
        val scope = rememberCoroutineScope()
        val closeSheetContentDescription = stringResource(id = R.string.bottomSheetView_close_content_desc)

        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = onDismissRequest,
                sheetState = sheetState,
                containerColor = colors.hmrcWhiteBackground,
                windowInsets = WindowInsets(top = topWindowInset),
                dragHandle = { HmrcDragHandle() },
                modifier = modifier,
            ) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = HmrcTheme.dimensions.hmrcSpacing16),
                    horizontalArrangement = Arrangement.End
                ) {
                    FilledIconButton(
                        colors = IconButtonDefaults.filledIconButtonColors(containerColor = colors.hmrcGrey3),
                        onClick = {
                            scope
                                .launch { sheetState.hide() }
                                .invokeOnCompletion {
                                    if (!sheetState.isVisible) {
                                        onDismissRequest()
                                    }
                                }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = closeSheetContentDescription,
                            tint = colors.hmrcGrey2
                        )
                    }
                }
                if (enableFullScreenExpansion) {
                    Column(
                        Modifier
                            .padding(horizontal = HmrcTheme.dimensions.hmrcSpacing16)
                            .fillMaxHeight()
                            .verticalScroll(rememberScrollState())
                    ) {
                        sheetContent()
                        Spacer(Modifier.windowInsetsBottomHeight(WindowInsets.safeContent))
                    }
                } else {
                    Column(
                        Modifier
                            .padding(horizontal = HmrcTheme.dimensions.hmrcSpacing16)
                            .verticalScroll(rememberScrollState())
                    ) {
                        sheetContent()
                        Spacer(Modifier.windowInsetsBottomHeight(WindowInsets.safeContent))
                    }
                }
            }
        }
    }

    // This DragHandle is a direct copy of the default DragHandle from Compose BottomSheetDefaults.
    // We need this to be able to remove the padding underneath the drag handle to reduce the space above our "close"
    // button on the sheet. This is the only intended change. As the sizing/accessibility parameters are not accessible
    // to us, we have created copies of them too, so we should be mindful of material design updates that may need
    // updating here too.
    @Composable
    private fun HmrcDragHandle(
        modifier: Modifier = Modifier,
        width: Dp = DockedDragHandleWidth,
        height: Dp = DockedDragHandleHeight,
        shape: Shape = MaterialTheme.shapes.extraLarge,
        color: Color = DockedDragHandleColor,
    ) {
        val dragHandleContentDescription = stringResource(id = R.string.bottomSheetView_drag_handle_content_desc)
        Surface(
            modifier = modifier
                .padding(top = DragHandleVerticalPadding)
                .semantics { contentDescription = dragHandleContentDescription },
            color = color,
            shape = shape
        ) {
            Box(Modifier.size(width = width, height = height))
        }
    }

    internal object SheetBottomTokens {
        val DockedDragHandleWidth = 32.0.dp
        val DockedDragHandleHeight = 4.0.dp
        val DockedDragHandleColor = HmrcGrey2
        val DragHandleVerticalPadding = 22.dp
    }
}
