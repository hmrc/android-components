fastlane documentation
----

# Installation

Make sure you have the latest version of the Xcode command line tools installed:

```sh
xcode-select --install
```

For _fastlane_ installation instructions, see [Installing _fastlane_](https://docs.fastlane.tools/#installing-fastlane)

# Available Actions

## Android

### android test

```sh
[bundle exec] fastlane android test
```

Runs all the tests

### android check

```sh
[bundle exec] fastlane android check
```

Runs all checks

### android tag_release

```sh
[bundle exec] fastlane android tag_release
```

Takes a release tag and updates changelog for the components package

### android release_test_version

```sh
[bundle exec] fastlane android release_test_version
```

Starts the release workflow on CI for the components-test package

### android publish

```sh
[bundle exec] fastlane android publish
```

Publishes components library

### android publish_test

```sh
[bundle exec] fastlane android publish_test
```

Publishes components test library

### android screenshot_record

```sh
[bundle exec] fastlane android screenshot_record
```

Starts an emulator and records new screenshots

### android connectedCheck

```sh
[bundle exec] fastlane android connectedCheck
```

Starts an emulator and runs all device checks

### android screenshot_diff

```sh
[bundle exec] fastlane android screenshot_diff
```

Runs the screenshot comparison

### android release_components_compose_version

```sh
[bundle exec] fastlane android release_components_compose_version
```

Takes a release tag and updates changelog for the components-compose package

### android jemmaTest

```sh
[bundle exec] fastlane android jemmaTest
```

Test

----

This README.md is auto-generated and will be re-generated every time [_fastlane_](https://fastlane.tools) is run.

More information about _fastlane_ can be found on [fastlane.tools](https://fastlane.tools).

The documentation of _fastlane_ can be found on [docs.fastlane.tools](https://docs.fastlane.tools).
