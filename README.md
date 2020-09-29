# HMRC Components Library for Android [![Build Status](https://app.bitrise.io/app/08593f02ad3e13fe/status.svg?token=BYIJbA6f__HFQ0q3B85V4w&branch=master)](https://app.bitrise.io/app/08593f02ad3e13fe)

Build applications using components with the HMRC look and feel.

# Getting Started

First add Bintray to the list of repositories in `build.gradle`:

```xml
repositories {
    maven {
        url "https://hmrc.bintray.com/mobile-releases"
    }
}
```

Then add the following to your list of dependencies:

```xml
implementation 'uk.gov.hmrc.components:components:{version}'
androidTestImplementation 'uk.gov.hmrc.components:components-test:{version}'
```

The latest release can be found [here](https://github.com/hmrc/android-components/releases).

## Theming

Changing your app theme to inherit from `Theme.Components` is the recommended approach:

```xml
<style name="Theme.MyApp" parent="@style/Theme.Components">
...
</style>
```

If you cannot change your theme to inherit from `Theme.Components`, inherit from a Material Components theme and set the following attributes:

```xml
<style name="Theme.MyApp" parent="@style/Theme.MaterialComponents.DayNight">
  <item name="colorError">@color/hmrc_red</item>
  <item name="materialTextButtonStyle">@style/MaterialTextButtonNoInset</item>
  <item name="materialIconButtonStyle">@style/MaterialTextIconButtonNoInset</item>
  <item name="materialCardViewStyle">@style/Widget.Components.CardView</item>
</style>
```

# Components

Components fall into one of three types:

* [Atoms](#Atoms) - individual UI element (e.g. label, button)
* [Molecules](#Molecules) - reusable combination of atoms (e.g. Body label + H3 label)
* [Organisms](#Organisms) - complex view, with multiple molecules and/or atoms, may contain functionality (e,g, Headline Card pictured)

## Atoms

### Primary Button

```xml
<uk.gov.hmrc.components.atom.button.PrimaryButton
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:text="Primary Button" />
```

### Secondary Button

```xml
<uk.gov.hmrc.components.atom.button.SecondaryButton
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:text="Secondary Button" />
```

### Icon Button

```xml
<uk.gov.hmrc.components.atom.button.IconButton
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:text="Icon Button"
    app:icon="@drawable/icon" />
```

### H3

```xml
<TextView
    style="@style/Text.H3"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:text="H3 Text" />
```

### Heading3

A H3 styled TextView identified as a visual heading for accessibility purposes.

```xml
<uk.gov.hmrc.components.atom.header.Heading3
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:text="Heading3 Text" />
```

### H4

```xml
<TextView
    style="@style/Text.H4"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:text="H4 Text" />
```

### Heading4

A H4 styled TextView identified as a visual heading for accessibility purposes.

```xml
<uk.gov.hmrc.components.atom.header.Heading4
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:text="Heading4 Text" />
```

### H5

```xml
<TextView
    style="@style/Text.H5"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:text="H5 Text" />
```

### Heading5

A H5 styled TextView identified as a visual heading for accessibility purposes.

```xml
<uk.gov.hmrc.components.atom.header.Heading5
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:text="Heading5 Text" />
```

### Bold

```xml
<TextView
    style="@style/Text.Bold"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:text="Bold Text" />
```

### Body

```xml
<TextView
    style="@style/Text.Body"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:text="Body Text" />
```

### Info

```xml
<TextView
    style="@style/Text.Info"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:text="Info Text" />
```

### Link

```xml
<TextView
    style="@style/Text.Link"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:text="Link Text" />
```

### Error

```xml
<TextView
    style="@style/Text.Error"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:text="Error Text" />
```

### Bulleted

```xml
<uk.gov.hmrc.components.atom.bullet.BulletedTextView
    style="@style/Text.Body"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:text="Bullet Text" />
```

### Divider

```xml
<View style="@style/Divider" />
```

or

`@drawable/divider` if you need a drawable resource

## Molecules

### Inset View

A view with an inset on the left side. Nest content inside of this view to display it
on the right side of the inset  

```xml
<uk.gov.hmrc.components.molecule.inset.InsetView
    android:layout_width="match_parent"
    android:layout_height="wrap_content">

    <!-- Nested content goes here -->

</uk.gov.hmrc.components.molecule.inset.InsetView>
```

### Inset Text View

A preconfigured version of Inset View, where a nested text view is ready for immediate use via
the xml text attribute

```xml
<uk.gov.hmrc.components.molecule.inset.InsetTextView
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:text="@string/inset_text" />
```

### Multi Column Row View

```xml
<uk.gov.hmrc.components.molecule.item.MultiColumnRowView
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:text1="@string/text1"
    app:text2="@string/text2"
    app:text3="@string/text3"
    app:text1ContentDescription="@string/text1_desc"
    app:text2ContentDescription="@string/text2_desc"
    app:text3ContentDescription="@string/text3_desc"
    app:text1IsSelectable="false"
    app:text2IsSelectable="true"
    app:text3IsSelectable="true"
    app:textStyle="@style/Text.Body"
    app:textStyle2="@style/Text.Body"
    app:textStyle3="@style/Text.Bold"
    app:text1Heading="true" />
```

This component can display a row with one, two or three columns of equal spacing, using the number of strings you provide.

The text style and heading can also be configured programmatically:

```kotlin
multi_column_row.setTextStyle(R.style.Text_Bold)
multi_column_row.setText1AsHeading(true)
```

### Text Input View

```xml
<uk.gov.hmrc.components.molecule.input.TextInputView
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:text="@string/text"
    app:overrideHintContentDescription="@string/content_description"
    app:counterEnabled="true"
    app:counterMaxLength="@integer/max_length"
    app:hintText="@string/hint_text" />
```

`TextInputView` represents an input for text. This includes the following features:
 - Hint text
 - Custom content description
 - Max length limits
 - Character counters

### Currency Input View

```xml
<uk.gov.hmrc.components.molecule.input.CurrencyInputView
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:text="@string/text"
    app:contentDescription="@string/content_description"
    app:counterEnabled="true"
    app:counterMaxLength="@integer/max_length"
    app:hintText="@string/hint_text"
    app:decimalEnabled="false" />
```

`CurrencyInputView` represents an input for currency. This includes the following features:
- Prefix text for currency
- Preset keyboard input for currency
- Preset disabled character counters
- Hint text
- Custom content description
- Max length limits
- Enable or disable decimals

### Switch Row

```xml
<uk.gov.hmrc.components.molecule.item.SwitchRowView
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:title="@string/title"
    app:body="@string/body"
    app:showBody="true"
    app:checked="true"
    app:switchContentDescription="@string/content_description" />
```

This component can display a row with a title, body and a switch.

The body can be hidden by calling `showBody(false)` - it is visible by default.

You can set a listener for when the switch state has changed by calling:
`setCheckedChangedListener()`

Example usage:

```kotlin
switch_row_example_1.setEnabledListener { Toast.makeText(context, "Example 1 state: $it", Toast.LENGTH_SHORT).show() }
```

### H4 Title Body View

```xml
<uk.gov.hmrc.components.molecule.header.H4TitleBodyView
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:title="@string/title"
    app:body="@string/body" />
```

### H5 Title Body View

```xml
<uk.gov.hmrc.components.molecule.header.H5TitleBodyView
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:title="@string/title"
    app:body="@string/body" />
```

### Bold Title Body View

```xml
<uk.gov.hmrc.components.molecule.header.BoldTitleBodyView
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:title="@string/title"
    app:body="@string/body" />
```

### Status View

```xml
<uk.gov.hmrc.components.molecule.status.StatusView
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:body="@string/status_card_placeholder_body"
    app:bodyContentDesc="@string/status_card_placeholder_body_content_desc"
    app:icon="@drawable/ic_maintenance"
    app:iconTintColor="@color/hmrc_grey_1"
    app:title="@string/status_card_placeholder_title"
    app:textColor="@color/hmrc_green_1"
    app:primaryButtonText="@string/status_card_placeholder_primary_button"
    app:secondaryButtonText="@string/status_card_placeholder_secondary_button"
    app:infoText="@string/status_card_placeholder_info_text" />
```

```kotlin
status.setBodyGravity(Gravity.START) // defaults to CENTER if not set
status.onPrimaryButtonClickedListener = { // do something }
status.onSecondaryButtonClickedListener = { // do something }
```

### Warning View

```xml
<uk.gov.hmrc.components.molecule.warning.WarningView
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:text="@string/warning_placeholder_title"
    app:textColor="@color/hmrc_black"
    app:icon="@drawable/ic_warning"
    app:iconTintColor="@color/hmrc_black"/>
```

### Tab Bar View

```xml
<uk.gov.hmrc.components.molecule.tabbar.TabBarView
    style="@style/TabBar"
    android:layout_width="match_parent"
    android:layout_height="wrap_content" />
```

There are 2 styles available for the Tab Bar, `TabBar` and `TabBar.Dark`. The component extends the `TabLayout` Android class, so you can add tabs using either the `TabLayout.addTab` methods or by using 'TabLayout.setUpWithViewPager'.

### Select Row View

```xml
<uk.gov.hmrc.components.molecule.selectrow.SelectRowGroup
    android:id="@+id/select_row_tick_group"
    android:layout_width="match_parent"
    android:layout_height="wrap_content">

    <uk.gov.hmrc.components.molecule.selectrow.SelectRowView
        android:id="@+id/select_row_tick_1"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:button="@drawable/select_row_tick"
        app:text="First row" />

    <uk.gov.hmrc.components.molecule.selectrow.SelectRowView
        android:id="@+id/select_row_tick_2"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:button="@drawable/select_row_tick"
        app:text="Second row"/>

</uk.gov.hmrc.components.molecule.selectrow.SelectRowGroup>
```

In order to be notified of a row being selected, `SelectRowView`s should be a direct child of a `SelectRowGroup` and a listener should be set on the `SelectRowGroup` using `setOnRowSelectedListener(listener: OnRowSelectedListener?)`
## Organisms

### Headline Card View

```xml
<uk.gov.hmrc.components.organism.headline.HeadlineCardView
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:title="@string/title"
    app:headline="@string/headline">

    <!-- Add optional child views to the card here -->

</uk.gov.hmrc.components.organism.headline.HeadlineCardView>
```

If you want to explicitly state the padding for the child views (eg. for secondary buttons) then use the `removeDefaultChildPadding()` method to remove any default padding from the children, or in xml set `app:childPadding="false"`.


### Status Card View

```xml
<uk.gov.hmrc.components.organism.status.StatusCardView
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:body="@string/status_card_placeholder_body"
    app:bodyContentDesc="@string/status_card_placeholder_body_content_desc"
    app:icon="@drawable/ic_maintenance"
    app:iconTintColor="@color/hmrc_grey_1"
    app:title="@string/status_card_placeholder_title">

    <!-- Add optional child views to the card here -->

</uk.gov.hmrc.components.organism.status.StatusCardView>
```

```kotlin
status_card.setBodyGravity(Gravity.START) // defaults to CENTER if not set
```

If you want to explicitly state the padding for the child views (eg. for secondary buttons) then use the `removeDefaultChildPadding()` method to remove any default padding from the children, or in xml set `app:childPadding="false"`.

### Primary Card View

```xml
<uk.gov.hmrc.components.organism.primary.PrimaryCardView
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:title="@string/title">

    <!-- Add optional child views to the card here -->

</uk.gov.hmrc.components.organism.primary.PrimaryCardView>
```

If you want to explicitly state the padding for the child views (eg. for secondary buttons) then use the `removeDefaultChildPadding()` method to remove any default padding from the children, or in xml set `app:childPadding="false"`.

### Expanding Row View

```xml
<uk.gov.hmrc.components.organism.expandable.ExpandingRowView
    android:id="@+id/expandable"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:title="Title"
    app:subtitle="Subtitle"
    app:icon="@drawable/ic_icon"
    app:clickTogglesExpansion="false"
    app:expanded="false">

    <!-- Content to expand or collapse -->

</uk.gov.hmrc.components.organism.expandable.ExpandingRowView>
```

You can then add an expansion listener to detect when the expansion state (expanded or collapsed) changes via:
```kotlin
expandable.addExpansionListener {
    Toast.makeText(context!!, if (it) "Expanded" else "Collapsed", Toast.LENGTH_SHORT).show()
}
```

You may also remove any previously set listener via:
```kotlin
expandable.removeExpansionListener(listener)
```

or remove all listeners via:
```kotlin
expandable.clearExpansionListeners()
```

### Icon Button Card View

```xml
<uk.gov.hmrc.components.organism.card.IconButtonCardView
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:accessibilityMessage="@string/accessibility_message"
    app:icon="@drawable/icon"
    app:title="@string/title" />
```

```kotlin
icon_button_card.setOnClickListener { // do something on click }
```

### Summary Row View

```xml
<uk.gov.hmrc.components.organism.summary.SummaryRowView
    android:id="@+id/summary_row_placeholder"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    app:readerTrait="info"
    app:rowStyle="@style/summary_row_style"
    app:title="@string/summary_row_placeholder_title"
    app:titleTextAppearance="@style/summary_row_title_style"
    app:titleMaxLines="2" />
```

This component contains a title, which can be set via xml or directly in the code, and a list of MultiColumnRow molecules, set via code.

```kotlin
val placeholderRow = MultiColumnRowView(context)
placeholderRow.setText(
    getString(R.string.summary_row_placeholder_row1_text1),
    getString(R.string.summary_row_placeholder_row1_text2),
    getString(R.string.summary_row_placeholder_row1_text3))
summary_row_placeholder.setRows(arrayListOf(placeholderRow))
summary_row_placeholder.setOnClickListener { // do something on click }
summary_row_placeholder.setChevronContentDescription(getString(R.string.accessibility_message)
summary_row_placeholder.setAccessibilityMessage(getString(R.string.accessibility_message))
```

#### Accessibility
If the Summary Row is to be used as a clickable button in the app, then you can use the `setButtonAccessibilityMessage(buttonText, action)` method to set the announcement you want read out in the form "[buttonText]; button. Double tap to [action]".
Note: This method should be used with the `simple` readerTrait defined. There may be duplicate talkback announcements if the `info` readerTrait is selected.

The `readerTrait` attribute changes the way the component is read out by TalkBack.
Note: If setting readerTrait programmatically instead of in an xml file, this attribute needs to be set before setRows() is called.

- `info` - Specifies that the row will be read out element by element when read by TalkBack. This is the default trait.

- `simple` - Specifies that the row will be read out as one element by TalkBack.

### Separated View Container

```xml
<uk.gov.hmrc.components.container.SeparatedViewContainer
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="vertical">

    <TextView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Item 1" />

    <TextView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Item 2" />

    <TextView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Item 3" />

</uk.gov.hmrc.components.container.SeparatedViewContainer>
```

This component is a container view that is essentially a custom LinearLayout.
Child views inside this layout will have dividers applied between them.

You can change the divider via:
- XML `android:divider="@drawable/divider"`: see https://developer.android.com/reference/android/widget/LinearLayout#attr_android:divider
or
- By calling `setDividerDrawable()` in code: see https://developer.android.com/reference/android/widget/LinearLayout.html#setDividerDrawable(android.graphics.drawable.Drawable)

By default the divider will be `@drawable/divider`. If you specify a divider, that will override the default divider.

You can change the dividers that are shown via:
- XML `android:showDividers="beginning`, `android:showDividers="middle`, `android:showDividers="end"`
or
- By calling `setShowDividers()` in code: see https://developer.android.com/reference/android/widget/LinearLayout#setShowDividers(int)
With the options listed in https://developer.android.com/reference/android/widget/LinearLayout#SHOW_DIVIDER_BEGINNING

If you do not set `showDividers` via XML or code, it will default to show beginning, middle and end - so all the dividers.
If you specify SHOW_DIVIDER_NONE as per https://developer.android.com/reference/android/widget/LinearLayout#SHOW_DIVIDER_NONE, then it will revert to default behaviour as mentioned above.

If you need padding on your dividers, you can set `android:dividerPadding="@dimen/hmrc_spacing_16"` in the xml.

### Information Message Card View

```xml
<uk.gov.hmrc.components.organism.information.InformationMessageCardView
    android:id="@+id/info_message_placeholder"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_margin="@dimen/hmrc_spacing_16"
    app:headline="@string/info_message_placeholder_headline"
    app:headlineIcon="@drawable/ic_info"
    app:type="urgent"
    app:title="@string/info_message_placeholder_title"
    app:body="@string/info_message_placeholder_body" />
```

Optionally, buttons can be added to the headline section using `addHeadlineButtons(buttons: List<SecondaryButton>)` or to the content section with `addContentButtons(buttons: List<Button>)`.

## Colours

| Name | Light | Dark | Usage |
| ------- | ----- | --------- | ----- |
| Black | ![#0B0C0C](https://placehold.it/15/0B0C0C/000000?text=+) |![#FFFFFF](https://placehold.it/15/FFFFFF/000000?text=+) | @color/hmrc_black |
| White | ![#FFFFFF](https://placehold.it/15/FFFFFF/000000?text=+) | ![#0B0C0C](https://placehold.it/15/0B0C0C/000000?text=+) | @color/hmrc_white |
| Green 1 | ![#00703C](https://placehold.it/15/00703C/000000?text=+) | ![#69B134](https://placehold.it/15/69B134/000000?text=+) | @color/hmrc_green_1 |
| Green 2 | ![#85994B](https://placehold.it/15/85994B/000000?text=+) | ![#28A197](https://placehold.it/15/28A197/000000?text=+) | @color/hmrc_green_2 |
| Blue | ![#1D70B8](https://placehold.it/15/1D70B8/000000?text=+) | ![#5BC0C6](https://placehold.it/15/5BC0C6/000000?text=+) | @color/hmrc_blue |
| Teal | ![#28A197](https://placehold.it/15/28A197/000000?text=+) | ![#28A197](https://placehold.it/15/28A197/000000?text=+) | @color/hmrc_teal |
| Red | ![#D4351C](https://placehold.it/15/D4351C/000000?text=+) | ![#EB66CA](https://placehold.it/15/EB66CA/000000?text=+) | @color/hmrc_red |
| Grey 1 | ![#6F777B](https://placehold.it/15/6F777B/000000?text=+) | ![#6F777B](https://placehold.it/15/B1B4B6/000000?text=+) | @color/hmrc_grey_1 |
| Grey 2 | ![#B1B4B6](https://placehold.it/15/B1B4B6/000000?text=+) | ![#B1B4B6](https://placehold.it/15/6F777B/000000?text=+) | @color/hmrc_grey_2 |
| Grey 3 | ![#F3F2F1](https://placehold.it/15/F3F2F1/000000?text=+) | ![#0B0C0C](https://placehold.it/15/0B0C0C/000000?text=+) | @color/hmrc_grey_3 |
| Pink | ![#CA2B75](https://placehold.it/15/CA2B75/000000?text=+) | ![#BB94FF](https://placehold.it/15/BB94FF/000000?text=+) | @color/hmrc_pink |
| Yellow | ![#FFDD00](https://placehold.it/15/FFDD00/000000?text=+) | ![#FEFF4F](https://placehold.it/15/FEFF4F/000000?text=+) | @color/hmrc_yellow |
| White Background | ![#FFFFFF](https://placehold.it/15/FFFFFF/000000?text=+) | ![#262626](https://placehold.it/15/262626/000000?text=+) | @color/hmrc_white_background |
| Always Black | ![#000000](https://placehold.it/15/000000/000000?text=+) | ![#000000](https://placehold.it/15/000000/000000?text=+) | @color/hmrc_always_black |
| Always White | ![#FFFFFF](https://placehold.it/15/FFFFFF/000000?text=+) | ![#FFFFFF](https://placehold.it/15/FFFFFF/000000?text=+) | @color/hmrc_always_white |

## Spacing

| Spacing  | Usage |
| ------- | ----- |
| 4dp | @dimen/hmrc_spacing_4 |
| 8dp | @dimen/hmrc_spacing_8 |
| 16dp | @dimen/hmrc_spacing_16 |
| 24dp | @dimen/hmrc_spacing_24 |
| 32dp | @dimen/hmrc_spacing_32 |
| 48dp | @dimen/hmrc_spacing_48 |

# Components Test

The test library includes custom `ViewMatchers` and `ViewActions` for easier testing of HMRC components using espresso.
