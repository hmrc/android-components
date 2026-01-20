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
package uk.gov.hmrc.components.sample.colors

import uk.gov.hmrc.components.sample.R
import uk.gov.hmrc.components.sample.base.ToolbarState
import uk.gov.hmrc.components.R as commonR

class ColorsFragment : ColorListFragment() {

    override fun provideToolbar() = ToolbarState(true, R.string.title_colors, false)

    override fun provideItems(): List<ColorListItem> {
        return listOf(
            ColorListItem(getString(R.string.colors_black), commonR.color.hmrc_black),
            ColorListItem(getString(R.string.colors_white), commonR.color.hmrc_white),
            ColorListItem(getString(R.string.colors_green_1), commonR.color.hmrc_green_1),
            ColorListItem(getString(R.string.colors_green_2), commonR.color.hmrc_green_2),
            ColorListItem(getString(R.string.colors_blue), commonR.color.hmrc_blue),
            ColorListItem(getString(R.string.colors_teal), commonR.color.hmrc_teal),
            ColorListItem(getString(R.string.colors_red), commonR.color.hmrc_red),
            ColorListItem(getString(R.string.colors_grey_1), commonR.color.hmrc_grey_1),
            ColorListItem(getString(R.string.colors_grey_2), commonR.color.hmrc_grey_2),
            ColorListItem(getString(R.string.colors_grey_3), commonR.color.hmrc_grey_3),
            ColorListItem(getString(R.string.colors_pink), commonR.color.hmrc_pink),
            ColorListItem(getString(R.string.colors_yellow), commonR.color.hmrc_yellow),
            ColorListItem(getString(R.string.colors_white_background), commonR.color.hmrc_white_background),
            ColorListItem(getString(R.string.colors_always_black), commonR.color.hmrc_always_black),
            ColorListItem(getString(R.string.colors_always_white), commonR.color.hmrc_always_white)
        )
    }
}
