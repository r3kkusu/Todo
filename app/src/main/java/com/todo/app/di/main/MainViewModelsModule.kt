package com.todo.app.di.main

import androidx.lifecycle.ViewModel
import com.todo.app.di.ViewModelKey
import com.todo.app.ui.main.done.DoneViewModel
import com.todo.app.ui.main.edit.EditViewModel
import com.todo.app.ui.main.home.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
open abstract class MainViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(viewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DoneViewModel::class)
    abstract fun bindDoneViewModel(viewModel: DoneViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(EditViewModel::class)
    abstract fun bindEditViewModel(viewModel: EditViewModel): ViewModel
}