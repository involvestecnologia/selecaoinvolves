package br.com.anestech.axcalc

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

/**
 * Created by Viny Paulino on 24/05/18.
 */

class AppCache : Application() {

    override fun onCreate() {
        super.onCreate()
        configAndInitRealm()
        appInstance = this
    }

    /**
     * Responsible for initialize and configure Realm database with initial data
     */
    private fun configAndInitRealm() {
        Realm.init(this)

        val config = RealmConfiguration.Builder()
                .name("challeng_moview.realm")
                .deleteRealmIfMigrationNeeded()
                .build()

        Realm.setDefaultConfiguration(config)
    }

    companion object {
        private var appInstance: AppCache? = null
        fun getInstance(): AppCache {
            if (appInstance == null){
                throw IllegalStateException("Configure a classe Application no AndroidManifest.xml")
            }
            return appInstance!!
        }
    }
}
