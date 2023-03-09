object Dependencies {

    object Plugins {
        const val application = "com.android.application"
        const val kotlinAndroid = "org.jetbrains.kotlin.android"
    }

    object Android {

        object Version {
            const val coreKtx = "1.7.0"
            const val appCompat = "1.4.1"
            const val activityCompose = "1.4.0"
            const val compose = "1.2.0"
            const val lifecycle = "2.5.0-rc01"
            const val lifecycleExtensions = "2.2.0"
        }

        const val coreKts = "androidx.core:core-ktx:${Version.coreKtx}"
        const val appCompat = "androidx.appcompat:appcompat:${Version.appCompat}"
        const val activityCompose = "androidx.activity:activity-compose:${Version.activityCompose}"

        // Compose
        const val composeUi = "androidx.compose.ui:ui:${Version.compose}"
        const val composeUiTooling = "androidx.compose.ui:ui-tooling:${Version.compose}"
        const val composeUiToolingPreview = "androidx.compose.ui:ui-tooling-preview:${Version.compose}"
        const val composeMaterial = "androidx.compose.material:material:${Version.compose}"
        const val composeMaterial3 = "androidx.compose.material3:material3:1.0.1"

        const val composeMaterialIconsExtended = "androidx.compose.material:material-icons-extended:${Version.compose}"

        // Lifecycle
        const val lifecycleViewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.lifecycle}"
        const val lifecycleRuntimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${Version.lifecycle}"
        const val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:${Version.lifecycleExtensions}"

        // Navigation
        const val navigationCompose = "androidx.navigation:navigation-compose:${Version.lifecycle}"
        const val lifecycleViewModelCompose = "androidx.lifecycle:lifecycle-viewmodel-compose:${Version.lifecycle}"
    }

    object ThirdParty {

        object Version {
            const val androidMaterial = "1.6.0"
            const val coilCompose = "1.3.2"
            const val coroutines = "1.6.4"
            const val retrofit = "2.9.0"
            const val dagger = "2.44"
            const val moshi = "1.14.0"
            const val glide = "1.0.0-alpha.2"
        }

        // Coroutines
        const val kotlinxCoroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.coroutines}"
        const val kotlinxCoroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.coroutines}"

        // Retrofit
        const val retrofit = "com.squareup.retrofit2:retrofit:${Version.retrofit}"
        const val retrofitConverterMoshi = "com.squareup.retrofit2:converter-moshi:${Version.retrofit}"

        // Moshi
        const val moshi = "com.squareup.moshi:moshi-kotlin:${Version.moshi}"
        const val moshiCodegen = "com.squareup.moshi:moshi-kotlin-codegen:${Version.moshi}"

        const val androidMaterial = "com.google.android.material:material:${Version.androidMaterial}"

        // dagger
        const val daggerAndroid = "com.google.dagger:hilt-android:${Version.dagger}"
        const val daggerCompilerAndroid = "com.google.dagger:hilt-android-compiler:${Version.dagger}"
        const val daggerNavigationCompose = "androidx.hilt:hilt-navigation-compose:1.0.0"

        const val glideCompose = "com.github.bumptech.glide:compose:${Version.glide}"
    }

    object Test {

        object Version {
            const val junit = "5.9.2"
            const val roboeletric = "4.6.1"
            const val mockk = "1.13.2"
            const val okHttp3MockWebServer = "4.9.2"
        }

        const val junit_api = "org.junit.jupiter:junit-jupiter-api:${Version.junit}"
        const val junit_engine = "org.junit.jupiter:junit-jupiter-engine:${Version.junit}"
        const val roboeletric = "org.robolectric:robolectric:${Version.roboeletric}"
        const val mockk = "io.mockk:mockk:${Version.mockk}"
        const val kotlinxCoroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${ThirdParty.Version.coroutines}"
        const val okHttp3MockWebServer = "com.squareup.okhttp3:mockwebserver:${Version.okHttp3MockWebServer}"
    }

    object AndroidTest {

        object Version {
            const val junit = "1.1.3"
            const val espressoCore = "3.4.0"
        }

        const val junit = "androidx.test.ext:junit:${Version.junit}"
        const val espressoCore = "androidx.test.espresso:espresso-core:${Version.espressoCore}"
        const val composeJunit = "androidx.compose.ui:ui-test-junit4:${Android.Version.compose}"
        const val composeUiTooling = "androidx.compose.ui:ui-tooling:${Android.Version.compose}"
    }
}

