package com.moka.mokatoyapp.tasklist


import com.moka.framework.widget.adapter.IAdapterModel
import com.moka.mokatoyapp.model.repository.ITaskRepository
import com.moka.mokatoyapp.vp.tasklist.TaskListPresenter
import com.moka.mokatoyapp.vp.tasklist.TaskListView
import org.junit.Before
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class TaskListPresenterTest {

    @Mock
    private lateinit var taskListView: TaskListView

    @Mock
    private lateinit var adapterModel: IAdapterModel

    @Mock
    private lateinit var taskRepository: ITaskRepository

    private lateinit var presenter: TaskListPresenter

    @Before
    fun setupTasksPresenter() {
        // Mockito has a very convenient way to inject mocks by using the @Mock annotation. To
        // inject the mocks in the test the initMocks method needs to be called.
        MockitoAnnotations.initMocks(this)

        // Get a reference to the class under test
        presenter = TaskListPresenter(adapterModel, taskRepository)
        presenter.attachView(taskListView)
    }

}