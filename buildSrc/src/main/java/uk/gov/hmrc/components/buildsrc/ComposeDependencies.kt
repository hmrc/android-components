/*
 * Copyright 2021 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package uk.gov.hmrc.components.buildsrc

class ComposeDependencies {
    object Versions {
        const val minSdk = 23
        const val targetSdk = 31
        const val compileSdk = 31
        const val compose = "1.0.5"
        const val kotlinComposeCompiler = "1.5.21"
    }

    object Libs {
        const val material = "com.google.android.material:material:1.4.0"

        const val androidX_coreKtx = "androidx.core:core-ktx:1.7.0"
        const val androidX_activity_activityCompose = "androidx.activity:activity-compose:1.4.0"
        const val androidX_appCompat = "androidx.appcompat:appcompat:1.4.0"

        const val androidX_compose_foundation = "androidx.compose.foundation:foundation:${Versions.compose}"
        const val androidX_compose_material = "androidx.compose.material:material:${Versions.compose}"
        const val androidX_compose_tooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
        const val androidX_compose_ui = "androidx.compose.ui:ui:${Versions.compose}"
        const val androidX_compose_uiTest = "androidx.compose.ui:ui-test-junit4:${Versions.compose}"

        const val androidX_lifecycle_runtimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:2.4.0"
        const val androidX_navigation_navigationCompose = "androidx.navigation:navigation-compose:2.4.0-beta02"
        const val androidX_test_ext_junit = "androidx.test.ext:junit-ktx:1.1.3"
        const val androidX_test_espressoCore = "androidx.test.espresso:espresso-core:3.4.0"

        const val junit = "junit:junit:4.13.2"
    }
}
