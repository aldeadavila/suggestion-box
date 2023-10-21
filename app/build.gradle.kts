plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("kotlin-parcelize")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.aldeadavila.suggestionbox"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.aldeadavila.suggestionbox"
        minSdk = 22
        targetSdk = 33
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}


dependencies {
    val compose_version = "1.1.1"
    val lifecycle_version = "2.4.1"
    val hilt_version = "2.40.1"
    val navigation_version = "2.4.0-alpha07"
    val accompanist_version = "0.24.3-alpha"
    val coil_version = "2.2.1"
    val paging_version = "1.0.0-alpha14"
    val lottieVersion = "5.2.0"
    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.appcompat:appcompat:1.4.0")
    implementation("com.google.android.material:material:1.6.1")
    implementation("androidx.appcompat:appcompat:1.4.1")
    implementation("androidx.compose.ui:ui:$compose_version")
    implementation("androidx.compose.material:material:$compose_version")
    implementation("androidx.compose.ui:ui-tooling-preview:$compose_version")
    implementation("androidx.compose.foundation:foundation:$compose_version")
    implementation("androidx.compose.foundation:foundation-layout:$compose_version")
    implementation("androidx.compose.material:material-icons-core:$compose_version")
    implementation("androidx.compose.material:material-icons-extended:$compose_version")
    implementation("androidx.constraintlayout:constraintlayout-compose:1.0.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.4.1")
    implementation("androidx.activity:activity-compose:1.4.0")
    //ViewModels
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycle_version")
    implementation("androidx.lifecycle:lifecycle-viewmodel-savedstate:2.4.1")
    implementation("androidx.navigation:navigation-compose:$navigation_version")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycle_version")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")
    //Hilt
    implementation("com.google.dagger:hilt-android:$hilt_version")
    kapt("com.google.dagger:hilt-compiler:$hilt_version")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
    //Accompanist
    implementation("com.google.accompanist:accompanist-insets:$accompanist_version")
    implementation("com.google.accompanist:accompanist-pager:$accompanist_version")
    implementation("com.google.accompanist:accompanist-pager-indicators:$accompanist_version")
    implementation("com.google.accompanist:accompanist-placeholder-material:$accompanist_version")
    implementation("com.google.accompanist:accompanist-swiperefresh:$accompanist_version")
    implementation("com.google.accompanist:accompanist-navigation-animation:$accompanist_version")

    //Coil
    implementation("io.coil-kt:coil-compose:$coil_version")
    //Paging 3.0
    implementation("androidx.paging:paging-compose:$paging_version")
    //SwipeLayout
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")
    //Android Util
    implementation("com.blankj:utilcodex:1.31.0")
    //Time Ago
    implementation("com.github.marlonlom:timeago:4.0.3")
    //Lottie
    implementation("com.airbnb.android:lottie-compose:$lottieVersion")
    // Import the Firebase BoM
    implementation(platform("com.google.firebase:firebase-bom:31.0.0"))
    implementation("com.google.firebase:firebase-analytics-ktx")
    implementation("com.google.firebase:firebase-auth-ktx")
    implementation("com.google.android.gms:play-services-auth:20.0.0")
    implementation("com.google.firebase:firebase-firestore-ktx")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:$compose_version")
    debugImplementation("androidx.compose.ui:ui-tooling:$compose_version")
    debugImplementation("androidx.compose.ui:ui-test-manifest:$compose_version")
}
kapt {
    correctErrorTypes = true
}
