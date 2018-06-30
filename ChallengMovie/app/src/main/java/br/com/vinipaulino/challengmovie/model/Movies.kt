package br.com.vinipaulino.challengmovie.model

import android.os.Parcel
import android.os.Parcelable
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.io.Serializable


open class Movies(
        @PrimaryKey
        var id: Int? = null,
        var poster_path: String? = null,
        var adult: Boolean? = false,
        var overview: String? = null,
        var release_date: String? = null,
        var genre_ids: RealmList<Int>? = null,

        var original_title: String? = null,
        var original_language: String? = null,
        var title: String? = null,
        var backdrop_path: String? = null,
        var popularity: Double? = null,
        var vote_count: Int? = null,
        var video: Boolean = false,
        var vote_average: Double? = null) : RealmObject(), Serializable {

}


