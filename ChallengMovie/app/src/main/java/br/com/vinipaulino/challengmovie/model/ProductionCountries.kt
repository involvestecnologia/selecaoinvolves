package br.com.vinipaulino.challengmovie.model

import io.realm.RealmObject
import java.io.Serializable

open class ProductionCountries (
        var iso_3166_1: String? = "",
        var name: String? = ""
) : RealmObject(), Serializable