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
package uk.gov.hmrc.components.compose.molecule.item

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme.dimensions

@Composable
fun SwitchRowView(
    modifier: Modifier = Modifier,
    title: String? = null,
    body: String? = null,
    checkedState: Boolean = false,
    checkedStateContentDesc: String? = null,
    onCheckedChangeListener: ((Boolean) -> Unit)? = null,
) {

    val icon: (@Composable () -> Unit)? = if (checkedState) {
        {
            Icon(
                imageVector = Icons.Filled.Check,
                contentDescription = null, // Icon isn't focusable, no need for content description
                modifier = Modifier.size(SwitchDefaults.IconSize),
            )
        }
    } else null

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f).wrapContentWidth(Alignment.Start)) {
            title?.let { title -> Text(text = title, style = HmrcTheme.typography.h6) }
            if (title != null && body != null) { Spacer(modifier = Modifier.height(dimensions.hmrcSpacing8)) }
            body?.let { body -> Text(text = body, style = HmrcTheme.typography.info) }
        }
        Column(modifier = Modifier.wrapContentWidth(Alignment.End).padding(start = dimensions.hmrcSpacing16)) {
            Switch(
                modifier = Modifier.semantics { checkedStateContentDesc?.let { stateDescription = it } },
                checked = checkedState,
                onCheckedChange = { if (onCheckedChangeListener != null) { onCheckedChangeListener(it) } },
                thumbContent = icon,
                colors = SwitchDefaults.colors(
                    checkedThumbColor = HmrcTheme.colors.hmrcWhite,
                    checkedTrackColor = HmrcTheme.colors.hmrcBlue,
                    checkedBorderColor = HmrcTheme.colors.hmrcBlue,
                    checkedIconColor = HmrcTheme.colors.hmrcBlue,
                    uncheckedThumbColor = HmrcTheme.colors.hmrcGrey1,
                    uncheckedTrackColor = HmrcTheme.colors.hmrcGrey3,
                    uncheckedBorderColor = HmrcTheme.colors.hmrcGrey1,
                    uncheckedIconColor = HmrcTheme.colors.hmrcGrey1
                )
            )
        }
    }
}
