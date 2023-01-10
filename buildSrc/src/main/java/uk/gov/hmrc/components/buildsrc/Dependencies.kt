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

class Dependencies {
    object Versions {
        const val minSdk = 23
        const val targetSdk = 33
        const val compileSdk = 33
        const val buildTools = "30.0.3"
        const val kotlin = "1.5.31"
    }

    object Libs {
        const val kotlin_stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"

        const val androidX_annotation = "androidx.annotation:annotation:1.2.0"
        const val androidX_appcompat = "androidx.appcompat:appcompat:1.2.0"
        const val androidX_recyclerview = "androidx.recyclerview:recyclerview:1.2.0"
        const val androidX_constraintlayout = "androidx.constraintlayout:constraintlayout:2.0.4"
        const val androidX_lifecycle = "androidx.lifecycle:lifecycle-common-java8:2.3.1"
        const val androidX_core_ktx = "androidx.core:core-ktx:1.3.0"
        const val androidX_test_core_ktx = "androidx.test:core-ktx:1.3.0"
        const val androidX_test_ext_junit = "androidx.test.ext:junit-ktx:1.1.2"
        const val androidX_test_espressoCore = "androidx.test.espresso:espresso-core:3.3.0"
        const val androidX_test_espressoContrib = "androidx.test.espresso:espresso-contrib:3.3.0"
        const val androidX_test_runner = "androidx.test:runner:1.3.0"
        const val androidX_test_uiautomator = "androidx.test.uiautomator:uiautomator:2.2.0"

        const val material = "com.google.android.material:material:1.3.0"

        const val junit = "junit:junit:4.13"
    }
}