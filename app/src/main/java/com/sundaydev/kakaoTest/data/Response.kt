package com.sundaydev.kakaoTest.data

import java.util.*

data class Tvs(val page: Int, val total_results: Int, val total_pages: Int, val results: List<Tv>)
data class Movies(val page: Int, val total_results: Int, val total_pages: Int, val results: List<Movie>)
data class Peoples(val page: Int, val total_results: Int, val total_pages: String, val results: List<Any>)

open class MediaItem(
    val poster_path: String? = null,
    val popularity: Long = 0,
    val original_language: String = "",
    var id: Int = -1,
    val media_type: String = "",
    val backdrop_path: String? = null,
    val vote_average: Long = 0L,
    val overview: String = "",
    val genre_ids: List<Int> = mutableListOf(),
    val vote_count: Int = 0
)

data class Tv(
    val first_air_date: String, val origin_country: List<String>,
    val name: String, val original_name: String
) : MediaItem()

data class Movie(
    val adult: Boolean = false, val release_date: String = "",
    val original_title: String = "", val title: String = "",
    val video: Boolean = false
) : MediaItem()

data class People<T>(
    val profile_path: String, val adult: Boolean, val id: Int,
    val known_for: T, val name: String, val popularity: Long
)

data class PeopleCredits(
    val cast: List<PeopleCast>, val crew: List<PeopleCrew>, val id: Int
)

data class PeopleCast(
    val id: Int, val original_language: String, val episode_count: Int,
    val overview: String, val origin_country: String, val original_name: String,
    val genre_ids: List<Int>, val name: String, val media_type: String,
    val poster_path: String?, val first_air_date: String, val vote_average: Int?,
    val vote_count: Int, val character: String, val backdrop_path: String?, val popularity: Long,
    val credit_id: String, val original_title: String, val video: Boolean,
    val release_date: Date, val title: String, val adult: Boolean
)

data class PeopleCrew(
    val id: Int,
    val department: String,
    val original_language: String,
    val episode_count: Int,
    val job: String,
    val overview: String,
    val origin_country: List<String>,
    val original_name: String,
    val vote_count: Int,
    val name: String,
    val media_type: String,
    val popularity: Long,
    val credit_id: String,
    val backdrop_path: String?,
    val first_air_date: String, val vote_average: Long, val genre_ids: List<Int>,
    val poster_path: String?, val original_title: String, val video: Boolean, val title: String,
    val adult: Boolean, val release_date: Date
)

data class MovieDetail(
    val adult: Boolean, val backdrop_path: String?,
    val budget: Int, val genres: List<Genres>,
    val homepage: String?, val id: Int, val imdb_id: String?,
    val original_language: String, val original_title: String,
    val overview: String?, val popularity: Int,
    val poster_path: String?, val production_companies: List<Company>,
    val production_countries: List<Country>, val release_date: Date,
    val revenue: Int, val runtime: Int?, val spoken_languages: List<SpokenLanguage>,
    val status: String, val tagline: String?, val title: String, val video: Boolean,
    val vote_average: Long, val vote_count: Int
)

data class PeopleDetail(
    val birthday: String, val known_for_department: String,
    val deathday: String, val id: Int, val name: String, val also_known_as: List<String>,
    val gender: Int, val biography: String, val popularity: Long, val place_of_birth: String?,
    val profile_path: String?, val adult: Boolean, val imdb_id: String,
    val homepage: String?
)

data class Credit(val id: Int, val cast: List<Cast>, val crew: List<Crew>)

data class Cast(
    val cast_id: Int, val character: String, val credit_id: String,
    val gender: Int, val id: Int, val name: String, val order: Int,
    val profile_path: String?
)

data class Crew(
    val credit_id: String, val department: String,
    val gender: Int?, val id: Int, val job: String, val name: String,
    val profile_path: String?
)

data class Genres(val id: Int, val name: String)
data class Company(val name: String, val id: Int, val logo_path: String?, val origin_country: String)
data class Country(val iso_3166_1: String, val name: String)
data class SpokenLanguage(val iso_639_1: String, val name: String)