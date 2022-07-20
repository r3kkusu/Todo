package com.todo.app.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.todo.app.data.Task
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

// Could not compare id because id's are generated once save in database
// manual comparison is need

public fun containsTaskObject(tasks: List<Task>, task: Task) : Boolean {
    var contains = false

    tasks.forEach {
        if (isEqualTaskObject(it, task)) {
            contains = true
            return@forEach
        }
    }

    return contains
}

fun isEqualTaskObject(task1: Task, task2: Task): Boolean {
    return task1.title == task2.title &&
            task1.description == task2.description &&
            task1.completed == task2.completed &&
            task1.date == task1.date
}

fun <T> LiveData<T>.getOrAwaitValue() : T {
    var data : T? = null
    val latch = CountDownLatch(1)

    val observer = object: Observer<T> {
        override fun onChanged(t: T) {
            data = t
            this@getOrAwaitValue.removeObserver(this)
            latch.countDown()
        }
    }

    this.observeForever(observer)

    try {
        if(!latch.await(2, TimeUnit.SECONDS)) {
            throw TimeoutException("Live data never gets its value")
        }
    } finally {
        this.removeObserver(observer)
    }

    return data as T
}