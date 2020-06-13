package com.sundaydev.kakaoTest.di

import android.net.Uri
import com.google.gson.GsonBuilder
import corp.ncsoft.ncapps.nu11.util.UriAdapter
import org.koin.dsl.module

val utilModule = module {
    single { GsonBuilder().registerTypeAdapter(Uri::class.java, UriAdapter()).setPrettyPrinting().create() }
}

val repositoryModule = module {

}

val viewModelModule = module {

}

val applicationModules = listOf(
    utilModule,
    repositoryModule,
    viewModelModule
)