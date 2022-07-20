package com.todo.app.utils

import com.todo.app.R

class AnimationUtils {
    companion object {
        val ANIM_RIGHT_TO_LIFE = intArrayOf(
            R.anim.slide_in_right,
            R.anim.slide_out_right,
            R.anim.slide_in_left,
            R.anim.slide_out_left)

        val ANIM_LIFT_TO_RIGHT = intArrayOf(
            R.anim.slide_in_left,
            R.anim.slide_out_left,
            R.anim.slide_in_right,
            R.anim.slide_out_right)

        val ANIM_FADE_IN = intArrayOf(
            R.anim.fade_in,
            R.anim.fade_out,
            R.anim.fade_in,
            R.anim.fade_out)
    }
}