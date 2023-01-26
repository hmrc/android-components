package uk.gov.hmrc.sample_compose_fragments.domain.repository

import uk.gov.hmrc.sample_compose_fragments.domain.model.ColorItems

interface Repository {
    suspend fun getLists(): List<ColorItems>
}