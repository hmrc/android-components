# Changelog
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

<!--
Allowed headings:
- "Added" for new features.
- "Changed" for changes in existing functionality.
- "Deprecated" for soon-to-be removed features.
- "Removed" for now removed features.
- "Fixed" for any bug fixes.
- "Security" in case of vulnerabilities.
-->

## [Unreleased]

### Fixed

* `EditableListView` bug with content description and edit button using TalkBack

### Changed

* `EditableListView` changed to have public `editMode` getter and `setIconButtonClickListener` method.

## [4.5.0] - 2023-02-17

### Changed

* `EditableListView` changed to use `EditableListItemViewState`.
* Changes to `DonutChartView` animation and Donut percentage calculation.

### Additions

* Added new `MiniAdvertCardView` component.

## [4.4.1] - 2023-02-03

### Changed
* `DonutChartView` standard width reduced.

## [4.4.0] - 2023-02-02

### Additions

* `DonutChartView` now supports three segments and stripes.
*  Added new `EditableListView` component.

### Changed

The following libraries were updated:

* `com.android.tools.build:gradle:`: `7.0.3` -> `7.3.1`
* `org.jetbrains.kotlin:kotlin-gradle-plugin:`: `1.5.31` -> `1.7.20`
* `targetSdk`: `30` -> `33`
* `compileSdk`: `30` -> `33`
* `buildTools`: `30.0.3` -> `33.0.1`

* `androidx.annotation:annotation:`: `1.2.0` -> `1.5.0`
* `androidx.appcompat:appcompat:`: `1.2.0` -> `1.5.1`
* `androidx.recyclerview:recyclerview:`: `1.2.0` -> `1.2.1`
* `androidx.constraintlayout:constraintlayout:`: `2.0.4` -> `2.1.4`
* `androidx.lifecycle:lifecycle-common-java8:2.3.1`: removed
* `androidx.lifecycle:lifecycle-compiler:2.5.1`: added
* `androidx.core:core-ktx:`: `1.3.0` -> `1.9.0`
* `androidx.test:core-ktx:`: `1.3.0` -> `1.5.0`
* `androidx.test.ext:junit-ktx:`: `1.1.2` -> `1.1.5`
* `androidx.test.espresso:espresso-core:`: `3.3.0` -> `3.5.1`
* `androidx.test.espresso:espresso-contrib:`: `3.3.0` -> `3.5.1`
* `androidx.test:runner:`: `1.3.0` -> `1.5.2`
* `com.google.android.material:material:`: `1.3.0` -> `1.7.0`
* `junit:junit:`: `4.13` -> `4.13.2`

## [4.3.2] - 2023-01-09

### Changed

* When an error is displayed on a `SelectRowGroup` component, then the error content description will have the prefix "Error:"

## [4.3.1] - 2022-12-28

### Additions

* Added ability to give a whole row content description on `MultiColumnRow`s.

## [4.3.0] - 2022-11-25

### Additions

* Added `DonutChartView` component.

## [4.2.0] - 2022-07-15

### Changed

* Updated hmrc_secondary_button_ripple.xml to use high contrast alpha from Material Design.
* Added `hmrc_secondary_button_ripple` to public colour.

## [4.1.0] - 2022-07-06

### Changed

* Updated `materialCardViewStyle` to ensure all cards use the HMRC dark mode supported background color (`hmrc_white_background`)

## [4.0.1] - 2022-06-24

### Changed

* Updated `TabBarView` to fix bugs when the component is used with large text or long, dynamic tab titles.

## [4.0.0] - 2022-03-25

### Changed

* Updated `InformationMessageCardView` button so it has the ability to set the button to outline style using `isOutlineButton`. This mean `setHeadlineButtons` will need to provide `InformationMessageButton` instead of `SecondaryButton`.

## [3.22.0] - 2022-02-03

### Changed

* Updated `InformationMessageCardView` buttons so that the button `minHeight` is reset from the default 60dp to 0dp. This means that buttons now only occupy the vertical space that they need and no more.

## [3.21.0] - 2022-01-25

### Added

* `WarningView` has new attribute `defaultPadding` which removes the default padding from the component when set to false. When set to true, or not provided in the xml, component has default `8dp` padding.

### Changed

