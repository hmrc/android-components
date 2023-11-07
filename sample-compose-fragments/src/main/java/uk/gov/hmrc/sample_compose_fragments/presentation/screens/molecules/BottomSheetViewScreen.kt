package uk.gov.hmrc.sample_compose_fragments.presentation.screens.molecules

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import uk.gov.hmrc.components.compose.atom.button.PrimaryButton
import uk.gov.hmrc.components.compose.molecule.bottomsheet.BottomSheetView
import uk.gov.hmrc.components.compose.molecule.selectrow.SelectRowView
import uk.gov.hmrc.components.compose.molecule.titleBody.H5TitleBodyView
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme
import uk.gov.hmrc.sample_compose_components.R
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.ScreenScrollViewColumn

@Composable
fun BottomSheetViewScreen() {
    var openBottomSheetExample1: Boolean by rememberSaveable { mutableStateOf(false) }
    var openBottomSheetExample2: Boolean by rememberSaveable { mutableStateOf(false) }
    var openBottomSheetExample3: Boolean by rememberSaveable { mutableStateOf(false) }
    var openBottomSheetExample4: Boolean by rememberSaveable { mutableStateOf(false) }
    var openBottomSheetExample5: Boolean by rememberSaveable { mutableStateOf(false) }
    var openBottomSheetExample6: Boolean by rememberSaveable { mutableStateOf(false) }
    var openBottomSheetExample7: Boolean by rememberSaveable { mutableStateOf(false) }
    var openBottomSheetExample8: Boolean by rememberSaveable { mutableStateOf(false) }
    var openBottomSheetExample9: Boolean by rememberSaveable { mutableStateOf(false) }
    var openBottomSheetExample10: Boolean by rememberSaveable { mutableStateOf(false) }

    ScreenScrollViewColumn {
        PrimaryButton(text = stringResource(id = R.string.bottom_sheet_example_1)) {
            openBottomSheetExample1 = true
        }
        Spacer(modifier = Modifier.height(HmrcTheme.dimensions.hmrcSpacing16))
        PrimaryButton(text = stringResource(id = R.string.bottom_sheet_example_2)) {
            openBottomSheetExample2 = true
        }
        Spacer(modifier = Modifier.height(HmrcTheme.dimensions.hmrcSpacing16))
        PrimaryButton(text = stringResource(id = R.string.bottom_sheet_example_3)) {
            openBottomSheetExample3 = true
        }
        Spacer(modifier = Modifier.height(HmrcTheme.dimensions.hmrcSpacing16))
        PrimaryButton(text = stringResource(id = R.string.bottom_sheet_example_4)) {
            openBottomSheetExample4 = true
        }
        Spacer(modifier = Modifier.height(HmrcTheme.dimensions.hmrcSpacing16))
        PrimaryButton(text = stringResource(id = R.string.bottom_sheet_example_5)) {
            openBottomSheetExample5 = true
        }
        Spacer(modifier = Modifier.height(HmrcTheme.dimensions.hmrcSpacing16))
        PrimaryButton(text = stringResource(id = R.string.bottom_sheet_example_6)) {
            openBottomSheetExample6 = true
        }
        Spacer(modifier = Modifier.height(HmrcTheme.dimensions.hmrcSpacing16))
        PrimaryButton(text = stringResource(id = R.string.bottom_sheet_example_7)) {
            openBottomSheetExample7 = true
        }
        Spacer(modifier = Modifier.height(HmrcTheme.dimensions.hmrcSpacing16))
        PrimaryButton(text = stringResource(id = R.string.bottom_sheet_example_8)) {
            openBottomSheetExample8 = true
        }
        Spacer(modifier = Modifier.height(HmrcTheme.dimensions.hmrcSpacing16))
        PrimaryButton(text = stringResource(id = R.string.bottom_sheet_example_9)) {
            openBottomSheetExample9 = true
        }
        Spacer(modifier = Modifier.height(HmrcTheme.dimensions.hmrcSpacing16))
        PrimaryButton(text = stringResource(id = R.string.bottom_sheet_example_10)) {
            openBottomSheetExample10 = true
        }

        SimpleBottomSheetExample(openBottomSheet = openBottomSheetExample1) {
            openBottomSheetExample1 = !openBottomSheetExample1
        }
        SimpleBottomSheetExtendedExample(openBottomSheet = openBottomSheetExample2) {
            openBottomSheetExample2= !openBottomSheetExample2
        }
        SimpleBottomFullSheetExample(openBottomSheet = openBottomSheetExample3) {
            openBottomSheetExample3 = !openBottomSheetExample3
        }
        SimpleBottomFullSheetExtendedExample(openBottomSheet = openBottomSheetExample4) {
            openBottomSheetExample4= !openBottomSheetExample4
        }
        SimpleLongBottomSheetExample(openBottomSheet = openBottomSheetExample5) {
            openBottomSheetExample5 = !openBottomSheetExample5
        }
        SimpleLongBottomSheetExtendedExample(openBottomSheet = openBottomSheetExample6) {
            openBottomSheetExample6= !openBottomSheetExample6
        }
        ScrollableBottomSheetExample(openBottomSheet = openBottomSheetExample7) {
            openBottomSheetExample7 = !openBottomSheetExample7
        }
        ExtendedScrollableBottomSheetExample(openBottomSheet = openBottomSheetExample8) {
            openBottomSheetExample8 = !openBottomSheetExample8
        }
        SmallExampleBottomSheet(openBottomSheet = openBottomSheetExample9) {
            openBottomSheetExample9 = !openBottomSheetExample9
        }
        SmallExampleBottomSheetExtended(openBottomSheet = openBottomSheetExample10) {
            openBottomSheetExample10 = !openBottomSheetExample10
        }
    }
}

