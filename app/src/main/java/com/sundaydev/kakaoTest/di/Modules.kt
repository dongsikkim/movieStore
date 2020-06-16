package com.sundaydev.kakaoTest.di

import android.net.Uri
import com.google.gson.GsonBuilder
import com.sundaydev.kakaoTest.network.MovieClient
import com.sundaydev.kakaoTest.repository.*
import com.sundaydev.kakaoTest.util.UriAdapter
import com.sundaydev.kakaoTest.viewmodel.*
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
    viewModel { MovieViewModel() }
    viewModel { MyInfoViewModel() }
    viewModel { PeopleDetailViewModel() }
    viewModel { PeopleViewModel() }
    viewModel { TvViewModel() }
    viewModel { (filterName: String) -> MovieContentsViewModel(filterName) }
    viewModel { (filterName: String) -> TvContentsViewModel(filterName) }
    viewModel { (movieId: Int) -> DetailViewModel(movieId) }
}

val applicationModules = listOf(
    utilModule,
    repositoryModule,
    viewModelModule
)