fastlane documentation
================
# Installation

Make sure you have the latest version of the Xcode command line tools installed:

```
xcode-select --install
```

Install _fastlane_ using
```
[sudo] gem install fastlane -NV
```
or alternatively using `brew install fastlane`

# Available Actions
## Android
### android test
```
fastlane android test
```
Runs all the tests
### android check
```
fastlane android check
```
Runs all checks
### android tag_release
```
fastlane android tag_release
```
Takes a release tag and updates changelog
### android release_test_version
```
fastlane android release_test_version
```
Starts the release workflow on CI
### android publish
```
fastlane android publish
```
Publishes components library
### android publish_test
```
fastlane android publish_test
```
Publishes components test library
### android screenshot_record
```
fastlane android screenshot_record
```
Starts an emulator and records new screenshots
### android connectedCheck
```
fastlane android connectedCheck
```
Starts an emulator and runs all device checks
### android screenshot_diff
```
fastlane android screenshot_diff
```
Runs the screenshot comparison

----

This README.md is auto-generated and will be re-generated every time [_fastlane_](https://fastlane.tools) is run.
More information about fastlane can be found on [fastlane.tools](https://fastlane.tools).
The documentation of fastlane can be found on [docs.fastlane.tools](https://docs.fastlane.tools).
