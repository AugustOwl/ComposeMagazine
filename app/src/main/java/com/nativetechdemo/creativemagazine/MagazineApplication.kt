package com.nativetechdemo.creativemagazine

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MagazineApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "MagazineApplication:Oncreate: Executing the Application level")
    }

    companion object {
        const val TAG = "MagazineApplication"
    }
}