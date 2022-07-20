package com.todo.app.ui.main.done

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.todo.app.BaseFragment
import com.todo.app.R
import com.todo.app.data.model.Task
import com.todo.app.ui.main.TaskAdapter
import com.todo.app.utils.AppUtils
import java.time.LocalDate

class DoneFragment : BaseFragment() {

    @BindView(R.id.recycler_task_list)
    lateinit var recyclerTaskList : RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_done, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ButterKnife.bind(this, view)

        val taskList = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            arrayListOf(
                Task("Task 1", "Description 1", LocalDate.now(), true),
                Task("Task 2", "Description 2", LocalDate.now(), true),
                Task("Task 3", "Description 3", LocalDate.now(), true),
                Task("Task 4", "Description 4", LocalDate.now(), true),
                Task("Task 5", "Description 5", LocalDate.now(), true),
                Task("Task 6", "Description 6", LocalDate.now(), true),
                Task("Task 7", "Description 7", LocalDate.now(), true),
                Task("Task 8", "Description 8", LocalDate.now(), true),
                Task("Task 9", "Description 9", LocalDate.now(), true),
                Task("Task 0", "Description 0", LocalDate.now(), true),
            )
        } else {
            arrayListOf()
        }

        val taskAdapter = TaskAdapter(taskList)
        recyclerTaskList.layoutManager = LinearLayoutManager(activity)
        recyclerTaskList.adapter = taskAdapter

        val itemTouchHelper = AppUtils.itemTouchHelperBuilder { viewHolder, direction ->
            Toast.makeText(activity, "Deleted Item", Toast.LENGTH_LONG).show()
            // remove/delete view model here
            // -----------------------------
            taskAdapter.notifyDataSetChanged()
        }

        itemTouchHelper.attachToRecyclerView(recyclerTaskList)
    }
}