object AppConfig {
    const val compileSdk = 33
    const val minSdk = 26
    const val targetSdk = 33
    const val applicationId = "com.example.albumlistcreator"
    const val versionCode = 1
    const val versionName = "1.0.0"
    const val androidTestInstrumentation = "androidx.test.runner.AndroidJUnitRunner"

    val javaJvmTarget = JavaVersion.VERSION_11.toString()
    val javaCompatibility = JavaVersion.VERSION_11
}

plugins {
    id("com.android.application")
    kotlin("kapt")
    id ("dagger.hilt.android.plugin")
    kotlin("android")
    id("de.mannodermaus.android-junit5")
}
android {
    compileSdk = AppConfig.compileSdk
    namespace = "com.example.albumlistcreator"

    defaultConfig {
        applicationId = AppConfig.applicationId
        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.targetSdk
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName

        testInstrumentationRunner = AppConfig.androidTestInstrumentation
        vectorDrawables {
            useSupportLibrary = true
        }

        buildConfigField("String", "API_BASE_ENDPOINT", "\"https://itunes.apple.com\"")
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
        sourceCompatibility = AppConfig.javaCompatibility
        targetCompatibility = AppConfig.javaCompatibility
    }

    kotlinOptions {
        jvmTarget = AppConfig.javaJvmTarget
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Dependencies.Android.Version.compose
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    testOptions {
        unitTests.apply {
            isReturnDefaultValues = true
        }
    }

    sourceSets {
        getByName("main").java.srcDirs("src/main/resources", "src/main/test/resources")
        getByName("test").java.srcDirs("src/test/resources")
        getByName("androidTest").java.srcDirs("src/androidTest/resources")
    }
}

dependencies {
    // Android
    implementation(Dependencies.Android.coreKts)
    implementation(Dependencies.Android.appCompat)
    implementation(Dependencies.Android.activityCompose)
    implementation(Dependencies.Android.navigationCompose)

    implementation(Dependencies.Android.composeUi)
    implementation(Dependencies.Android.composeMaterial)
    implementation(Dependencies.Android.composeMaterialIconsExtended)
    implementation(Dependencies.Android.composeUiTooling)
    implementation(Dependencies.Android.composeUiToolingPreview)
    implementation(Dependencies.Android.composeMaterial3)

    implementation(Dependencies.Android.lifecycleRuntimeKtx)
    implementation(Dependencies.Android.lifecycleViewModelKtx)
    implementation(Dependencies.Android.lifecycleExtensions)
    implementation(Dependencies.Android.lifecycleViewModelCompose)

    // Third Party
    implementation(Dependencies.ThirdParty.androidMaterial)

    implementation(Dependencies.ThirdParty.kotlinxCoroutinesCore)
    implementation(Dependencies.ThirdParty.kotlinxCoroutinesAndroid)

    implementation(Dependencies.ThirdParty.retrofit)
    implementation(Dependencies.ThirdParty.retrofitConverterMoshi)

    implementation(Dependencies.ThirdParty.moshi)
    kapt(Dependencies.ThirdParty.moshiCodegen)

    implementation(Dependencies.ThirdParty.glideCompose)

    // Dagger - Hilt
    implementation (Dependencies.ThirdParty.daggerAndroid)
    kapt (Dependencies.ThirdParty.daggerCompilerAndroid)

    //hiltViewModel
    implementation (Dependencies.ThirdParty.daggerNavigationCompose)

    // Test
    testImplementation(Dependencies.Test.junit_api)
    testRuntimeOnly(Dependencies.Test.junit_engine)
    debugImplementation("com.google.truth:truth:1.1.3")

    testImplementation(Dependencies.Test.roboeletric)
    testImplementation(Dependencies.Test.mockk)
    testImplementation(Dependencies.Test.kotlinxCoroutinesTest)
    testImplementation(Dependencies.Test.okHttp3MockWebServer)

    testImplementation ("com.google.dagger:hilt-android-testing:2.44")
    kaptTest ("com.google.dagger:hilt-android-compiler:2.44")

    // Android Test
    androidTestImplementation(Dependencies.AndroidTest.junit)
    androidTestImplementation(Dependencies.AndroidTest.espressoCore)
    androidTestImplementation(Dependencies.AndroidTest.composeJunit)
    debugImplementation(Dependencies.AndroidTest.composeUiTooling)


}