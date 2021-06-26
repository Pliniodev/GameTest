package com.pliniodev.gametest.di

import android.content.SharedPreferences
import com.pliniodev.gametest.data.local.localdatasource.LocalDataSource
import com.pliniodev.gametest.data.local.localdatasource.LocalDataSourceImpl
import com.pliniodev.gametest.data.local.provideDB
import com.pliniodev.gametest.data.local.provideStepDAO
import com.pliniodev.gametest.data.local.repository.StepRepositoryImpl
import com.pliniodev.gametest.data.local.sharedpreferences.getSharedPrefs
import com.pliniodev.gametest.data.remote.api.ApiService
import com.pliniodev.gametest.data.remote.remotedatasource.RemoteDataSource
import com.pliniodev.gametest.data.remote.remotedatasource.RemoteDataSourceImpl
import com.pliniodev.gametest.data.remote.repository.ApiRepositoryImpl
import com.pliniodev.gametest.data.remote.retrofit.createApi
import com.pliniodev.gametest.data.remote.retrofit.provideOkHttpClient
import com.pliniodev.gametest.data.remote.retrofit.provideRetrofit
import com.pliniodev.gametest.domain.repository.ApiRepository
import com.pliniodev.gametest.domain.repository.StepRepository
import com.pliniodev.gametest.domain.usecase.ApiUseCase
import com.pliniodev.gametest.domain.usecase.DBUseCase
import com.pliniodev.gametest.domain.usecase.UpdateDBUseCase
import com.pliniodev.gametest.presentation.MainViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModules = module {
    viewModel {
        MainViewModel(
            apiUseCase = get(),
            dbPhraseUseCase = get(),
            updateDBUseCase = get(),
            shared = get(),
            sharedEditor = get()
        )
    }
}

val sharedPrefModule = module {
    single { getSharedPrefs(androidApplication()) }
    single<SharedPreferences.Editor> { getSharedPrefs(androidApplication()).edit() }
}

val networkModules = module {
    single { provideOkHttpClient() }
    single { provideRetrofit(get()) }
    single { createApi<ApiService>(get()) }
}

val databaseModule = module {
    single { provideDB(application = get()) }
    single { provideStepDAO(database = get()) }
}

val dataModules = module {
    factory<LocalDataSource> { LocalDataSourceImpl(db = get()) }
    factory<RemoteDataSource> { RemoteDataSourceImpl(api = get()) }
    factory<ApiRepository> { ApiRepositoryImpl(remoteDataSource = get()) }
    single<StepRepository> { StepRepositoryImpl(get()) }
}

val domainModules = module {
    factory { ApiUseCase(repository = get()) }
    factory { DBUseCase(repository = get()) }
    factory { UpdateDBUseCase(repository = get()) }
}

