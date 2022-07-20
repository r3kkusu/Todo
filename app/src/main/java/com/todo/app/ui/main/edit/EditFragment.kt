package com.todo.app.ui.main.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageButton
import androidx.annotation.MainThread
import butterknife.BindView
import butterknife.ButterKnife
import com.todo.app.BaseFragment
import com.todo.app.R


class EditFragment : BaseFragment() {

    @BindView(R.id.btn_task_delete)
    lateinit var btnTaskDelete: ImageButton

    @BindView(R.id.btn_task_back)
    lateinit var btnTaskBack: ImageButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_edit, container, false)
    }

    @MainThread
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        ButterKnife.bind(this, view)

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


        btnTaskDelete.setOnClickListener {
            view.startAnimation(animation)
        }

        btnTaskBack.setOnClickListener {
            view.startAnimation(animation)
        }

        init()
    }

    private fun init() {

    }
}