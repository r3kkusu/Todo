package com.todo.app

import android.app.Application
import com.todo.app.di.AppComponent
import com.todo.app.di.DaggerAppComponent

class BaseApplication : Application() {

    val appComponent: AppComponent by lazy {
        initializeComponent()
    }

    private fun initializeComponent(): AppComponent {
        return DaggerAppComponent.factory().create(applicationContext)
    }
}
