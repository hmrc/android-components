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

class RepositoryImp @Inject constructor() : Repository {

    override suspend fun getLists(): List<ColorItem> {
        val testList = arrayListOf<ColorItem>()
        testList.add(ColorItem("White (${HmrcWhite.hexToString()})", HmrcWhite))
        testList.add(ColorItem("Black (${HmrcBlack.hexToString()})", HmrcBlack))
        testList.add(ColorItem("Green1 (${HmrcGreen1.hexToString()})", HmrcGreen1))
        testList.add(ColorItem("Green2 (${HmrcGreen2.hexToString()})", HmrcGreen2))
        testList.add(ColorItem("Blue (${HmrcBlue.hexToString()})", HmrcBlue))
        testList.add(ColorItem("Teal (${HmrcTeal.hexToString()})", HmrcTeal))
        testList.add(ColorItem("Gray (${HmrcGrey1.hexToString()})", HmrcGrey1))
        testList.add(ColorItem("Gray2 (${HmrcGrey2.hexToString()})", HmrcGrey2))
        testList.add(ColorItem("Gray3 (${HmrcGrey3.hexToString()})", HmrcGrey3))
        testList.add(ColorItem("Pink (${HmrcPink.hexToString()})", HmrcPink))
        testList.add(ColorItem("Yellow (${HmrcYellow.hexToString()})", HmrcYellow))
        testList.add(
            ColorItem(
                "White background (${
                    HmrcWhiteBackground.hexToString()
                })", HmrcWhiteBackground
            )
        )
        testList.add(
            ColorItem(
                "Always black (${HmrcWhiteDark.hexToString()})",
                HmrcWhiteDark
            )
        )
        testList.add(
            ColorItem(
                "Always white (${HmrcWhiteBackground.hexToString()})",
                HmrcWhiteBackground
            )
        )
        return testList
    }

    private fun Color.hexToString(): String = String.format("#%08X", toArgb())

}