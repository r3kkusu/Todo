package com.todo.app

import android.content.Context
import com.todo.app.ui.UIFragmentWindowEvents
import dagger.android.support.DaggerFragment

open class BaseFragment constructor(
    private val windowsListener: UIFragmentWindowEvents?
    ) : DaggerFragment() {

    constructor() : this(null)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        windowsListener?.onWindowOpen()
    }

    override fun onDetach() {
        super.onDetach()
        windowsListener?.onWindowClosed()
    }
}