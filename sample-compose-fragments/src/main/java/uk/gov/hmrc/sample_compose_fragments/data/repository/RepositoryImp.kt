package uk.gov.hmrc.sample_compose_fragments.data.repository

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import uk.gov.hmrc.components.compose.ui.theme.HmrcBlack
import uk.gov.hmrc.components.compose.ui.theme.HmrcBlue
import uk.gov.hmrc.components.compose.ui.theme.HmrcGreen1
import uk.gov.hmrc.components.compose.ui.theme.HmrcGreen2
import uk.gov.hmrc.components.compose.ui.theme.HmrcGrey1
import uk.gov.hmrc.components.compose.ui.theme.HmrcGrey2
import uk.gov.hmrc.components.compose.ui.theme.HmrcGrey3
import uk.gov.hmrc.components.compose.ui.theme.HmrcPink
import uk.gov.hmrc.components.compose.ui.theme.HmrcTeal
import uk.gov.hmrc.components.compose.ui.theme.HmrcWhite
import uk.gov.hmrc.components.compose.ui.theme.HmrcWhiteBackground
import uk.gov.hmrc.components.compose.ui.theme.HmrcWhiteDark
import uk.gov.hmrc.components.compose.ui.theme.HmrcYellow
import uk.gov.hmrc.sample_compose_fragments.domain.model.ColorItem
import uk.gov.hmrc.sample_compose_fragments.domain.repository.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor() : Repository {

    override suspend fun getLists(): List<ColorItem> {
        val colorList = arrayListOf<ColorItem>()
        colorList.add(ColorItem("White (${HmrcWhite.hexToString()})", HmrcWhite))
        colorList.add(ColorItem("Black (${HmrcBlack.hexToString()})", HmrcBlack))
        colorList.add(ColorItem("Green1 (${HmrcGreen1.hexToString()})", HmrcGreen1))
        colorList.add(ColorItem("Green2 (${HmrcGreen2.hexToString()})", HmrcGreen2))
        colorList.add(ColorItem("Blue (${HmrcBlue.hexToString()})", HmrcBlue))
        colorList.add(ColorItem("Teal (${HmrcTeal.hexToString()})", HmrcTeal))
        colorList.add(ColorItem("Grey (${HmrcGrey1.hexToString()})", HmrcGrey1))
        colorList.add(ColorItem("Grey2 (${HmrcGrey2.hexToString()})", HmrcGrey2))
        colorList.add(ColorItem("Grey3 (${HmrcGrey3.hexToString()})", HmrcGrey3))
        colorList.add(ColorItem("Pink (${HmrcPink.hexToString()})", HmrcPink))
        colorList.add(ColorItem("Yellow (${HmrcYellow.hexToString()})", HmrcYellow))
        colorList.add(
            ColorItem(
                "White background (${
                    HmrcWhiteBackground.hexToString()
                })", HmrcWhiteBackground
            )
        )
        colorList.add(
            ColorItem(
                "Always black (${HmrcWhiteDark.hexToString()})",
                HmrcWhiteDark
            )
        )
        return colorList
    }

    private fun Color.hexToString(): String = String.format("#%08X", toArgb())

}