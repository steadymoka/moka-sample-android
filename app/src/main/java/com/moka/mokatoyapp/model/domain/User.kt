package com.moka.mokatoyapp.model.domain

import com.moka.framework.util.DateUtil
import io.realm.Realm
import io.realm.RealmObject
import io.realm.annotations.Ignore

open class User : RealmObject(), BaseDomain {

    var id: Long = 0
    var email: String? = null
    var name: String? = null
    var createdAt: Long = 0
    var updatedAt: Long = 0

    companion object {

        @Ignore
        var ob: Observe<User> = Observe()

        fun insert(realm: Realm, insert: (user: User) -> Unit): User {
            val number = realm.where(User::class.java).max("id")
            val userId: Long
            if (null == number)
                userId = 1
            else
                userId = number.toLong() + 1

            /* *************************** */
            realm.beginTransaction()
            val user = realm.createObject(User::class.java)
            user.id = userId
            insert(user)
            user.createdAt = DateUtil.timestampInSecond
            user.updatedAt = DateUtil.timestampInSecond
            realm.commitTransaction()
            /* *************************** */

            ob.onInsert(user)
            return user
        }

        fun update(realm: Realm, user: User, update: () -> Unit) {
            realm.beginTransaction()
            update()
            user.updatedAt = DateUtil.timestampInSecond
            realm.commitTransaction()

            ob.onUpdate(user)
        }

    }

}