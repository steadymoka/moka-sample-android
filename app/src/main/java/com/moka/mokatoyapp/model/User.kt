package com.moka.mokatoyapp.model

import io.realm.RealmObject

open class User : RealmObject() {

    var email: String? = null
    var name: String? = null
    var createdAt: Long = 0
    var updatedAt: Long = 0

}