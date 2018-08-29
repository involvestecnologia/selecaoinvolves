package com.involves.involvesteste

import android.app.Application
import android.arch.persistence.room.Room
import com.involves.involvesteste.data.source.local.InvolvesDatabase


class InvolvesTestApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this

        getDatabase()
    }

    fun getDatabase(): InvolvesDatabase {
        return Room.databaseBuilder(applicationContext,
                InvolvesDatabase::class.java, "involvestest_db")
                .build()
    }

    companion object {
        lateinit var instance : InvolvesTestApplication
        private set
    }
}