@Composable
fun SimpleBottomSheetExample(openBottomSheet: Boolean, onDismissRequest: () -> Unit) {
    BottomSheetView(
        showBottomSheet = openBottomSheet,
        skipPartiallyExpanded = false,
        onDismissRequest = onDismissRequest
    ) {
        SimpleContent(onDismissRequest)
    }
}

@Composable
fun SimpleBottomSheetExtendedExample(openBottomSheet: Boolean, onDismissRequest: () -> Unit) {
    BottomSheetView(
        showBottomSheet = openBottomSheet,
        skipPartiallyExpanded = true,
        onDismissRequest = onDismissRequest
    ) {
        SimpleContent(onDismissRequest)
    }
}

@Composable
fun SimpleBottomFullSheetExample(openBottomSheet: Boolean, onDismissRequest: () -> Unit) {
    BottomSheetView(
        showBottomSheet = openBottomSheet,
        skipPartiallyExpanded = false,
        enableFullScreenExpansion = true,
        onDismissRequest = onDismissRequest
    ) {
        SimpleContent(onDismissRequest)
    }
}

@Composable
fun SimpleBottomFullSheetExtendedExample(openBottomSheet: Boolean, onDismissRequest: () -> Unit) {
    BottomSheetView(
        showBottomSheet = openBottomSheet,
        skipPartiallyExpanded = true,
        enableFullScreenExpansion = true,
        onDismissRequest = onDismissRequest
    ) {
        SimpleContent(onDismissRequest)
    }
}

@Composable
fun SimpleContent(onDismissRequest: () -> Unit) {
    H5TitleBodyView(
        titleText = "Do you receive Blind Person's Allowance?",
        bodyText = "If you're registered blind or severely sight impaired, you may get Blind Person's Allowance. " +
                "This increases your Personal Allowance and means you pay less tax."
    )
    SelectRowView(
        selectRowViewItems = listOf("Yes", "No"),
        defaultRowHorizontalPadding = false
    ) { _, _ -> }
    PrimaryButton("Save") { onDismissRequest() }
}

@Composable
fun SimpleLongBottomSheetExample(openBottomSheet: Boolean, onDismissRequest: () -> Unit) {
    BottomSheetView(
        showBottomSheet = openBottomSheet,
        skipPartiallyExpanded = false,
        onDismissRequest = onDismissRequest
    ) {
        SimpleLongContentExample(onDismissRequest)
    }
}

@Composable
fun SimpleLongBottomSheetExtendedExample(openBottomSheet: Boolean, onDismissRequest: () -> Unit) {
    BottomSheetView(
        showBottomSheet = openBottomSheet,
        skipPartiallyExpanded = true,
        onDismissRequest = onDismissRequest
    ) {
        SimpleLongContentExample(onDismissRequest)
    }
}

