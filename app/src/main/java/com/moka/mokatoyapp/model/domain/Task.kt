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

    companion object {

//        @Ignore
//        var ob: Observer<Task> = Observer()
//
//        fun insert(insert: (task: Task) -> Unit) {
//            var copyTask: Task? = null
//            RealmHelper.onTransation { realm ->
//
//                val number = realm.where(Task::class.java).max("id")
//                val taskId: Long
//                if (null == number)
//                    taskId = 1
//                else
//                    taskId = number.toLong() + 1
//
//                val task = realm.createObject(Task::class.java, taskId)
//                insert(task)
//                task.createdAt = DateUtil.timestampInSecond
//                task.updatedAt = DateUtil.timestampInSecond
//
//                copyTask = realm.copyFromRealm(task)
//            }
//            ob.onInsert(copyTask!!)
//        }
//
//        fun update(task: Task, update: (realmTask: Task) -> Unit) {
//            var copyTask: Task? = null
//            RealmHelper.onTransation { realm ->
//
//                val realmTask = realm.copyToRealmOrUpdate(task)
//                update(realmTask)
//                realmTask.updatedAt = DateUtil.timestampInSecond
//
//                copyTask = realm.copyFromRealm(realmTask)
//            }
//            ob.onUpdate(copyTask!!)
//        }
//
//        fun delete(taskId: Long) {
//            var copyTask: Task? = null
//            RealmHelper.onTransation { realm ->
//
//                val task = realm.where(Task::class.java).equalTo("id", taskId).findFirst()
//                copyTask = realm.copyFromRealm(task)
//                task.deleteFromRealm()
//            }
//            ob.onDelete(copyTask!!)
//        }
//
//        fun getTask(taskId: Long): Observable<Task> {
//            var observable: Observable<Task>? = null
//
//            RealmHelper.onInstance { realm ->
//                observable = realm.where(Task::class.java)
//                        .equalTo("id", taskId)
//                        .findFirst()
//                        .asObservable<Task>()
//                        .filter { it.isLoaded }
//                        .doOnCompleted { realm.close() }
//            }
//            return observable!!
//        }

//        fun getTasks(filterStatus: Int = 0): Observable<Task> {
//            var observable: Observable<Task>? = null
//
//            RealmHelper.onInstance { realm ->
//                var query = realm.where(Task::class.java)
//
//                query = when (filterStatus) {
//                    ACTIVE_TASKS -> query.equalTo("completed", false)
//                    COMPLETED_TASKS -> query.equalTo("completed", true)
//                    else -> query
//                }
//
//                observable = query.findAll()
//                        .asObservable()
//                        .filter { it.isLoaded }
//                        .first()
//                        .flatMap {
//                            Observable.from(realm.copyFromRealm(it))
//                        }
//                        .doOnCompleted { realm.close() }
//            }
//            return observable!!
//        }
    }

}