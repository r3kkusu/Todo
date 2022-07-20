package com.todo.app.ui.main

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageButton
import butterknife.BindView
import butterknife.ButterKnife
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.todo.app.R
import com.todo.app.helpers.FragmentBuilder
import com.todo.app.ui.UIFragmentWindowEvent
import com.todo.app.ui.main.done.DoneFragment
import com.todo.app.ui.main.home.HomeFragment
import com.todo.app.ui.main.edit.EditFragment
import com.todo.app.utils.AnimationUtils
import com.todo.app.utils.AppUtils
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity(), UIFragmentWindowEvent {

    @BindView(R.id.btn_add_task)
    lateinit var btnAddTask: ImageButton

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
//            root.isEnabled = false
            btnAddTask.post {
                val fragment = FragmentBuilder.fragmentFactory(this, EditFragment())
                AppUtils.replaceFragment(this, fragment, AnimationUtils.ANIM_FADE_IN, R.id.dialog_fragment)
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
                    val fragment = FragmentBuilder.fragmentFactory(this, HomeFragment())
                    AppUtils.replaceFragment(this, fragment, AnimationUtils.ANIM_LIFT_TO_RIGHT, R.id.fragment_view)
                }
                R.id.done -> {
                    val fragment = FragmentBuilder.fragmentFactory(this, DoneFragment())
                    AppUtils.replaceFragment(this, fragment, AnimationUtils.ANIM_RIGHT_TO_LIFE, R.id.fragment_view)
                }
            }
        }
    }

    override fun onAttach() {
        dialogFragment.visibility = View.VISIBLE
    }

    override fun onDetach() {
        dialogFragment.visibility =  View.GONE
    }
}