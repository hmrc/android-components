package uk.gov.hmrc.sample_compose_fragments.data.repository

import uk.gov.hmrc.components.compose.ui.theme.ColorItem
import uk.gov.hmrc.sample_compose_fragments.domain.repository.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor() : Repository {

    override suspend fun getColorList() = arrayListOf(
        ColorItem.HmrcBlack, ColorItem.HmrcWhite, ColorItem.HmrcGreen1, ColorItem.HmrcGreen2,
        ColorItem.HmrcBlue, ColorItem.HmrcTeal, ColorItem.HmrcRed, ColorItem.HmrcGrey1,
        ColorItem.HmrcGrey2, ColorItem.HmrcGrey3, ColorItem.HmrcPink, ColorItem.HmrcYellow,
        ColorItem.HmrcWhiteBackground, ColorItem.HmrcAlwaysBlack, ColorItem.HmrcAlwaysWhite,
    )

}