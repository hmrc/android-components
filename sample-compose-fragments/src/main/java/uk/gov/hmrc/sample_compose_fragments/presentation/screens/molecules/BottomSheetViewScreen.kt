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
import androidx.compose.ui.unit.dp
import uk.gov.hmrc.components.compose.atom.button.IconButton
import uk.gov.hmrc.components.compose.atom.button.PrimaryButton
import uk.gov.hmrc.components.compose.atom.text.BodyText
import uk.gov.hmrc.components.compose.molecule.bottomsheet.BottomSheetView
import uk.gov.hmrc.components.compose.molecule.input.TextInputView
import uk.gov.hmrc.components.compose.molecule.selectrow.SelectRowView
import uk.gov.hmrc.components.compose.molecule.titleBody.H5TitleBodyView
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme
import uk.gov.hmrc.components.compose.ui.theme.HmrcTheme.dimensions
import uk.gov.hmrc.components.compose.ui.theme.HmrcAllDevicePreview
import uk.gov.hmrc.sample_compose_components.R
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.ExamplesSlot
import uk.gov.hmrc.sample_compose_fragments.presentation.screens.sampletemplate.HmrcPreview
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
    var openBottomSheetExample11: Boolean by rememberSaveable { mutableStateOf(false) }

    ScreenScrollViewColumn {
        ExamplesSlot {
            // Example 1
            PrimaryButton(text = stringResource(id = R.string.bottom_sheet_example_1)) {
                openBottomSheetExample1 = true
            }
            SimpleBottomSheetExample(openBottomSheet = openBottomSheetExample1, skipPartiallyExpanded = false) {
                openBottomSheetExample1 = !openBottomSheetExample1
            }
            Spacer(modifier = Modifier.height(HmrcTheme.dimensions.hmrcSpacing16))

            // Example 2
            PrimaryButton(text = stringResource(id = R.string.bottom_sheet_example_2)) {
                openBottomSheetExample2 = true
            }
            SimpleBottomSheetExample(openBottomSheet = openBottomSheetExample2, skipPartiallyExpanded = true) {
                openBottomSheetExample2 = !openBottomSheetExample2
            }
            Spacer(modifier = Modifier.height(HmrcTheme.dimensions.hmrcSpacing16))

            // Example 3
            PrimaryButton(text = stringResource(id = R.string.bottom_sheet_example_3)) {
                openBottomSheetExample3 = true
            }
            SimpleBottomFullSheetExample(openBottomSheet = openBottomSheetExample3, skipPartiallyExpanded = false) {
                openBottomSheetExample3 = !openBottomSheetExample3
            }
            Spacer(modifier = Modifier.height(HmrcTheme.dimensions.hmrcSpacing16))

            // Example 4
            PrimaryButton(text = stringResource(id = R.string.bottom_sheet_example_4)) {
                openBottomSheetExample4 = true
            }
            SimpleBottomFullSheetExample(openBottomSheet = openBottomSheetExample4, skipPartiallyExpanded = true) {
                openBottomSheetExample4 = !openBottomSheetExample4
            }
            Spacer(modifier = Modifier.height(HmrcTheme.dimensions.hmrcSpacing16))

            // Example 5
            PrimaryButton(text = stringResource(id = R.string.bottom_sheet_example_5)) {
                openBottomSheetExample5 = true
            }
            SimpleLongBottomSheetExample(openBottomSheet = openBottomSheetExample5, skipPartiallyExpanded = false) {
                openBottomSheetExample5 = !openBottomSheetExample5
            }
            Spacer(modifier = Modifier.height(HmrcTheme.dimensions.hmrcSpacing16))

            // Example 6
            PrimaryButton(text = stringResource(id = R.string.bottom_sheet_example_6)) {
                openBottomSheetExample6 = true
            }
            SimpleLongBottomSheetExample(openBottomSheet = openBottomSheetExample6, skipPartiallyExpanded = true) {
                openBottomSheetExample6 = !openBottomSheetExample6
            }
            Spacer(modifier = Modifier.height(HmrcTheme.dimensions.hmrcSpacing16))

            // Example 7
            PrimaryButton(text = stringResource(id = R.string.bottom_sheet_example_7)) {
                openBottomSheetExample7 = true
            }
            ScrollableBottomSheetExample(openBottomSheet = openBottomSheetExample7, skipPartiallyExpanded = false) {
                openBottomSheetExample7 = !openBottomSheetExample7
            }
            Spacer(modifier = Modifier.height(HmrcTheme.dimensions.hmrcSpacing16))

            // Example 8
            PrimaryButton(text = stringResource(id = R.string.bottom_sheet_example_8)) {
                openBottomSheetExample8 = true
            }
            ScrollableBottomSheetExample(openBottomSheet = openBottomSheetExample8, skipPartiallyExpanded = true) {
                openBottomSheetExample8 = !openBottomSheetExample8
            }
            Spacer(modifier = Modifier.height(HmrcTheme.dimensions.hmrcSpacing16))

            // Example 9
            PrimaryButton(text = stringResource(id = R.string.bottom_sheet_example_9)) {
                openBottomSheetExample9 = true
            }
            SmallExampleBottomSheet(openBottomSheet = openBottomSheetExample9, enableFullScreenExpansion = false) {
                openBottomSheetExample9 = !openBottomSheetExample9
            }
            Spacer(modifier = Modifier.height(HmrcTheme.dimensions.hmrcSpacing16))

            // Example 10
            PrimaryButton(text = stringResource(id = R.string.bottom_sheet_example_10)) {
                openBottomSheetExample10 = true
            }
            SmallExampleBottomSheet(openBottomSheet = openBottomSheetExample10, enableFullScreenExpansion = true) {
                openBottomSheetExample10 = !openBottomSheetExample10
            }
            Spacer(modifier = Modifier.height(HmrcTheme.dimensions.hmrcSpacing16))

            // Example 11
            PrimaryButton(text = stringResource(id = R.string.bottom_sheet_example_11)) {
                openBottomSheetExample11 = true
            }
            CustomPaddingBottomSheet(openBottomSheet = openBottomSheetExample11) {
                openBottomSheetExample11 = !openBottomSheetExample11
            }
        }
    }
}

