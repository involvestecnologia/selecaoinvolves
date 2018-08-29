package com.involves.involvesteste.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey

@Entity(tableName = "movie_genre_join",
        primaryKeys = [ "movieId", "genreId"],
        foreignKeys = [
            ForeignKey(entity = Movie::class, parentColumns = ["id"], childColumns = ["movieId"]),
            ForeignKey(entity = Genre::class, parentColumns = ["id"], childColumns = ["genreId"])
        ])
data class MovieGenreJoin(
    val movieId: Int,
    val genreId: Int
)