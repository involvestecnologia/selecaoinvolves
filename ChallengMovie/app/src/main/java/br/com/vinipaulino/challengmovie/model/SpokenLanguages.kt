package br.com.vinipaulino.challengmovie.model

import io.realm.RealmObject
import java.io.Serializable

open class SpokenLanguages (
        var iso_639_1: String? = "",
        var name: String? = ""
) : RealmObject(), Serializable