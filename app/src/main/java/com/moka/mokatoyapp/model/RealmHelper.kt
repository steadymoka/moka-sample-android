package com.moka.mokatoyapp.model


import android.content.Context
import io.realm.Realm
import io.realm.RealmConfiguration


object RealmHelper {

    private val DB_VERSION = 1

    fun init(context: Context) {
        Realm.init(context)
        val config = RealmConfiguration.Builder()
                .name("default.realm")
                .schemaVersion(DB_VERSION.toLong())
                .migration(Migration())
                .build()

        Realm.setDefaultConfiguration(config)
    }

    val instance: Realm
        get() = Realm.getDefaultInstance()

    fun onInstance(work: (realm: Realm) -> Unit) {
        val realm = instance
        work(realm)
    }

    fun onTransation(work: (realm: Realm) -> Unit) {
        val realm = instance
        realm.beginTransaction()
        work(realm)
        realm.commitTransaction()
        realm.close()
    }

}