* Updated padding on `HeadlineCardView` so that if there are no children added then the bottom padding is the same as a normal card (16dp). If children are added, this padding is increased back to 24dp automatically.

## [3.20.0] - 2021-12-09

### Changed

* Updated `MenuPanelRowView` notifications to add support for 'New' badge.
To show the notification you now need to provide the type of notification as a parameter: `showNotification(notificationType: Notification)`
Notification types: `Count(val count: Int = 0)`, `New`, `None`.
Default for panel is `None` and this does not need to be set.
Default of `Count` just shows a dot, and a count value does not need to be provided, this is the default state of `showNotification()`.

* Refactored dependencies and updated the following:
	- kotlin version: 1.5.20 -> 1.5.31
	- hmrc spotless plugin: 1.0.0 -> 1.1.1
	- detekt: 1.6.0 -> 1.18.1

## [3.19.0] - 2021-11-08

* Updated `StatusCardView` to improve support for vectors than are more than 100dp wide

## [3.18.0] - 2021-10-22

* Added Java 11 support

## [3.17.1] - 2021-08-13

* Updated `hmrc_grey_1` colour to match the GOV palette 

## [3.17.0] - 2021-07-26

### Added

* Added `setOnClickListener(clickHandler: () -> Unit)` and `setChevronContentDescription(description: CharSequence)` methods to `HeadlineCardView`.

## [3.16.0] - 2021-07-16

### Added

* Exposed the `setTextFocusable(focusable: Boolean)` for use in `MultiColumnRow` and provided `isTextFocusable` for setting this in xml.

## [3.15.1] - 2021-07-01Z

* Updated `hmrc_yellow`

## [3.15.0] - 2021-05-28

### Removed

* Removed the `ExpandingRowView` component

## [3.14.3] - 2021-05-27

### Changed

* Improved keyboard navigation for `TextInputView`

## [3.14.2] - 2021-05-26

### Added

* Added `isScreenReaderEnabled()` extension for `Context` in `ContextExt.kt`
* Added `dpToPx()` extension for `Float` in `FloatExtensions.kt`

### Changed

* Improved announcement of errors for `TextInputView`
 
## [3.14.1] - 2021-05-21

### Changed

* Improved content description for `TextInputView` to prevent duplicating error announcements when counter is exceeded.

## [3.14.0] - 2021-05-17

### Added

* Added new methods to set click listeners and custom click content descriptions to each text view inside `MultiColumnRowView`:
	`setText1ClickAction(clickDescription: CharSequence? = null, listener: OnClickListener)`,
	`setText2ClickAction(clickDescription: CharSequence? = null, listener: OnClickListener)`,
	`setText3ClickAction(clickDescription: CharSequence? = null, listener: OnClickListener)`

### Changed

* Fixed typo in method name inside `MultiColumnRowView`:
	`setTextContentDescription(desc1: CharSequence?, desc2: CharSequence?, desc3: CharSequence?)`

### Removed

* Removed `setTextIsSelectable` method and xml attributes `text1IsSelectable`, `text2IsSelectable` and `text3IsSelectable` from `MultiColumnRowView`

## [3.13.0] - 2021-05-10

### Added

* Added ability to set error content descriptions on `TextInputView`

### Changed

* Improved content description for `TextInputView` to announce the error and counter status along with the hint on talkback.
* Upgrade target and compile sdk versions to 30
* Upgrade Gradle plugin to 4.1.3
* Upgrade Kotlin version to 1.4.20
* Upgrade Karumi shot version to 5.7.0
* Upgrade android x annotation version to 1.2.0
* Upgrade constraint layout version to 2.0.4
* Upgrade material version to 1.3.0
* Upgrade junit version to 4.13
* Upgrade dependencies used in sample app: app compat to 1.2.0, recyclerview to 1.2.0

## [3.12.0] - 2021-04-30

### Changed

* Updated `MenuPanelRowView` touch state colour

## [3.11.0] - 2021-04-13

### Added

* Added body text for `InformationMessageCardView`

## [3.10.0] - 2021-04-07
- Changed to Github packages for artefact storage.

## [3.9.0] - 2021-03-01

### Added

* Added ability for `InformationMessageCardView` to add a custom content description to headline

## [3.8.0] - 2021-02-04

### Changed

* Updated the color of all text hints from black to grey 1
* Removed content area from `InformationMessageCardView`

