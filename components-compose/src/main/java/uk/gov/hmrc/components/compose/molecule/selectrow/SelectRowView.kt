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
package uk.gov.hmrc.components.compose.molecule.selectrow

import androidx.annotation.StringRes
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.LiveRegionMode
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.liveRegion
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import uk.gov.hmrc.components.compose.R
import uk.gov.hmrc.components.compose.atom.divider.HmrcDivider
import uk.gov.hmrc.components.compose.atom.text.BodyText
import uk.gov.hmrc.components.compose.atom.text.ErrorText
import uk.gov.hmrc.components.compose.ui.theme.HmrcRippleTheme
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme

object SelectRowView {

    data class SelectRowViewItem(@StringRes val label: Int)

    /**
     * Composable function that creates a SelectRowView with icons and text.
     *
     * @param selectRowViewItems List of items to be displayed in the selectable rows.
     * @param checkedIcon Resource ID for the checked icon.
     * @param uncheckedIcon Resource ID for the unchecked icon.
     * @param rowSelectedPosition Initial selection position of a SelectRowView
     * @param errorText Error text to be displayed, if any.
     * @param onRowSelected Callback function triggered when an item is clicked.
     */
    @Composable
    operator fun invoke(
        modifier: Modifier = Modifier,
        selectRowViewItems: List<SelectRowViewItem>,
        checkedIcon: Int = R.drawable.components_select_row_circle_checked,
        uncheckedIcon: Int = R.drawable.components_select_row_circle_unchecked,
        showDivider: Boolean = false,
        defaultRowHorizontalPadding: Boolean = true,
        selectedRowItem: SelectRowViewItem? = null,
        @StringRes errorText: Int? = null,
        onRowSelected: (SelectRowViewItem) -> Unit
    ) {
        CompositionLocalProvider(LocalRippleTheme provides HmrcRippleTheme) {
            Column(modifier = modifier) {
                errorText?.let {
                    ErrorText(
                        text = stringResource(id = errorText),
                        modifier = Modifier
                            .padding(horizontal = HmrcTheme.dimensions.hmrcSpacing16)
                            .focusable()
                            .semantics { liveRegion = LiveRegionMode.Polite }
                    )
                }

                Column(modifier = Modifier.selectableGroup()) {
                    val rowHorizontalPadding = if (defaultRowHorizontalPadding) {
                        HmrcTheme.dimensions.hmrcSpacing16
                    } else 0.dp
                    selectRowViewItems.forEachIndexed { index, rowItem ->
                        Row(
                            verticalAlignment = Alignment.Top,
                            modifier = modifier
                                .selectable(
                                    selected = selectedRowItem == rowItem,
                                    onClick = { onRowSelected(rowItem) },
                                    role = Role.RadioButton
                                )
                                .padding(
                                    vertical = HmrcTheme.dimensions.hmrcSpacing16,
                                    horizontal = rowHorizontalPadding
                                )
                        ) {
                            Icon(
                                painter = painterResource(
                                    if (selectedRowItem == rowItem) checkedIcon else uncheckedIcon
                                ),
                                contentDescription = null,
                                tint = HmrcTheme.colors.hmrcBlack
                            )
                            Spacer(modifier = Modifier.width(HmrcTheme.dimensions.hmrcSpacing16))
                            BodyText(
                                text = stringResource(id = rowItem.label),
                                modifier = Modifier.fillMaxWidth(),
                            )
                        }
                        if (showDivider && index < selectRowViewItems.size - 1) {
                            HmrcDivider(
                                modifier = Modifier.padding(
                                    start = HmrcTheme.dimensions.hmrcSpacing16,
                                    end = HmrcTheme.dimensions.hmrcSpacing16
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}
