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
or alternatively using `brew cask install fastlane`

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

----

This README.md is auto-generated and will be re-generated every time [fastlane](https://fastlane.tools) is run.
More information about fastlane can be found on [fastlane.tools](https://fastlane.tools).
The documentation of fastlane can be found on [docs.fastlane.tools](https://docs.fastlane.tools).
