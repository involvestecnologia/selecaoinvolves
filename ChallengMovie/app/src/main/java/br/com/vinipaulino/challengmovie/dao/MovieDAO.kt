package br.com.anestech.axcalc.database

import br.com.vinipaulino.challengmovie.model.Genres
import br.com.vinipaulino.challengmovie.model.Movies
import io.realm.Realm
import io.realm.RealmObject

class MovieDAO {
    companion object {
        fun save(obj:RealmObject){
            val realm = Realm.getDefaultInstance()
            realm.beginTransaction()
            realm.insertOrUpdate(obj)
            realm.commitTransaction()
            realm.close()
        }

        fun saveList(obj: List<Movies>){
            val realm = Realm.getDefaultInstance()
            realm.beginTransaction()
            realm.insertOrUpdate(obj)
            realm.commitTransaction()
            realm.close()
        }

        fun findMovie(movie_id: Long?): Movies? {
            val realm = Realm.getDefaultInstance()
            val movie = realm.copyFromRealm(realm.where(Movies::class.java).equalTo("id", movie_id).findFirst())
            realm.close()
            return movie
        }
   }
}