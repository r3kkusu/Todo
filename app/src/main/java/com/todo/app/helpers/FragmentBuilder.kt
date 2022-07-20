package com.todo.app.helpers

import com.todo.app.BaseFragment
import com.todo.app.ui.main.UIFragmentWindowEvent

class FragmentBuilder {
    companion object {
        fun <T : BaseFragment> fragmentFactory(detachListener: UIFragmentWindowEvent, baseFragment: T) : T {
            baseFragment.setOnDetachListener(detachListener)
            return baseFragment
        }
    }
}