/*
 * Copyright 2020 HM Revenue & Customs
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
package uk.gov.hmrc.components.atom.button

import android.content.Context
import android.util.AttributeSet
import androidx.core.content.ContextCompat
import uk.gov.hmrc.components.R

class PrimaryButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : Button(context, attrs) {

    init {
        backgroundTintList = ContextCompat.getColorStateList(context, R.color.hmrc_primary_button_background)

        setTextColor(ContextCompat.getColorStateList(context, R.color.hmrc_primary_button_text))
    }
}
