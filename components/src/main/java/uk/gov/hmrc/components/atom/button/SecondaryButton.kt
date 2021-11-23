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

open class SecondaryButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = R.attr.materialTextButtonStyle
) : Button(context, attrs, defStyleAttr) {

    init {
        this.setTextColor(ContextCompat.getColor(context, R.color.hmrc_blue))
        this.setRippleColorResource(R.color.hmrc_secondary_button_ripple)
        this.setPadding(
            resources.getDimensionPixelSize(R.dimen.hmrc_spacing_16),
            resources.getDimensionPixelSize(R.dimen.hmrc_spacing_4),
            resources.getDimensionPixelSize(R.dimen.hmrc_spacing_16),
            resources.getDimensionPixelSize(R.dimen.hmrc_spacing_4)
        )
    }
}
