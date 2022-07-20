package com.todo.app.ui.main.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.annotation.MainThread
import com.todo.app.BaseFragment
import com.todo.app.R
import kotlinx.android.synthetic.main.fragment_edit.*


class EditFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_edit, container, false)
    }

    @MainThread
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val animation: Animation = AnimationUtils.loadAnimation(activity, R.anim.fade_out)
        animation.duration = resources.getInteger(android.R.integer.config_shortAnimTime).toLong()

        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(p0: Animation?) {}

            override fun onAnimationRepeat(p0: Animation?) {}

            override fun onAnimationEnd(p0: Animation?) {
                try {
                    val transaction = activity?.supportFragmentManager?.beginTransaction()
                    if (transaction != null) {
                        transaction.remove(this@EditFragment )
                        transaction.commitAllowingStateLoss()
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        })


        btn_task_delete.setOnClickListener {
            view.startAnimation(animation)
        }

        btn_task_back.setOnClickListener {
            view.startAnimation(animation)
        }

        init()
    }

    private fun init() {

    }
}