package com.todo.app.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task_table")
data class Task(
    var title: String,
    var description: String,
    var date: Long = System.currentTimeMillis(),
    var completed: Boolean = false,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
)
