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
        const val targetSdk = 34
        const val compileSdk = 34
        const val kotlinCompilerExtensionVersion = "1.4.8"
        const val jetpackNavigation = "2.7.6"
        const val lifecycleVersion = "2.6.2"
    }

    object Libs {
        const val compose_bom = "androidx.compose:compose-bom:2024.02.00"
        const val compose_foundation = "androidx.compose.foundation:foundation"
        const val compose_material = "androidx.compose.material3:material3"
        const val compose_material_android = "androidx.compose.material3:material3-android:1.2.0-beta01"
        const val compose_tooling = "androidx.compose.ui:ui-tooling"
        const val compose_ui = "androidx.compose.ui:ui"
        const val compose_uiTest = "androidx.compose.ui:ui-test-junit4"

        const val material = "com.google.android.material:material:1.11.0"
        const val androidx_material = "androidx.compose.material:material:1.5.4"
        const val androidX_activity_activityCompose = "androidx.activity:activity-compose:1.8.2"
        const val androidX_appCompat = "androidx.appcompat:appcompat:1.6.1"
        const val androidX_coreKtx = "androidx.core:core-ktx:1.12.0"
        const val androidX_lifecycle_runtimeKtx =
            "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleVersion}"
        const val androidX_navigation_fragment =
            "androidx.navigation:navigation-fragment-ktx:${Versions.jetpackNavigation}"
        const val androidX_navigation_ui =
            "androidx.navigation:navigation-ui-ktx:${Versions.jetpackNavigation}"
        const val androidX_navigation_navigationCompose =
            "androidx.navigation:navigation-compose:${Versions.jetpackNavigation}"
        const val androidX_lifecycle_viewModelCompose =
            "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.lifecycleVersion}"
        const val androidX_lifecycle_viewModelKtx =
            "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycleVersion}"
        const val androidX_lifecycle_liveDataCore =
            "androidx.lifecycle:lifecycle-livedata-core-ktx:${Versions.lifecycleVersion}"
        const val androidX_lifecycle_runtimeLiveData = "androidx.compose.runtime:runtime-livedata:1.5.4"
        const val androidx_lifecycle_runtimeCompose =
            "androidx.lifecycle:lifecycle-runtime-compose:${Versions.lifecycleVersion}"
        const val androidX_test_ext_junit = "androidx.test.ext:junit-ktx:1.1.5"
        const val androidX_test_espressoCore = "androidx.test.espresso:espresso-core:3.5.1"

        const val junit = "junit:junit:4.13.2"
    }
}
