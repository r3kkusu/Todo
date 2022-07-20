package com.todo.app.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.todo.app.R
import com.todo.app.data.Task

class TaskAdapter : RecyclerView.Adapter<TaskAdapter.TaskHolder>() {

    private var taskList: List<Task> = listOf()

    class TaskHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

        @BindView(R.id.txt_task_title)
        lateinit var title : TextView

        @BindView(R.id.btn_task_complete)
        lateinit var status : ImageButton

        init {

            ButterKnife.bind(this, view);
            title.setOnClickListener(this)
            status.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskHolder {
        val inflate = LayoutInflater.from(parent.context).inflate(R.layout.task_item, parent, false)
        return TaskHolder(inflate)
    }

    override fun onBindViewHolder(holder: TaskHolder, position: Int) {
        holder.title.text = taskList[position].title
        if (taskList[position].completed) {
            holder.status.setImageResource(R.drawable.ic_baseline_check_circle_24)
        } else {
            holder.status.setImageResource(R.drawable.ic_baseline_check_circle_outline_24)
        }
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    fun updateTaskList(tasks: List<Task>) {
        taskList = tasks
    }
}