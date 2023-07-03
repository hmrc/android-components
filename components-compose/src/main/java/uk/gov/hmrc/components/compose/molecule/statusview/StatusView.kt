package uk.gov.hmrc.components.compose.molecule.statusview

import androidx.compose.runtime.Composable

object StatusView {

    @Composable
    operator fun invoke(
        title : String,
        body : String,
        bodyContentDesc : String,
        icon : Int,
        iconTint : Int,
        primaryButtonText : String,
        secondaryButtonText : String,
        infoText : String
    ){

    }
}