@Composable
fun SimpleLongContentExample(onDismissRequest: () -> Unit) {
    H5TitleBodyView(
        titleText = "Do you receive Blind Person's Allowance?",
        bodyText = "If you're registered blind or severely sight impaired, you may get Blind Person's Allowance. " +
                "This increases your Personal Allowance and means you pay less tax."
    )
    SelectRowView(
        selectRowViewItems = listOf("Yes", "No", "Maybe", "I don't know", "Repeat question"),
        defaultRowHorizontalPadding = false
    ) { _, _ -> }
    PrimaryButton("Save") { onDismissRequest() }
}

@Composable
fun ScrollableBottomSheetExample(openBottomSheet: Boolean, onDismissRequest: () -> Unit) {
    BottomSheetView(
        showBottomSheet = openBottomSheet,
        skipPartiallyExpanded = false,
        onDismissRequest = onDismissRequest
    ) {
        ScrollableExampleContent(onDismissRequest)
    }
}

@Composable
fun ExtendedScrollableBottomSheetExample(openBottomSheet: Boolean, onDismissRequest: () -> Unit) {
    BottomSheetView(
        showBottomSheet = openBottomSheet,
        skipPartiallyExpanded = true,
        onDismissRequest = onDismissRequest
    ) {
        ScrollableExampleContent(onDismissRequest)
    }
}

@Composable
fun ScrollableExampleContent(onDismissRequest: () -> Unit) {
    H5TitleBodyView(
        titleText = "Do you receive Blind Person's Allowance?",
        bodyText = "If you're registered blind or severely sight impaired, you may get Blind Person's Allowance. " +
                "This increases your Personal Allowance and means you pay less tax.",
        modifier = Modifier.padding(bottom = HmrcTheme.dimensions.hmrcSpacing16)
    )
    H5TitleBodyView(
        titleText = stringResource(R.string.long_text),
        bodyText = stringResource(R.string.longest_text),
        modifier = Modifier.padding(bottom = HmrcTheme.dimensions.hmrcSpacing16)
    )
    H5TitleBodyView(
        titleText = stringResource(R.string.long_text),
        bodyText = stringResource(R.string.longest_text),
        modifier = Modifier.padding(bottom = HmrcTheme.dimensions.hmrcSpacing16)
    )
    H5TitleBodyView(
        titleText = stringResource(R.string.long_text),
        bodyText = stringResource(R.string.longest_text),
        modifier = Modifier.padding(bottom = HmrcTheme.dimensions.hmrcSpacing16)
    )
    H5TitleBodyView(
        titleText = stringResource(R.string.long_text),
        bodyText = stringResource(R.string.longest_text),
        modifier = Modifier.padding(bottom = HmrcTheme.dimensions.hmrcSpacing16)
    )
    H5TitleBodyView(
        titleText = stringResource(R.string.long_text),
        bodyText = stringResource(R.string.longest_text),
        modifier = Modifier.padding(bottom = HmrcTheme.dimensions.hmrcSpacing16)
    )
    H5TitleBodyView(
        titleText = stringResource(R.string.long_text),
        bodyText = stringResource(R.string.longest_text),
        modifier = Modifier.padding(bottom = HmrcTheme.dimensions.hmrcSpacing16)
    )
    H5TitleBodyView(
        titleText = stringResource(R.string.long_text),
        bodyText = stringResource(R.string.longest_text),
        modifier = Modifier.padding(bottom = HmrcTheme.dimensions.hmrcSpacing16)
    )
    H5TitleBodyView(
        titleText = stringResource(R.string.long_text),
        bodyText = stringResource(R.string.longest_text),
        modifier = Modifier.padding(bottom = HmrcTheme.dimensions.hmrcSpacing16)
    )
    PrimaryButton("Okay") { onDismissRequest() }
}

@Composable
fun SmallExampleBottomSheet(openBottomSheet: Boolean, onDismissRequest: () -> Unit) {
    BottomSheetView(
        showBottomSheet = openBottomSheet,
        skipPartiallyExpanded = false,
        onDismissRequest = onDismissRequest
    ) {
        Text("Hello!")
    }
}

@Composable
fun SmallExampleBottomSheetExtended(openBottomSheet: Boolean, onDismissRequest: () -> Unit) {
    BottomSheetView(
        showBottomSheet = openBottomSheet,
        skipPartiallyExpanded = false,
        enableFullScreenExpansion = true,
        onDismissRequest = onDismissRequest
    ) {
        Text("Hello!")
    }
}
