package com.sundaydev.kakaoTest.network

const val URL_HOST = "https://api.themoviedb.org/"

class MovieClient : MovieClientBase() {

    init {
        updateEndPoint(URL_HOST)
    }

    override fun updateEndPoint(host: String) {
        movieApi = createClient(host).create(NullServices::class.java)
    }
}