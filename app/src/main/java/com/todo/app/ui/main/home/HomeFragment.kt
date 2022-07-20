package com.todo.app.ui.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.todo.app.BaseFragment
import com.todo.app.R
import com.todo.app.data.Task
import com.todo.app.ui.UIFragmentWindowEvents
import com.todo.app.ui.main.EditTaskHandler
import com.todo.app.ui.main.adapter.TaskAdapter
import com.todo.app.ui.main.adapter.TaskAdapterEvents
import com.todo.app.utils.AppUtils
import javax.inject.Inject


class HomeFragment(
    private val listener: EditTaskHandler?
    ) : BaseFragment() {


    constructor() : this(null)

    @BindView(R.id.recycler_task_list)
    lateinit var recyclerTaskList : RecyclerView

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ButterKnife.bind(this, view)

        val taskAdapter = TaskAdapter(object : TaskAdapterEvents {
            override fun onClickTitle(task: Task) {
                listener?.openEditFragment(task)
            }

            override fun onClickStatus(task: Task) {
                task.completed = true
                viewModel.updateTask(task)
            }

        })

        viewModel = ViewModelProvider(this, viewModelFactory)[HomeViewModel::class.java]
        viewModel.getTaskLiveData().observe(viewLifecycleOwner) {
            taskAdapter.updateTaskList(it)
            taskAdapter.notifyDataSetChanged()
        }
        viewModel.getUnCompleteTask()

        recyclerTaskList.layoutManager = LinearLayoutManager(activity)
        recyclerTaskList.adapter = taskAdapter

        val itemTouchHelper = AppUtils.itemTouchHelperBuilder { viewHolder ->
            Toast.makeText(activity, getString(R.string.task_deleted), Toast.LENGTH_SHORT).show()
            viewModel.deleteTask(viewHolder.layoutPosition)
            taskAdapter.notifyDataSetChanged()
        }

        itemTouchHelper.attachToRecyclerView(recyclerTaskList)
    }
}