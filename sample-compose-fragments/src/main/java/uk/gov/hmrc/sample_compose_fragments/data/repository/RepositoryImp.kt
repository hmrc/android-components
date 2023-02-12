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
package uk.gov.hmrc.sample_compose_fragments.data.repository

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import uk.gov.hmrc.sample_compose_components.R
import uk.gov.hmrc.sample_compose_fragments.data.model.AtomItem
import uk.gov.hmrc.sample_compose_fragments.data.model.ColorItem
import uk.gov.hmrc.sample_compose_fragments.domain.repository.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(@ApplicationContext val mContext: Context) : Repository {
    val string = String
    override suspend fun getColorList() = ColorItem.values().asList()
    override suspend fun getAtomList() = arrayListOf(
        AtomItem(1, mContext.getString(R.string.atoms_text)),
        AtomItem(2, mContext.getString(R.string.atoms_buttons)),
        AtomItem(3, mContext.getString(R.string.atoms_divider))
    )
}