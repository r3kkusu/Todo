package com.todo.app.ui.main.adapter

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

class TaskAdapter constructor(private val listener: TaskAdapterEvents): RecyclerView.Adapter<TaskAdapter.TaskHolder>() {

    private var taskList: List<Task> = listOf()

    class TaskHolder(view: View) : RecyclerView.ViewHolder(view) {

        @BindView(R.id.txt_task_title)
        lateinit var title : TextView

        @BindView(R.id.btn_task_complete)
        lateinit var status : ImageButton

        init {
            ButterKnife.bind(this, view);
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskHolder {
        val inflate = LayoutInflater.from(parent.context).inflate(R.layout.task_item, parent, false)
        return TaskHolder(inflate)
    }

    override fun onBindViewHolder(holder: TaskHolder, position: Int) {
        val task = taskList[position];

        holder.title.text = task.title
        if (taskList[position].completed) {
            holder.status.setImageResource(R.drawable.ic_baseline_check_circle_24)
        } else {
            holder.status.setImageResource(R.drawable.ic_baseline_check_circle_outline_24)
        }

        holder.title.setOnClickListener {
            listener.onClickTitle(task)
        }

        holder.status.setOnClickListener {
            listener.onClickStatus(task)
        }
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    fun updateTaskList(tasks: List<Task>) {
        taskList = tasks
    }
}