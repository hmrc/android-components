import uk.gov.hmrc.components.buildsrc.ComposeDependencies.Versions
import uk.gov.hmrc.components.buildsrc.ComposeDependencies.Libs

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs")
    id("kotlin-kapt")
}

android {
    namespace = "uk.gov.hmrc.sample_compose_components"
    compileSdk = Versions.compileSdk

    defaultConfig {
        applicationId = "uk.gov.hmrc.sample_compose_components"
        minSdk = Versions.minSdk
        targetSdk = Versions.targetSdk
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        viewBinding = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.kotlinCompilerExtensionVersion
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":components-compose"))

    implementation(platform(Libs.compose_bom))
    androidTestImplementation(platform(Libs.compose_bom))

    implementation(Libs.androidX_lifecycle_viewModelCompose)
    implementation(Libs.androidx_lifecycle_runtimeCompose)
    implementation(Libs.compose_androidX_runtimeLiveData)
    implementation(Libs.androidX_coreKtx)
    implementation(Libs.androidX_lifecycle_runtimeKtx)
    implementation(Libs.androidX_activity_activityCompose)
    implementation(Libs.compose_ui)
    implementation(Libs.compose_tooling_preview)
    implementation(Libs.androidX_appCompat)
    implementation(Libs.compose_material3)
    implementation(Libs.androidX_navigation_fragment)
    implementation (Libs.androidX_navigation_ui)
    implementation(Libs.hilt)
    kapt(Libs.hilt_compiler)
    testImplementation(Libs.junit)
    androidTestImplementation(Libs.androidX_test_ext_junit)
    androidTestImplementation(Libs.androidX_test_espressoCore)
    androidTestImplementation(Libs.compose_uiTest)
    debugImplementation(Libs.compose_tooling)
    debugImplementation(Libs.compose_uiTestManifest)
    constraints {
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.8.10") {
            because("kotlin-stdlib-jdk7 is now a part of kotlin-stdlib")
        }
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.8.10") {
            because("kotlin-stdlib-jdk8 is now a part of kotlin-stdlib")
        }
    }
}