// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.4.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.0")
        classpath ("com.google.dagger:hilt-android-gradle-plugin:2.44")
        classpath ("de.mannodermaus.gradle.plugins:android-junit5:1.8.2.1")

    }
}

tasks {
    register("clean", Delete::class) {
        delete(rootProject.buildDir)
    }
}
