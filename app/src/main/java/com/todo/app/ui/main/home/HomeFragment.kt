package com.todo.app.ui.main.home

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.todo.app.BaseFragment
import com.todo.app.R
import com.todo.app.data.Task
import com.todo.app.ui.main.TaskAdapter
import com.todo.app.utils.AppUtils
import java.time.LocalDate
import javax.inject.Inject


class HomeFragment : BaseFragment() {

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

        val taskAdapter = TaskAdapter()
        viewModel = ViewModelProvider(this, viewModelFactory)[HomeViewModel::class.java]
        viewModel.getTaskLiveData().observe(viewLifecycleOwner) {
            taskAdapter.updateTaskList(it)
            taskAdapter.notifyDataSetChanged()
        }
        viewModel.getUnCompleteTask()

        recyclerTaskList.layoutManager = LinearLayoutManager(activity)
        recyclerTaskList.adapter = taskAdapter


        val itemTouchHelper = AppUtils.itemTouchHelperBuilder { viewHolder ->
            Toast.makeText(activity, "Task Deleted! ", Toast.LENGTH_LONG).show()
            viewModel.deleteTask(viewHolder.layoutPosition)
            taskAdapter.notifyDataSetChanged()
        }

        itemTouchHelper.attachToRecyclerView(recyclerTaskList)

    }
}