package br.com.vinipaulino.challengmovie.model

import io.realm.RealmObject
import java.io.Serializable

open class Genres (
        var id: Int? = null,
        var name : String? = ""
) : RealmObject(), Serializable