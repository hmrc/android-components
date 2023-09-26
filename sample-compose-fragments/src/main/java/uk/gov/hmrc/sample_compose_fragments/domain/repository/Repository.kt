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
package uk.gov.hmrc.sample_compose_fragments.domain.repository

import uk.gov.hmrc.sample_compose_fragments.data.model.ColorItem
import uk.gov.hmrc.sample_compose_fragments.data.model.ComponentMenuItem

interface Repository {
    suspend fun getColorList(): List<ColorItem>
    suspend fun getAtomList(): List<ComponentMenuItem>
    suspend fun getMoleculesList(): List<ComponentMenuItem>
    suspend fun getOrganismsList(): List<ComponentMenuItem>
}