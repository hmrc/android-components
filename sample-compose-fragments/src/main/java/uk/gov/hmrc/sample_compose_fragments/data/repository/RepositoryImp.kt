package uk.gov.hmrc.sample_compose_fragments.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.ui.graphics.Color
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
import uk.gov.hmrc.sample_compose_fragments.domain.model.ColorItems
import uk.gov.hmrc.sample_compose_fragments.domain.repository.Repository
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
class RepositoryImp @Inject constructor() : Repository {

    override suspend fun getLists(): List<ColorItems> {
        val testList = arrayListOf<ColorItems>()
        testList.add(ColorItems("White (${HmrcWhite.toAGColor().hexToString()})", HmrcWhite))
        testList.add(ColorItems("Black (${HmrcBlack.toAGColor().hexToString()})", HmrcBlack))
        testList.add(ColorItems("Green1 (${HmrcGreen1.toAGColor().hexToString()})", HmrcGreen1))
        testList.add(ColorItems("Green2 (${HmrcGreen2.toAGColor().hexToString()})", HmrcGreen2))
        testList.add(ColorItems("Blue (${HmrcBlue.toAGColor().hexToString()})", HmrcBlue))
        testList.add(ColorItems("Teal (${HmrcTeal.toAGColor().hexToString()})", HmrcTeal))
        testList.add(ColorItems("Gray (${HmrcGrey1.toAGColor().hexToString()})", HmrcGrey1))
        testList.add(ColorItems("Gray2 (${HmrcGrey2.toAGColor().hexToString()})", HmrcGrey2))
        testList.add(ColorItems("Gray3 (${HmrcGrey3.toAGColor().hexToString()})", HmrcGrey3))
        testList.add(ColorItems("Pink (${HmrcPink.toAGColor().hexToString()})", HmrcPink))
        testList.add(ColorItems("Yellow (${HmrcYellow.toAGColor().hexToString()})", HmrcYellow))
        testList.add(
            ColorItems(
                "White background (${
                    HmrcWhiteBackground.toAGColor().hexToString()
                })", HmrcWhiteBackground
            )
        )
        testList.add(
            ColorItems(
                "Always black (${HmrcWhiteDark.toAGColor().hexToString()})",
                HmrcWhiteDark
            )
        )
        testList.add(
            ColorItems(
                "Always white (${HmrcWhiteBackground.toAGColor().hexToString()})",
                HmrcWhiteBackground
            )
        )
        return testList
    }

    private fun Int.hexToString() = String.format("#0x%06X", this)
    private fun Color.toAGColor() =
        toString().run { android.graphics.Color.argb(alpha, red, green, blue) }

}