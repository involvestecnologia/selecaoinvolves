package br.com.vinipaulino.challengmovie.model

import io.realm.RealmList
import io.realm.RealmObject

open class MovieDetails(
        var adult: Boolean = false,
        var backdrop_path: String? = null,
        var belongs_collection: Collection? = null,
        var budget: Int? = null,
        var genres: RealmList<Genres>? = null,
        var homepage: String? = null,
        var id: Int? = null,
        var imdb_id: String? = "",
        var original_language: String? = null,
        var original_title: String? = null,
        var overview: String? = null,
        var popularity: Double? = null,
        var poster_path: String? = null,
        var production_companies: RealmList<ProductionCountries>? = null,
        var release_date: String? = null,
        var revenue: Int? = null,
        var runtime: Int? = null,
        var spokenLanguages: RealmList<SpokenLanguages>? = null,
        var status: String? = "",
        var tagline: String? = "",
        var title: String? = null,
        var video: Boolean? = false,
        var vote_average: Double? = null,
        var vote_count: Int? = null
): RealmObject()
