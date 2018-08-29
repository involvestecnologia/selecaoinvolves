package com.involves.involvesteste.data.model

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class Converters {

    @TypeConverter
    fun stringToJson(value : String) : List<Movie> {
        val listType = object : TypeToken<ArrayList<Movie>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun jsonToArray(list : List<Movie>) : String {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun stringToGenres(value : String) : List<Genre> {
        val listType = object : TypeToken<ArrayList<Genre>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun genresToString(list : List<Genre>) : String {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun stringToInt(value : String) : List<Int> {
        val listType = object : TypeToken<ArrayList<Int>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun intToString(list : List<Int>) : String {
        return Gson().toJson(list)
    }
}