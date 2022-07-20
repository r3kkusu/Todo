package com.todo.app.di

import androidx.lifecycle.ViewModelProvider
import com.todo.app.helpers.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}