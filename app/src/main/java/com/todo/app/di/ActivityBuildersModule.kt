package com.todo.app.di

import com.todo.app.di.main.MainFragmentBuildersModule
import com.todo.app.di.main.MainScope
import com.todo.app.di.main.MainViewModelsModule
import com.todo.app.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {
    @MainScope
    @ContributesAndroidInjector(modules = [MainFragmentBuildersModule::class, MainViewModelsModule::class])
    abstract fun contributeMainActivity(): MainActivity
}