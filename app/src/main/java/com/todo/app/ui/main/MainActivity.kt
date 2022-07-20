package com.todo.app.ui.main

import android.os.Bundle
import android.view.View
import com.todo.app.R
import com.todo.app.helpers.FragmentBuilder
import com.todo.app.ui.main.done.DoneFragment
import com.todo.app.ui.main.home.HomeFragment
import com.todo.app.ui.main.edit.EditFragment
import com.todo.app.utils.AnimationUtils
import com.todo.app.utils.AppUtils
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : DaggerAppCompatActivity(), UIFragmentWindowEvent {

    private val TAG = "MainActivity"
    private var fragmentId = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        super.onDetachedFromWindow()

        init()

    }


    private fun init() {

        btn_add_task.setOnClickListener {
//            root.isEnabled = false
            btn_add_task.post {
                val fragment = FragmentBuilder.fragmentFactory(this, EditFragment())
                AppUtils.replaceFragment(this, fragment, AnimationUtils.ANIM_FADE_IN, R.id.dialog_fragment)
            }
        }

        setupNavFragment(R.id.home)
        navigation_view.setOnItemSelectedListener {
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
                    AppUtils.replaceFragment(this, fragment, AnimationUtils.ANIM_RIGHT_TO_LIFE, R.id.fragment_view)
                }
                R.id.done -> {
                    val fragment = FragmentBuilder.fragmentFactory(this, DoneFragment())
                    AppUtils.replaceFragment(this, fragment, AnimationUtils.ANIM_LIFT_TO_RIGHT, R.id.fragment_view)
                }
            }
        }
    }

    override fun onAttach() {
        dialog_fragment.visibility = View.VISIBLE
    }

    override fun onDetach() {
        dialog_fragment.visibility =  View.GONE
    }
}