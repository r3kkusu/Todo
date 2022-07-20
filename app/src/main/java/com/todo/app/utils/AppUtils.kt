package com.todo.app.utils

import android.util.Log
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import dagger.android.support.DaggerFragment

class AppUtils {
    companion object {
        val TAG = "AppUtils"

        fun replaceFragment(activity: FragmentActivity, fragment: DaggerFragment, animations: IntArray, fragmentView: Int) {
            Log.d(TAG, "replaceFragment: " + fragment.javaClass.name)
            val transaction = activity.supportFragmentManager
                .beginTransaction()
                .setCustomAnimations(animations[0], animations[1], animations[2], animations[3])
            transaction.replace(fragmentView, fragment)
            transaction.commit()
        }

        fun itemTouchHelperBuilder(callbackAction: (viewHolder: RecyclerView.ViewHolder) -> Unit) : ItemTouchHelper {
            return ItemTouchHelper(
                object : ItemTouchHelper.SimpleCallback(0,
                    ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
                ) {
                    override fun onMove(
                        recyclerView: RecyclerView,
                        viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder
                    ): Boolean {
                        return true
                    }

                    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                        callbackAction(viewHolder)
                    }
                })
        }
    }
}