package com.todo.app.ui.main.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.annotation.MainThread
import androidx.lifecycle.ViewModelProvider
import butterknife.BindView
import butterknife.ButterKnife
import com.todo.app.BaseFragment
import com.todo.app.R
import com.todo.app.data.Task
import javax.inject.Inject


class EditFragment constructor(
    private var task: Task?
) : BaseFragment() {

    @BindView(R.id.btn_task_back)
    lateinit var btnTaskBack: ImageButton

    @BindView(R.id.txt_task_title)
    lateinit var txtTaskTitle: EditText

    @BindView(R.id.txt_task_desc)
    lateinit var txtTaskDesc: EditText

    @BindView(R.id.txt_task_done)
    lateinit var btnTaskDone: TextView

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: EditViewModel

    private lateinit var title: String
    private lateinit var description: String

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

        // Fade out animation when user closes fragment
        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(anim: Animation) {}
            override fun onAnimationRepeat(anim: Animation) {}
            override fun onAnimationEnd(anim: Animation) {
                try {
                    val transaction = activity?.supportFragmentManager?.beginTransaction()
                    transaction?.let {
                        it.remove(this@EditFragment )
                        it.commitAllowingStateLoss()
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        })

        btnTaskBack.setOnClickListener {
            updateTask()
            view.startAnimation(animation)
        }

        btnTaskDone.setOnClickListener {
            updateTask()
            view.startAnimation(animation)
        }

        init()
    }

    private fun init() {
        viewModel = ViewModelProvider(this, viewModelFactory)[EditViewModel::class.java]
        task?.let {
            txtTaskTitle.setText(it.title)
            txtTaskDesc.setText(it.description)
        }
    }

    private fun updateTask() {
        if (!isNotValidInputs()) {
            justifyTitle()
            encapsulateData()
            task?.let {
                viewModel.insertTask(it)
            }
        }
    }

    private fun isNotValidInputs() : Boolean {
        title = txtTaskTitle.text.toString()
        description = txtTaskDesc.text.toString()
        return (title.isEmpty() && description.isEmpty())
    }

    private fun justifyTitle() {
        if (title.isEmpty())
            title = description
    }

    private fun encapsulateData() {
        task?.let {
            it.title = title
            it.description = description
        }

        if (task == null) {
            task = Task(title, description, false)
        }
    }
}