package com.todo.app.ui.main.home

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.todo.app.BaseFragment
import com.todo.app.R
import com.todo.app.data.model.Task
import com.todo.app.ui.main.TaskAdapter
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

        viewModel = ViewModelProvider(this, viewModelFactory)[HomeViewModel::class.java]

        val taskList = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            arrayListOf(
                Task("Task 1", "Description 1", LocalDate.now(), false),
                Task("Task 2", "Description 2", LocalDate.now(), false),
                Task("Task 3", "Description 3", LocalDate.now(), false),
                Task("Task 4", "Description 4", LocalDate.now(), false),
                Task("Task 5", "Description 5", LocalDate.now(), false),
                Task("Task 6", "Description 6", LocalDate.now(), false),
                Task("Task 7", "Description 7", LocalDate.now(), false),
                Task("Task 8", "Description 8", LocalDate.now(), false),
                Task("Task 9", "Description 9", LocalDate.now(), false),
                Task("Task 0", "Description 0", LocalDate.now(), false),
            )
        } else {
            arrayListOf()
        }

        val taskAdapter = TaskAdapter(taskList)
        recyclerTaskList.layoutManager = LinearLayoutManager(activity)
        recyclerTaskList.adapter = taskAdapter
    }
}