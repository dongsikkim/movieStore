package com.sundaydev.kakaoTest.network

const val URL_HOST = "https://api.themoviedb.org/3/"

class MovieClient : MovieClientBase() {

    init {
        updateEndPoint(URL_HOST)
    }

    override fun updateEndPoint(host: String) {
        movieApi = createClient(host).create(MovieService::class.java)
    }
}