package com.sundaydev.kakaoTest.di

import android.net.Uri
import com.google.gson.GsonBuilder
import com.sundaydev.kakaoTest.network.MovieClient
import com.sundaydev.kakaoTest.repository.*
import com.sundaydev.kakaoTest.viewmodel.*
import corp.ncsoft.ncapps.nu11.util.UriAdapter
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val utilModule = module {
    single { GsonBuilder().registerTypeAdapter(Uri::class.java, UriAdapter()).setPrettyPrinting().create() }
}

val repositoryModule = module {
    single<ContentsRepository> { ContentsRepositoryImpl() }
    single<PeopleRepository> { PeopleRepositoryImpl() }
    single<MyInfoRepository> { MyInfoRepositoryImpl() }
    single { MovieClient() }
}

val viewModelModule = module {
    viewModel { SplashViewModel() }
    viewModel { DetailViewModel() }
    viewModel { MovieViewModel() }
    viewModel { MyInfoViewModel() }
    viewModel { PeopleDetailViewModel() }
    viewModel { PeopleViewModel() }
    viewModel { TvViewModel() }
    viewModel { (filterName: String) -> ContentsViewModel(filterName) }
}

val applicationModules = listOf(
    utilModule,
    repositoryModule,
    viewModelModule
)