package com.pliniodev.gametest.di

import com.pliniodev.gametest.data.local.provideDatabase
import com.pliniodev.gametest.data.local.provideStepDAO
import com.pliniodev.gametest.data.local.repository.StepRepository
import com.pliniodev.gametest.data.local.repository.StepRepositoryImpl
import com.pliniodev.gametest.data.remote.api.ApiService
import com.pliniodev.gametest.data.remote.remotedatasource.RemoteDataSource
import com.pliniodev.gametest.data.remote.remotedatasource.RemoteDataSourceImpl
import com.pliniodev.gametest.data.remote.repository.ApiRepositoryImpl
import com.pliniodev.gametest.data.remote.retrofit.createApi
import com.pliniodev.gametest.data.remote.retrofit.provideOkHttpClient
import com.pliniodev.gametest.data.remote.retrofit.provideRetrofit
import com.pliniodev.gametest.domain.repository.ApiRepository
import com.pliniodev.gametest.domain.usecase.GetPhraseUseCase
import com.pliniodev.gametest.presentation.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModules = module {
    viewModel{ MainViewModel(useCase = get(), database = get()) }
}

val networkModules = module {
    single { provideOkHttpClient() }
    single { provideRetrofit(get()) }
    single { createApi<ApiService>(get()) }
}

val databaseModule = module {
    single { provideDatabase(application = get()) }
    single { provideStepDAO(database = get()) }
}

val repositoryModule = module {
    single<StepRepository> { StepRepositoryImpl(get()) }
}

val dataModules = module {
    factory<RemoteDataSource> { RemoteDataSourceImpl(api = get()) }
    factory<ApiRepository> { ApiRepositoryImpl(remoteDataSource = get()) }
}

val domainModules = module {
    factory { GetPhraseUseCase(repository = get()) }
}

