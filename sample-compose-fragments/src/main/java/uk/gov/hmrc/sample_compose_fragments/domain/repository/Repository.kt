package uk.gov.hmrc.sample_compose_fragments.domain.repository

import uk.gov.hmrc.components.compose.ui.theme.ColorItem

interface Repository {
    suspend fun getColorList(): List<ColorItem>
}