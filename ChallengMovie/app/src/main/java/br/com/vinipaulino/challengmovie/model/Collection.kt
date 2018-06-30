package br.com.vinipaulino.challengmovie.model

import io.realm.RealmObject
import java.io.Serializable

open class Collection (
        var id: Long? = 0,
        var name: String? = "",
        var poster_path: String? = "",
        var backdrop_path: String? = ""

): RealmObject(), Serializable