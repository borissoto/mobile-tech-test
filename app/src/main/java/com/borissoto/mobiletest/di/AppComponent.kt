package com.borissoto.mobiletest.di

import android.app.Application
import com.borissoto.mobiletest.ui.detail.DetailFragment
import com.borissoto.mobiletest.ui.main.MainFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [ AppModule::class, AppDataModule::class ]
)
interface AppComponent {

    fun inject(fragment: MainFragment)
    fun inject(fragment: DetailFragment)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: Application): AppComponent
    }
}