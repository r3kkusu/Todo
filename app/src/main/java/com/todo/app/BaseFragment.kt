package com.todo.app

import android.content.Context
import com.todo.app.ui.main.UIFragmentWindowEvent
import dagger.android.support.DaggerFragment

open class BaseFragment : DaggerFragment() {
    private var listener: UIFragmentWindowEvent? = null

    fun setOnDetachListener(listener: UIFragmentWindowEvent) {
        this.listener = listener
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener?.onAttach()
    }

    override fun onDetach() {
        super.onDetach()
        listener?.onDetach()
    }
}