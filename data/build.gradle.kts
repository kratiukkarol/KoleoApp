plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    kotlin("kapt")
    alias(libs.plugins.hilt)
    alias(libs.plugins.serialization)
}

apply(plugin = "realm-android")

android {
    namespace = "pl.kkapps.railways.data"
    compileSdk = 34

    defaultConfig {
        minSdk = 28

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    implementation(project(":domain"))

    kapt(libs.hiltCompiler)
    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.hiltAndroid)
    implementation(libs.bundles.coroutines)
    implementation(libs.retrofit)
    implementation(libs.converterGson)
    implementation(libs.jsonSerialization)
    implementation(libs.serializationConverter)
    implementation(libs.bundles.okhttp)

    kaptTest(libs.hiltCompiler)
    testImplementation(libs.junit)
    testImplementation(libs.coroutinesTest)
    testImplementation(libs.hiltAndroidTesting)

    kaptAndroidTest(libs.hiltAndroidCompiler)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.hiltAndroidTesting)
}

kapt {
    correctErrorTypes = true
}