## [3.7.0] - 2021-01-21

### Changed

* Renamed `InformationMessageCardView` functions: `addHeadlineButtons()` -> `setHeadlineButtons()`, `addContentButtons()` -> `setContentButtons()`
* `setHeadlineButtons()` and `setContentButtons()` now clear existing buttons before adding new ones.

## [3.6.0] - 2020-12-08
### Changed
* Move to new Bintray reposiotry organisation `https://dl.bintray.com/hmrc-mobile/mobile-releases`

## [3.5.0] - 2020-12-02

### Added

* `MenuPanelRowView` component

## [3.4.10] - 2020-12-01

### Added

* screenshot tests

### Changed
* Made `InformationMessageCardView` headline background colours customisable

## [3.4.9] - 2020-11-06

### Added
* `Theme.Components.ActionBar` style to allow consuming apps to use action bar style
* `fastlane` release process

## [3.4.6] - 2020-09-10

### Fixed

* `TextInputView` clear button tint was incorrect

## [3.4.5] - 2020-09-10

### Changed

* `CurrencyInputView` currency symbol is now set using prefixText
* `TextInputView` clear button now uses material `endIcon` api as recommended
* Gradle plugin updated to 4.0.1
* Material library updated to 1.2.1
* Kotlin version updated to 1.4.0

### Removed

* `TextInputView.setDrawableLeft`

## [3.4.4] - 2020-09-07

### Fixed

* `InformationMessageCardView` became visible on rotation when visibility set to GONE

## [3.4.3] - 2020-08-24

### Fixed

* Removed default `type` for `InformationMessageCardView`

## [3.4.2] - 2020-07-27

### Changed

* `setAsAccessibilityHeading(isHeading: Boolean = true)` is now an extension function of `View`

## [3.4.1] - 2020-07-27

### Added

* Content description field for `body` of `StatusView` and `StatusCardView`

## [3.4.0] - 2020-07-27

### Added

* `InformationMessageCardView` component

## [3.3.0] - 2020-07-24

### Added

* `Heading3`, `Heading4` and `Heading5` to be used as visual headers for accessibility purposes.
* TextView extension for adding/removing a visual heading, `TextView.setAsAccessibilityHeading(isHeading: Boolean = true)`

### Changed

* The following components now have visual headings: `TitleBodyView`, `H4TitleBodyView`, `H5TitleBodyView`, `BoldTitleBodyView`, `HeadlineCardView`, `PrimaryCardView`, `MultiColumnRowView`, `StatusCardView` and `StatusView`
* Renamed package for TitleBodyView molecules from `uk.gov.hmrc.components.molecule.header` to `uk.gov.hmrc.components.molecule.titleBodyView`

## [3.2.2] - 2020-07-22

### Changed

* Made `SelectRowView` to one touch state for consistent ripple effect

## [3.2.1] - 2020-07-21

### Changed

* Made ripple background a public resource

## [3.2.0] - 2020-07-20

### Changed

* Renamed `Text.H6` style to `Text.Bold` and `H6TitleBodyView` to `BoldTitleBodyView`

## [3.1.9] - 2020-07-16

### Changed

* Change the following components touch state to blue: `SelectRowView`, `SummaryRowView`, `ExpandingRowView` and `TabBarView`

## [3.1.8] - 2020-07-02

### Fixed

* `TextInputView` was not saving its state correctly

### Changed

* Upgrade Gradle plugin to 4.0.0

## [3.1.7] - 2020-06-30

### Changed

* Updated `TextInputView` to ensure a unique id is applied to the `TextInputLayout` inside

## [3.1.6] - 2020-06-17

### Added

* `SummaryRowView.getTitle` function

## [3.1.5] - 2020-05-22

### Added

* `TextInputView` overrideHintContentDescription attribute for overriding the hint portion of the default content description

### Removed

* `TextInputView` contentDescription attribute that didn't work correctly

## [3.1.4] - 2020-05-21

### Added

* `SwitchRowView` attribute for setting the content description of the switch

## [3.1.3] - 2020-05-14

### Changed

* Renamed library drawables to avoid possible clashes

## [3.1.2] - 2020-05-06

### Changed

* Updated pink colour to improve contrast

### Fixed

