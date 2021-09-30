package com.alesno.mytrainings

import android.app.Application
import android.content.Context
import com.alesno.mytrainings.di.AppComponent
import com.alesno.mytrainings.di.DaggerAppComponent

class MyTrainingsApplication : Application() {

    val appComponent by lazy { DaggerAppComponent.builder().context(this).build() }

}

val Context.appComponent: AppComponent
    get() = when (this) {
        is MyTrainingsApplication -> appComponent
        else -> this.applicationContext.appComponent
    }