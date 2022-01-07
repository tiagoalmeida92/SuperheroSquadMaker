package com.tiago.superherosquadmaker

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SquadApplication: Application() {

    override fun onCreate() {
        super.onCreate()
    }

}