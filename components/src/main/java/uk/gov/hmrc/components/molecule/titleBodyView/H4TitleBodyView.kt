/*
 * Copyright 2019 HM Revenue & Customs
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
package uk.gov.hmrc.components.molecule.titleBodyView

import android.content.Context
import android.util.AttributeSet
import uk.gov.hmrc.components.R
import uk.gov.hmrc.components.extensions.setStyle

class H4TitleBodyView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    TitleBodyView(context, attrs, defStyleAttr) {
    init {
        binding.title.setStyle(R.style.Text_H4)
        binding.body.setStyle(R.style.Text_H5)
    }
}
