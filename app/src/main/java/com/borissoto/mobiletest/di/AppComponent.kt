package com.borissoto.mobiletest.di

import android.app.Application
import com.borissoto.mobiletest.ui.detail.DetailFragmentComponent
import com.borissoto.mobiletest.ui.detail.DetailFragmentModule
import com.borissoto.mobiletest.ui.main.MainFragmentComponent
import com.borissoto.mobiletest.ui.main.MainFragmentModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [ AppModule::class, AppDataModule::class ]
)
interface AppComponent {

    fun plus(mainFragmentModule: MainFragmentModule): MainFragmentComponent
    fun plus(detailFragmentModule: DetailFragmentModule): DetailFragmentComponent

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: Application): AppComponent
    }
}