package com.todo.app.di.main

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
open abstract class MainViewModelsModule {
//    @Binds
//    @IntoMap
//    @ViewModelKey(ProfileViewModel::class)
//    abstract fun bindProfileViewModel(viewModel: ProfileViewModel?): ViewModel?
//
//    @Binds
//    @IntoMap
//    @ViewModelKey(PostsViewModel::class)
//    abstract fun bindPostsViewModel(viewModel: PostsViewModel?): ViewModel?
}