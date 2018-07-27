package com.involves.involvesteste.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movies")
data class Movie(
        @PrimaryKey
        val id : Int,
        @SerializedName("poster_path") val posterPath : String?,
        @SerializedName("overview") val overview : String?,
        @SerializedName("release_date") val releaseDate : String?,
        @SerializedName("original_title") val originalTitle : String?,
        @SerializedName("title") val title : String?,
        @SerializedName("backdrop_path") val backdropPath : String?,
//        @SerializedName("genre_ids") val genreIds: List<Int>?,
        val tagline : String?

)