package com.todo.app.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task_table")
data class Task(
    val title: String,
    val description: String,
    val date: Long = System.currentTimeMillis(),
    val completed: Boolean = false,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
)
