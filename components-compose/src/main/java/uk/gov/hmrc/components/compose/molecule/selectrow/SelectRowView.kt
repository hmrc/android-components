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

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import uk.gov.hmrc.components.compose.R
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme

object SelectRowView {

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
        selectRowViewItems: List<String>,
        checkedIcon: Int = R.drawable.components_select_row_circle_checked,
        uncheckedIcon: Int = R.drawable.components_select_row_circle_unchecked,
        rowSelectedPosition: Int,
        errorText: String = "",
        onRowSelected: (position: Int, value: String) -> Unit
    ) {
        Column(modifier = modifier) {

            if (errorText.isNotEmpty()) {
                Text(
                    text = errorText,
                    style = HmrcTheme.typography.body.copy(color = HmrcTheme.colors.hmrcRed),
                    modifier = Modifier.offset(HmrcTheme.dimensions.hmrcSpacing8)
                )
                Spacer(modifier = Modifier.height(HmrcTheme.dimensions.hmrcSpacing8))
            }

            selectRowViewItems.forEachIndexed { index, selectRow ->
                val interactionSource = remember { MutableInteractionSource() }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .selectable(
                            selected = (rowSelectedPosition == index),
                            interactionSource = interactionSource,
                            indication = rememberRipple(bounded = true),
                            onClick = { onRowSelected(index, selectRow) },
                            role = Role.RadioButton,
                        )
                        .padding(HmrcTheme.dimensions.hmrcSpacing16)
                ) {
                    IconToggleButton(
                        checked = rowSelectedPosition == index,
                        onCheckedChange = {
                            onRowSelected(index, selectRow)
                        },
                        modifier = Modifier.size(HmrcTheme.dimensions.hmrcIconSize24)
                    ) {
                        Icon(
                            painter = painterResource(
                                if (rowSelectedPosition == index) {
                                    checkedIcon
                                } else {
                                    uncheckedIcon
                                }
                            ),
                            contentDescription = "",
                            tint = HmrcTheme.colors.hmrcBlack
                        )
                    }

                    Spacer(modifier = Modifier.width(HmrcTheme.dimensions.hmrcSpacing16))

                    Text(
                        text = selectRow,
                        modifier = Modifier.fillMaxWidth(),
                        style = HmrcTheme.typography.body
                    )
                }
            }
        }
    }
}
