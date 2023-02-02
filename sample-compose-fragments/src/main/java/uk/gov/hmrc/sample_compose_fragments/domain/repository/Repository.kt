package uk.gov.hmrc.sample_compose_fragments.domain.repository

import uk.gov.hmrc.sample_compose_fragments.domain.model.ColorItem

interface Repository {
    suspend fun getColorList(): List<ColorItem>
}