package com.moka.mokatoyapp.model.repository

import com.moka.framework.util.DateUtil
import com.moka.mokatoyapp.model.RealmHelper
import com.moka.mokatoyapp.model.domain.Observer
import com.moka.mokatoyapp.model.domain.Task
import com.moka.mokatoyapp.model.domain.copy
import com.moka.mokatoyapp.vp.tasklist.TaskListFragment
import rx.Observable

class TaskRepository : ITaskRepository {

    companion object {
        var ob: Observer<Task> = Observer()
    }

    override fun insert(insert: (Task) -> Unit) {
        var copyTask: Task? = null
        RealmHelper.onTransaction { realm ->

            val number = realm.where(Task::class.java).max("id")
            val taskId: Long
            if (null == number)
                taskId = 1
            else
                taskId = number.toLong() + 1

            val realmTask = realm.createObject(Task::class.java, taskId)
            insert(realmTask)
            realmTask.createdAt = DateUtil.timestampInSecond
            realmTask.updatedAt = DateUtil.timestampInSecond

            copyTask = realmTask.copy()
        }
        ob.onInsert(copyTask!!)
    }

    override fun update(task: Task, update: (Task) -> Unit) {
        var copyTask: Task? = null
        RealmHelper.onTransaction { realm ->

            val realmTask = realm.copyToRealmOrUpdate(task)
            update(realmTask)
            realmTask.updatedAt = DateUtil.timestampInSecond

            copyTask = realmTask.copy()
        }
        ob.onUpdate(copyTask!!)
    }

    override fun delete(id: Long) {
        var copyTask: Task? = null
        RealmHelper.onTransaction { realm ->

            val realmTask: Task = realm.where(Task::class.java).equalTo("id", id).findFirst()
            copyTask = realmTask.copy()
            realmTask.deleteFromRealm()
        }
        ob.onDelete(copyTask!!)
    }

    override fun get(id: Long): Observable<Task> {
        var observable: Observable<Task>? = null

        RealmHelper.onInstance { realm ->
            observable = realm.where(Task::class.java)
                    .equalTo("id", id)
                    .findFirst()
                    .asObservable<Task>()
                    .filter { it.isLoaded }
                    .map(Task::copy)
                    .doOnCompleted { realm.close() }
        }
        return observable!!
    }

    override fun getTasks(filterStatus: Int): Observable<Task> {
        var observable: Observable<Task>? = null

        RealmHelper.onInstance { realm ->
            var query = realm.where(Task::class.java)

            query = when (filterStatus) {
                TaskListFragment.ACTIVE_TASKS -> query.equalTo("completed", false)
                TaskListFragment.COMPLETED_TASKS -> query.equalTo("completed", true)
                else -> query
            }

            observable = query.findAll()
                    .asObservable()
                    .filter { it.isLoaded }
                    .first()
                    .flatMap {
                        Observable.from(it.copy())
                    }
                    .doOnCompleted { realm.close() }
        }
        return observable!!
    }

}