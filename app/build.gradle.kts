import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    id("com.google.devtools.ksp")
    id("com.google.gms.google-services")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
}

android {
    namespace = "com.aldeadavila.suggestionbox"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.aldeadavila.suggestionbox"
        minSdk = 26
        targetSdk = 35
        versionCode = 31
        versionName = "1.31"
  
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        // Read Maps API key from local.properties
        val properties = project.properties
        manifestPlaceholders += mapOf(
            "MAPS_API_KEY" to (properties["MAPS_API_KEY"] as String? ?: "")
        )
    }

    buildTypes {
        release {
            // Enables code shrinking, obfuscation, and optimization for only
            // your project's release build type. Make sure to use a build
            // variant with `isDebuggable=false`.
            isMinifyEnabled = true

            // Enables resource shrinking, which is performed by the
            // Android Gradle plugin.
            isMinifyEnabled = false
            proguardFiles(
                // Includes the default ProGuard rules files that are packaged with
                // the Android Gradle plugin. To learn more, go to the section about
                // R8 configuration files.
                getDefaultProguardFile("proguard-android-optimize.txt"),
                // Includes a local, custom Proguard rules file
                "proguard-rules.pro"

            )
            ndk {
                debugSymbolLevel = "SYMBOL_TABLE"
            }
        }
        debug {
            isDebuggable = true
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
        buildConfig = true

    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.8"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    val navVersion = "2.8.4"
    val roomVersion = "2.6.1"
    val hiltVersion = rootProject.extra["hilt_version"] as String
    val composeVersion = rootProject.extra["compose_version"] as String

    // Force Credentials & Google ID to versions built with Kotlin 1.9 (1.4.0 does not exist; 1.6.x uses Kotlin 2.1)
    configurations.all {
        resolutionStrategy {
            force("androidx.credentials:credentials:1.3.0")
            force("androidx.credentials:credentials-play-services-auth:1.3.0")
            force("com.google.android.libraries.identity.googleid:googleid:1.1.1")
        }
    }

    // Import the Firebase BoM
    implementation(platform("com.google.firebase:firebase-bom:33.7.0"))

    implementation("com.google.firebase:firebase-auth-ktx")
    implementation("com.google.firebase:firebase-firestore-ktx")
    implementation("com.google.firebase:firebase-storage")
    implementation("com.google.firebase:firebase-database-ktx")
    implementation("com.google.firebase:firebase-messaging-ktx")

    // Credentials & Google ID (1.3.0 is latest stable compatible with Kotlin 1.9)
    implementation("androidx.credentials:credentials:1.3.0")
    implementation("androidx.credentials:credentials-play-services-auth:1.3.0")
    implementation("com.google.android.libraries.identity.googleid:googleid:1.1.1")

    // Maps SDK for Android
    implementation("com.google.android.gms:play-services-location:21.3.0")
    implementation("com.google.android.gms:play-services-maps:19.0.0")
    implementation("com.google.maps.android:maps-compose:4.4.1")
    implementation("com.google.maps.android:maps-compose-utils:4.4.1")
    implementation("com.google.maps.android:maps-compose-widgets:4.4.1")

    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.7")
    implementation("androidx.activity:activity-compose:1.9.3")
    implementation(platform("androidx.compose:compose-bom:2024.08.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.ui:ui:$composeVersion")
    implementation("androidx.compose.material:material-icons-extended:$composeVersion")
    implementation("androidx.compose.material:material:$composeVersion")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3")

    // Pager (replaces deprecated Accompanist)
    implementation("androidx.compose.foundation:foundation")

    // Glide
    implementation("com.github.bumptech.glide:compose:1.0.0-beta01")

    // FILES
    implementation("commons-io:commons-io:2.17.0")

    // GSON
    implementation("com.google.code.gson:gson:2.11.0")

    // MOSHI
    implementation("com.squareup.moshi:moshi-kotlin:1.15.1")

    // RETROFIT
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")

    // Navigation
    implementation("androidx.navigation:navigation-compose:$navVersion")

    // Coil
    implementation("io.coil-kt:coil-compose:2.7.0")

    // DataStore
    implementation("androidx.datastore:datastore-preferences:1.1.1")

    //daguer hilt
    implementation("com.google.dagger:hilt-android:$hiltVersion")
    ksp("com.google.dagger:hilt-compiler:$hiltVersion")
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")

    // Room
    implementation("androidx.room:room-runtime:$roomVersion")
    ksp("androidx.room:room-compiler:$roomVersion")
    implementation("androidx.room:room-ktx:$roomVersion")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2024.08.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}

secrets {
    // Optionally specify a different file name containing your secrets.
    // The plugin defaults to "local.properties"
    propertiesFileName = "secrets.properties"

    // A properties file containing default secret values. This file can be
    // checked in version control.
    defaultPropertiesFileName = "local.defaults.properties"

    // Configure which keys should be ignored by the plugin by providing regular expressions.
    // "sdk.dir" is ignored by default.
    ignoreList.add("keyToIgnore") // Ignore the key "keyToIgnore"
    ignoreList.add("sdk.*")       // Ignore all keys matching the regexp "sdk.*"
}
