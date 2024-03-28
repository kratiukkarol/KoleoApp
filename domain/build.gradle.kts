plugins {
    kotlin("jvm")
    kotlin("kapt")
}

dependencies {

    kapt(libs.hiltCompiler)
    implementation(libs.hiltCore)
    implementation(libs.bundles.coroutines)

    kaptTest(libs.hiltCompiler)
    testImplementation(libs.junit)
    testImplementation(libs.coroutinesTest)
    testImplementation(libs.bundles.test)
}