@Composable
fun SimpleBottomSheetExample(openBottomSheet: Boolean, skipPartiallyExpanded: Boolean, onDismissRequest: () -> Unit) {
    BottomSheetView(
        showBottomSheet = openBottomSheet,
        skipPartiallyExpanded = skipPartiallyExpanded,
        onDismissRequest = onDismissRequest
    ) {
        SimpleContent(onDismissRequest)
    }
}

@Composable
fun SimpleBottomFullSheetExample(
    openBottomSheet: Boolean,
    skipPartiallyExpanded: Boolean,
    onDismissRequest: () -> Unit
) {
    BottomSheetView(
        showBottomSheet = openBottomSheet,
        skipPartiallyExpanded = skipPartiallyExpanded,
        enableFullScreenExpansion = true,
        onDismissRequest = onDismissRequest
    ) {
        SimpleContent(onDismissRequest)
    }
}

@Composable
fun SimpleContent(onDismissRequest: () -> Unit) {
    H5TitleBodyView(
        titleText = stringResource(id = R.string.bottom_sheet_example_long_content_title),
        bodyText = stringResource(id = R.string.bottom_sheet_example_long_content_body),
    )
    SelectRowView(
        selectRowViewItems = listOf(
            SelectRowView.SelectRowViewItem(R.string.bottom_sheet_example_yes),
            SelectRowView.SelectRowViewItem(R.string.bottom_sheet_example_no)
        ),
        defaultRowHorizontalPadding = false
    ) { _ -> }
    PrimaryButton(stringResource(id = R.string.bottom_sheet_example_save)) { onDismissRequest() }
}

@Composable
fun SimpleLongBottomSheetExample(
    openBottomSheet: Boolean,
    skipPartiallyExpanded: Boolean,
    onDismissRequest: () -> Unit
) {
    BottomSheetView(
        showBottomSheet = openBottomSheet,
        skipPartiallyExpanded = skipPartiallyExpanded,
        onDismissRequest = onDismissRequest
    ) {
        SimpleLongContentExample(onDismissRequest)
    }
}

