package com.example.topfilms.di

import androidx.lifecycle.ViewModelProvider
import org.kodein.di.Kodein
import org.kodein.di.direct
import org.kodein.di.erased.*
import com.example.topfilms.api.PopularFilms
import com.example.topfilms.data.DataManager
import com.example.topfilms.data.NetworkManager
import com.example.topfilms.ui.ViewModelFactory
import com.example.topfilms.ui.activities.MainViewModel
import com.example.topfilms.util.bindViewModel

val networkModule = Kodein.Module("network") {
    constant("serverURL") with "https://api.themoviedb.org"

    bind<PopularFilms>() with singleton { PopularFilms.create(instance("serverURL")) }
    bind<DataManager>() with singleton { DataManager(instance()) }
    bind<NetworkManager>() with singleton { NetworkManager(instance()) }

    //bind<AuthApi>() with singleton { createWebService<AuthApi>(instance(), instance("serverURL")) }

}

val viewModelModule = Kodein.Module("viewModelModule") {
    bind<ViewModelProvider.Factory>() with singleton { ViewModelFactory(kodein.direct) }
    bindViewModel<MainViewModel>() with provider { MainViewModel(instance()) }
}