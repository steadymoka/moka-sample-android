package com.moka.mokatoyapp.model.domain

import com.moka.framework.util.DateUtil
import com.moka.mokatoyapp.model.RealmHelper
import io.realm.RealmObject
import io.realm.annotations.Ignore

open class Task : RealmObject(), BaseDomain {

    var id: Long = 0
    var title: String = ""
    var content: String = ""
    var createdAt: Long = 0
    var updatedAt: Long = 0

    companion object {

        @Ignore
        var ob: Observe<Task> = Observe()

        fun insert(insert: (task: Task) -> Unit): Task {
            val realm = RealmHelper.instance
            val number = realm.where(Task::class.java).max("id")
            val taskId: Long
            if (null == number)
                taskId = 1
            else
                taskId = number.toLong() + 1

            /* *************************** */
            realm.beginTransaction()
            val task = realm.createObject(Task::class.java)
            task.id = taskId
            insert(task)
            task.createdAt = DateUtil.timestampInSecond
            task.updatedAt = DateUtil.timestampInSecond

            realm.commitTransaction()
            realm.close()
            /* *************************** */

            ob.onInsert(task)
            return task
        }

        fun update(task: Task, update: (realmTask: Task) -> Unit) {
            val realm = RealmHelper.instance
            realm.beginTransaction()

            val realmTask = realm.copyToRealmOrUpdate(task)
            update(realmTask)
            task.updatedAt = DateUtil.timestampInSecond

            realm.commitTransaction()
            realm.close()

            ob.onUpdate(task)
        }

    }

}