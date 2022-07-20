package com.todo.app.ui.main

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.todo.app.R
import com.todo.app.data.Task
import com.todo.app.ui.UIFragmentWindowEvents
import com.todo.app.ui.main.done.DoneFragment
import com.todo.app.ui.main.home.HomeFragment
import com.todo.app.ui.main.edit.EditFragment
import com.todo.app.utils.AnimationUtils
import com.todo.app.utils.AppUtils
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity(),
    UIFragmentWindowEvents,
    EditTaskHandler {

    @BindView(R.id.txt_add_task)
    lateinit var btnAddTask: TextView

    @BindView(R.id.txt_header)
    lateinit var headerTitle : TextView

    @BindView(R.id.navigation_view)
    lateinit var navigationView: BottomNavigationView

    @BindView(R.id.dialog_fragment)
    lateinit var dialogFragment: FrameLayout

    private val TAG = "MainActivity"
    private var fragmentId = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
        init()
    }

    private fun init() {

        btnAddTask.setOnClickListener {
            btnAddTask.post {
                openEditFragment(null)
            }
        }

        setupNavFragment(R.id.home)
        navigationView.setOnItemSelectedListener {
            setupNavFragment(it.itemId)
            return@setOnItemSelectedListener true
        }
    }

    private fun setupNavFragment(fragmentID: Int) {
        if (fragmentId != fragmentID) {
            fragmentId = fragmentID
            when(fragmentID) {
                R.id.home -> {
                    headerTitle.text = getString(R.string.incomplete)
                    AppUtils.replaceFragment(this, HomeFragment(this),
                        AnimationUtils.ANIM_LIFT_TO_RIGHT, R.id.fragment_view)
                }
                R.id.done -> {
                    headerTitle.text = getString(R.string.completed)
                    AppUtils.replaceFragment(this, DoneFragment(this),
                        AnimationUtils.ANIM_RIGHT_TO_LIFE, R.id.fragment_view)
                }
            }
        }
    }

    override fun onWindowOpen() {
        dialogFragment.visibility = View.VISIBLE
    }

    override fun onWindowClosed() {
        dialogFragment.visibility =  View.GONE
    }

    override fun openEditFragment(task: Task?) {
        AppUtils.replaceFragment(this, EditFragment(task, this), AnimationUtils.ANIM_FADE_IN, R.id.dialog_fragment)
    }
}