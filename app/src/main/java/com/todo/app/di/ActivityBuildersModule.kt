package com.todo.app.di

import com.todo.app.di.main.MainFragmentBuildersModule
import com.todo.app.di.main.MainModule
import com.todo.app.di.main.MainScope
import com.todo.app.di.main.MainViewModelsModule
import com.todo.app.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {
    @MainScope
    @ContributesAndroidInjector(modules = [MainFragmentBuildersModule::class, MainViewModelsModule::class, MainModule::class])
    abstract fun contributeMainActivity(): MainActivity
}