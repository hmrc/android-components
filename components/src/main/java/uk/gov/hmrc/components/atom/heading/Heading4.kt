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
package uk.gov.hmrc.components.atom.heading

import android.content.Context
import android.util.AttributeSet
import uk.gov.hmrc.components.R
import uk.gov.hmrc.components.extensions.setStyle

class Heading4 @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : Heading(context, attrs) {

    init {
        setStyle(R.style.Text_H4)
    }
}
