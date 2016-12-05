package com.moka.mokatoyapp.model.domain

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Task : RealmObject(), BaseDomain {

    @PrimaryKey
    var id: Long = 0
    var title: String = ""
    var content: String = ""
    var completed: Boolean = false
    var createdAt: Long = 0
    var updatedAt: Long = 0

}