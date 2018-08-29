package com.involves.involvesteste.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "genres")
data class Genre(
        @PrimaryKey
        val id : Int,
        val name : String?,
        val movieId : Int
)

