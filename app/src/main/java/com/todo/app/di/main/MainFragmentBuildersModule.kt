package com.todo.app.di.main

import com.todo.app.ui.main.done.DoneFragment
import com.todo.app.ui.main.home.HomeFragment
import com.todo.app.ui.main.edit.EditFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun contributeDoneFragment(): DoneFragment

    @ContributesAndroidInjector
    abstract fun contributeEditFragment(): EditFragment
}