@Composable
fun SimpleLongContentExample(onDismissRequest: () -> Unit) {
    H5TitleBodyView(
        titleText = stringResource(id = R.string.bottom_sheet_example_long_content_title),
        bodyText = stringResource(id = R.string.bottom_sheet_example_long_content_body),
    )
    SelectRowView(
        selectRowViewItems = listOf(
            SelectRowView.SelectRowViewItem(R.string.bottom_sheet_example_yes),
            SelectRowView.SelectRowViewItem(R.string.bottom_sheet_example_no),
            SelectRowView.SelectRowViewItem(R.string.bottom_sheet_example_maybe),
            SelectRowView.SelectRowViewItem(R.string.bottom_sheet_example_dont_know),
            SelectRowView.SelectRowViewItem(R.string.bottom_sheet_example_repeat_question),
        ),
        defaultRowHorizontalPadding = false
    ) { _ -> }
    PrimaryButton(stringResource(id = R.string.bottom_sheet_example_save)) { onDismissRequest() }
}

@Composable
fun ScrollableBottomSheetExample(
    openBottomSheet: Boolean,
    skipPartiallyExpanded: Boolean,
    onDismissRequest: () -> Unit
) {
    BottomSheetView(
        showBottomSheet = openBottomSheet,
        skipPartiallyExpanded = skipPartiallyExpanded,
        onDismissRequest = onDismissRequest
    ) {
        ScrollableExampleContent(onDismissRequest)
    }
}

@Composable
fun ScrollableExampleContent(onDismissRequest: () -> Unit) {
    H5TitleBodyView(
        titleText = stringResource(id = R.string.bottom_sheet_example_long_content_title),
        bodyText = stringResource(id = R.string.bottom_sheet_example_long_content_body),
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
    PrimaryButton(stringResource(R.string.bottom_sheet_example_okay)) { onDismissRequest() }
}

@Composable
fun SmallExampleBottomSheet(
    openBottomSheet: Boolean,
    enableFullScreenExpansion: Boolean,
    onDismissRequest: () -> Unit
) {
    BottomSheetView(
        showBottomSheet = openBottomSheet,
        skipPartiallyExpanded = false,
        enableFullScreenExpansion = enableFullScreenExpansion,
        onDismissRequest = onDismissRequest
    ) {
        Text(text = stringResource(R.string.bottom_sheet_example_hello), style = HmrcTheme.typography.body)
    }
}

@Composable
fun CustomPaddingBottomSheet(
    openBottomSheet: Boolean,
    onDismissRequest: () -> Unit
) {
    BottomSheetView(
        showBottomSheet = openBottomSheet,
        skipPartiallyExpanded = false,
        enableFullScreenExpansion = true,
        onDismissRequest = onDismissRequest,
        contentHorizontalPadding = 0.dp
    ) {
        H5TitleBodyView(
            titleText = stringResource(R.string.long_text),
            bodyText = stringResource(R.string.longest_text),
            modifier = Modifier.padding(
                start = dimensions.hmrcSpacing16,
                end = dimensions.hmrcSpacing16,
                bottom = dimensions.hmrcSpacing16
            )
        )
        BodyText(
            text = stringResource(R.string.longest_text),
            modifier = Modifier.padding(horizontal = dimensions.hmrcSpacing16)
        )
        Spacer(modifier = Modifier.height(dimensions.hmrcSpacing16))
        TextInputView(
            onInputValueChange = { },
            errorText = "",
            labelText = stringResource(id = R.string.text_input_placeholder_label),
            hintText = stringResource(id = R.string.text_input_placeholder_hint),
            placeholderText = stringResource(id = R.string.text_input_placeholder_placeholder),
        )
        PrimaryButton(
            modifier = Modifier.padding(horizontal = dimensions.hmrcSpacing16),
            text = stringResource(id = R.string.long_text),
            onClick = { }
        )
        IconButton(
            text = stringResource(id = R.string.button_icon_example),
            iconResId = R.drawable.ic_info,
            onClick = {}
        )
    }
}

@HmrcAllDevicePreview
@Composable
internal fun BottomSheetViewScreenPreview() {
    HmrcPreview {
        BottomSheetViewScreen()
    }
}