* Fixed issue where text could not be set on `BulletedTextView`

## [3.1.1] - 2020-05-06

### Changed

* Increased size of `BulletedTextView` bullet points on Android P and above
* Updated tab item content descriptions to include position

## [3.1.0] - 2020-05-01

### Changed

* Changed style on `SecondaryButton` and `IconButton` to remove the inset added to MaterialButtons

## [3.0.10] - 2020-04-07

### Added

* Added dark mode yellow

## [3.0.9] - 2020-04-01

### Changed

* Removed rounded corners from Material Card components

## [3.0.8] - 2020-03-30

### Added

* Added ability to programmatically add text to a `BulletedTextView`

## [3.0.7] - 2020-03-27

### Fixed

* Fixed `WarningView` content description

## [3.0.6] - 2020-03-25

### Added

* Added ability to change text color, icon and icon tint on `WarningView`

## [3.0.5] - 2020-03-24

### Changed

* Removed divider from `SelectRowGroup`

## [3.0.4] - 2020-03-11

### Changed

* Upgrade Gradle plugin to 3.6.1
* Upgrade Kotlin version to 1.3.70

## [3.0.3] - 2020-02-10

### Added

* Added optional primary button, secondary button and info text to `StatusView`

### Changed

* Removed built in bottom margin on `SwitchRowView`
* `SwitchRowView` now extends `ConstraintLayout`
* `SwitchRowView` switch is now centered vertically, as per the designs`

## [3.0.2] - 2020-02-06

### Fixed

* Fixed `IconButton` icon size to 24dp

## [3.0.1] - 2020-02-06

### Fixed

* Fix for setting `IconButtonCardView` programmatically rather than with xml attribute

## [3.0.0] - 2020-02-05

### Changed

* Updated to Material Components 1.1.0
* Replaced `@style/Button.Primary` and `@style/Button.Primary` styles with `PrimaryButton` and `SecondaryButton` views
* Replaced `IconButtonView` with `IconButton`

### Added

* Added `@style/Theme.Components` theme

### Removed

* Removed `@style/Card` style. `MaterialCardView` should be used instead.

## [2.0.5] - 2020-01-30

### Fixed

* Reverted fix from 2.0.4 and fixed the layout bug in a simpler way

## [2.0.4] - 2020-01-28

### Fixed

* `ExpandingRowView` was not laid out correctly within `RecyclerView`

## [2.0.3] - 2020-01-24

### Fixed

* Clear `OnRowSelectedListener` when `SelectRowGroup` is detached from window

## [2.0.2] - 2020-01-22

### Changed

* Updated dark mode colours

## [2.0.1] - 2020-01-17

### Changed

* Updated dark mode colours

## [2.0.0] - 2020-01-15

### Added

* Support for dark mode

## [1.2.2] - 2019-12-19

### Changed

* Switch to use semantic versioning
* Automated publishing process

## [D2-1.2.1] (December 16, 2019)

### Changes

* Fix public resources to remove `hmrc_grey_4` and added `hmrc_yellow`

## Version D2-1.2.0 (December 16, 2019)

### Additions

* Added `hmrc_yellow`

### Breaking changes

* Remove `hmrc_grey_4`

### Changes

* Update component colours

## [D2-1.1.11] (November 15, 2019)

### Changes

* Added `setError` to `SelectRowGroup`

## [D2-1.1.10] (November 11, 2019)

### Changes

* Fixed padding on buttons when text enlarged

## [D2-1.1.9] (November 6, 2019)

### Changes

* Fixed bug where `SelectRowGroup` state was not restored correctly if first child was selected

## [D2-1.1.8] (October 23, 2019)

### Changes

* Added `android:maxLines` attribute to `TextInputView`

## [D2-1.1.7] (October 18, 2019)

### Changes

* Added `removeChildPadding()` to `InsetView`

## [D2-1.1.6] (October 3, 2019)

### Changes

* Updated Grey 4 in line with HMRC colour palette

## [D2-1.1.5] (September 19, 2019)

### Changes

* Added the ability to change the text color of a StatusView with the new `textColor` xml attribute

## [D2-1.1.4] (August 23, 2019)

### Changes

* Support setting `WarningView` text by StringRes
* Support setting `TextInputView` error text by StringRes

## [D2-1.1.3] (August 14, 2019)

### Changes

* Fixed bug where `SummaryRowView` title text did not default to H6 style when AttributeSet not supplied

## [D2-1.1.2] (August 9, 2019)

### Changes

* Added `rowStyle`, `titleTextAppearance` and `titleMaxLines` attributes to SummaryRowView

## [D2-1.1.1] (August 7, 2019)

### Changes

* Secondary button text style is no longer bold

## [D2-1.1.0] (July 30, 2019)

### Additions

* Added SelectRowGroup and SelectRowView

## [D2-1.0.1] (July 25, 2019)

### Changes

* Remove regex validation from `CurrencyInputView`. Invalid currency amounts should be handled by the client

## [D2-1.0.0] (July 12, 2019)

### Additions

* Added TabBarView

### Changes

* Allow setting null values for `text` and `contentDescription` on `MultiColumnRowView`

## [D1-7.1.1] (June 25, 2019)

### Changes

* Made the `readerTrait` attribute of `SummaryRowView` public so it can be set via code.

## [D1-7.1.0] (June 24, 2019)

### Additions

* Added `WarningView` component

## [D1-7.0.2] (June 10, 2019)

### Additions

* Added content description support to `ExpandingRowView` and `HeadlineCardView`

## [D1-7.0.1] (June 7, 2019)

### Additions

* Added `getError` to `TextInputView`

### Changes

* Refactored to ensure the expand icon and accessibility message reflect the `expanded` value in `ExpandingRowView`.

## [D1-7.0.0] (June 4, 2019)

### Breaking changes

* Updated the dependencies to use the Android X libraries

### Changes

* Fixed bug where long press was triggered when clicking on clear text button on `TextInputView`
* Exposed the `expanded` field in `ExpandingRowView` to enable it to be set programmatically.

## [D1-6.1.1] (May 20, 2019)

### Additions

* Added `hmrc_spacing_32`, `card_elevation` and `ic_pound_sign` to public resources.

## [D1-6.1.0] (May 17, 2019)

### Additions

* Added 'getText()' method to `TextInputView`
* Handle saving/restoring state for `TextInputView`
* Allow setting style for `SwitchRowView` title

### Changes

* `ExpandingRowView` title and subtitle now split onto two lines if text is sufficiently large

## [D1-6.0.0] (Apr 23, 2019)

### Breaking changes

* Removed the `body` attribute from the `HeadlineCardView`. Body text should now be added as a child view

## [D1-5.4.0] (Apr 15, 2019)

* Removed superfluous ViewGroups from some components

## [D1-5.3.0] (Mar 29, 2019)

### Additions

* Added `text1IsSelectable`, `text2IsSelectable` and `text3IsSelectable` options to `MultiColumnRowView`

## [D1-5.2.1] (Mar 22, 2019)

### Changes

* Added `imeOptions`, `inputType` and `maxLength` attributes to `TextInputView`
* Fixed left spacing issue for IconButtonView when icon is removed

## [D1-5.2.0] (Mar 13, 2019)

### Additions

* Added `setButtonAccessibilityMessage` method to SummaryRowView.
* Added `BulletedTextView`
* Updated `MultiColumnRowView` to allow distinct styles for columns
* Fixed layout bug for `ExpandingRowView` with no icon
* Made `Divider` style public

## [D1-5.1.0] (Mar 06, 2019)

### Additions

* Added `H6TitleBodyView`

### Changes

* Updated `SwitchRowView` to use `H6TitleBodyView` instead of `H5TitleBodyView`
* Converted methods with parameters of type `String` to `CharSequence`
* Moved `removeChildPadding` method and layout attribute `childPadding` from `PrimaryCardView`, `HeadlineCardView` and `StatusCardView` to `DynamicCardView`

## [D1-5.0.1] (Feb 12, 2019)

### Changes

* Added `childPadding` attribute to `StatusCardView`.
* Fixed margins on `PrimaryCardView`, `HeadlineCardView` and `StatusCardView` on pre-Oreo devices.

## [D1-5.0.0] (Feb 5, 2019)

### Breaking changes

* Removed the `StatusButtonCardView` organism. The `StatusCardView` can now have any additional body views.

## [D1-4.1.1] (Jan 31, 2019)

### Changes

* Refactored `ExpandingRowView`

## [D1-4.1.0] (Jan 28, 2019)

### Changes

* Removed unused `insetText` view attribute. None of the components use this attribute.
If you intend to set the text on a InsetTextView please use app:text instead.

* You can now add expansion listeners to `ExpandingRowView` to detect when the expansion state (expanded or collapsed) changes.
Prior to this, any onClickListeners set on a `ExpandingRowView` component would be ignored.

* SummaryRowView now has `readerTrait` attribute for changing the way it is read out by TalkBack.

## [D1-4.0.0] (Jan 14, 2019)

### Breaking changes

* `InsetView` now represents a generic view with an inset, which can have content nested inside it.
This will *no longer have the text functionality as before*. For this functionality you should use `InsetTextView`

### Additions

* Added `InsetTextView` which is a preconfigured version of `InsetView` which has a nested TextView and
an xml attribute to quickly set the text. Users of `InsetView` in prior versions should update usages
of `InsetView` to `InsetTextView` to retain existing existing functionality

## [D1-3.0.0] (Jan 3, 2019)

### Breaking changes

* Rename the `SwitchRowView` attributes from `subtitle` to `body`, and `showSubtitle` to `showBody`. The method `showSubtitle()` has also been renamed to `showBody()`.
* Removed the `PrimaryInsetCardView` and `PrimaryButtonCardView` organisms.
* Removed the `body` attribute from the `PrimaryCardView`. Instead the view now can have any custom body views.
* Removed the `HeadlineInsetCardView` and `HeadlineButtonCardView` organisms. The `HeadlineCardView` can now have any additional body views.

### Changes

* `SummaryRowView` now has methods to set a custom content description on the chevron for accessibility, or a custom accessibility message for views with only one row.


## [D1-2.0.1] (Dec 19, 2018)

### Changes

* `CurrencyInputView` is set to max 2 decimal points and includes decimalEnabled attribute
* Fixed a bug in `CurrencyInputView` where setting text would result in clearing the left drawable
* Removed `TextInputViewModel` from `TextInputView`. You can change functionality of this component via xml attributes or the relevant setters


## [D1-2.0.0] (Dec 13, 2018)

### Additions

* `SeparatedViewContainer` component
* Divider via `@style/divider` and `@drawable/divider`
* `CurrencyInputView` component

### Breaking changes

* Removed SwitchRowView attributes and setters for `showBottomDivider` and `showTopDivider`
* Moved `TextInputView` package from `textinput` to `input`, so imports will change from:
`uk.gov.hmrc.components.molecule.textinput.TextInputView` to `uk.gov.hmrc.components.molecule.input.TextInputView`
* Changed `InsetView.setText()` parameter type from `String` to `CharSequence`

### Changes

* Reduce margin above SecondaryButton on `PrimaryButtonCardView`, `StatusButtonCardView` and `HeadlineButtonCardView`
* Increase margins between icon, title and body on `StatusCard`


## [1.1.0] (Dec 04, 2018)

### Additions

* SummaryRowView component
* SwitchRowView component

### Breaking changes

* `IconButton` molecule renamed to `IconButtonView`.
* `TextInput` organism renamed to `TextInputView`, and demoted to a molecule. New package is `uk.gov.hmrc.components.molecule.textinput`.
* All components which previously had a `description` custom attribute now have a `body` custom attribute instead.
* `H4TitleBodyView` and `H5TitleBodyView` molecules custom attribute `subtitle` renamed to `body`.
* `HeadlineCardView` component's custom attributes `subtitle` renamed to `title`, `title` renamed to `headline`, and `description` renamed to `body`.
* Removed padding on `StatusView`.
* Renamed `setButtonGravity()` method in `StatusButtonCardView` to `setButtonTextGravity()`.
* Renamed the `MultiColumnRow` custom attributes from `startText`, `centreText` and `endText` to `text1`, `text2` and `text3`.

### Changes

* Changes to the Sample app examples, to provide a range of useful examples for testing.
* Removed unused assets (`ic_info_outline.xml`, `ic_maintenance`, `ic_warning`).
* Added custom attributes `buttonStyle` and `buttonTextGravity` to `PrimaryButtonCardView`, `StatusButtonCardView` and `HeadlineButtonCardView`.


## [1.0.0] (Nov 22, 2018)

Initial release!
