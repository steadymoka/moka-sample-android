package com.moka.mokatoyapp.model


import io.realm.DynamicRealm
import io.realm.RealmMigration


class Migration : RealmMigration {

    override fun migrate(realm: DynamicRealm, oldVersion: Long, newVersion: Long) {
        when (oldVersion.toInt()) {

            0 -> migrate0To1(realm)
        }
    }

    private fun migrate0To1(realm: DynamicRealm) {
        val schema = realm.schema
    }

}
