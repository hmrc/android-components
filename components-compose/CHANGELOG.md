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

### Added

* `InformationMessageCardView` component.
* `DonutChartView` component.
* `Link` text style

### Changed

* Better dark mode support for `TextInputView` and `CurrencyInputView`

## [0.0.6] - 2024-04-25Z

### Changed

* Changed how SelectRowGroup works. It now takes a list of `SelectRowViewItem` to populate the rows, and requires a
selected item rather than relying on position.

## [0.0.4] - 2024-04-12Z

### Changed

* kotlinCompilerExtensionVersion = "1.4.2" -> "1.4.8"
* kotlin_version = "1.8.10" -> "1.8.22"
* androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1 -> 2.6.2
* androidx.lifecycle:lifecycle-runtime-compose:2.6.1 -> 2.6.2
* androidx.compose.runtime:runtime-livedata:1.4.3 -> 1.5.4
* androidx.core:core-ktx:1.10.1 -> 1.12.0
* androidx.lifecycle:lifecycle-runtime-ktx:2.6.1 -> 2.6.2
* androidx.activity:activity-compose:1.7.2 -> 1.8.2
* androidx.compose.material3:material3:1.1.1 -> 1.1.2
* androidx.navigation:navigation-fragment-ktx:2.6.0 -> 2.7.6
* androidx.navigation:navigation-ui:2.6.0 -> 2.7.6
* androidx.compose:compose-bom:2022.12.00 -> 2023.10.01
* com.google.android.material:material:1.8.0 -> 1.11.0
* androidx.compose.material:material:1.4.1 -> 1.5.4
* androidx.activity:activity-compose:1.7.2 -> 1.8.2
* androidx.core:core-ktx:1.10.1 -> 1.12.0
* lifecycleVersion updated to 2.6.2
* androidx.compose.runtime:runtime-livedata:1.4.2 -> 1.5.4

## [0.0.3] - 2024-01-02Z

Initial alpha release of the HMRC Android Component Library, containing an initial subsection of HMRC Components built with Jetpack Compose!

### Added

* Initial setup of `components-compose` module
* Add `HmrcTheme` with HMRC color palette and support for dark mode colors

* Initial setup of `sample-compose` module
* Add Compose Navigation Component and set up nav graph
* Wire in BottomNavigation and AppBar
* Add sample strings from existing component library
