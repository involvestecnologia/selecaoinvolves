package com.involves.involvesteste.data.model

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Relation

class MovieAllGenres(@Embedded val movie: Movie) {
    @Relation(parentColumn = "id", entityColumn = "movieId", entity = Genre::class)
    lateinit var genres: List<Genre>
}