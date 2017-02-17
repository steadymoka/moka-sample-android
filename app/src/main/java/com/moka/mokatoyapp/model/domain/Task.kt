package com.moka.mokatoyapp.model.domain

import io.realm.RealmObject
import io.realm.RealmResults
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

fun Task.copy(): Task {
    val task = Task()
    task.id = this.id
    task.title = this.title
    task.content = this.content
    task.completed = this.completed
    task.createdAt = this.createdAt
    task.updatedAt = this.updatedAt
    return task
}

fun RealmResults<Task>.copy(): List<Task> {
    return this.map { it.copy() }
}