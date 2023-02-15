package com.borissoto.mobiletest.di

import android.app.Application
import com.borissoto.mobiletest.ui.detail.DetailFragmentComponent
import com.borissoto.mobiletest.ui.detail.DetailFragmentModule
import com.borissoto.mobiletest.ui.main.MainViewModelFactory
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [UseCaseModule::class, DataModule::class, AppModule::class,
        ViewModelsModule::class]
)
interface AppComponent {

    val mainViewModelFactory: MainViewModelFactory
    fun plus(detailFragmentModule: DetailFragmentModule): DetailFragmentComponent

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: Application): AppComponent
    }
}