package com.example.thecelestials

import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.Animation.AnimationListener
import android.view.animation.AnimationSet
import android.view.animation.RotateAnimation
import android.view.animation.ScaleAnimation
import android.view.animation.Transformation
import android.view.animation.TranslateAnimation
import kotlin.math.cos
import kotlin.math.sin

class Animation {

    enum class AnimationType {
        FADE_IN,
        FADE_OUT,
        TRANSLATE,
        SCALE,
        ROTATE,
        SLIDE_IN_RIGHT,
        SLIDE_IN_RIGHT_TWO,
        SLIDE_IN_LEFT,
        SLIDE_IN_LEFT_TWO,
        SLIDE_UP,
        SLIDE_DOWN,
        BLINK,
        STOP,
        DROP,
        REVOLUTIONARY,
        FALL
    }

    fun animateView(view: View, animationType: AnimationType, offset: Long = 0, callback: (() -> Unit)? = null) {
        val animation: Animation = when (animationType) {

            AnimationType.FADE_IN -> AlphaAnimation(0.0f, 1.0f).apply {
                duration = 1000L
                startOffset = offset
            }

            AnimationType.FADE_OUT -> AlphaAnimation(1.0f, 0.0f).apply {
                duration = 1000L
                startOffset = offset
            }

            AnimationType.TRANSLATE -> TranslateAnimation(0f, 0f, offset.toFloat(), 0f).apply {
                duration = 1000L
                startOffset = offset
            }

            AnimationType.SCALE -> ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f).apply {
                duration = 1000L
                startOffset = offset
            }

            AnimationType.ROTATE -> RotateAnimation(0f, 360f).apply {
                duration = 1000L
                startOffset = offset
            }
            AnimationType.SLIDE_IN_RIGHT -> TranslateAnimation(0f, 1500f, 0f, 0f).apply {
                duration = 2000L // Set the specific duration for this animation
                startOffset = offset
            }
            AnimationType.SLIDE_IN_RIGHT_TWO -> TranslateAnimation(-2000f, 0f, 0f, 0f).apply {
                duration = 1000L
                startOffset = offset
            }

            AnimationType.SLIDE_IN_LEFT -> TranslateAnimation(2000f, -1000f, 0f, 0f).apply {
                repeatMode = Animation.RESTART
                repeatCount = Animation.INFINITE
                duration = 1000L // Set the specific duration for this animation
            }

            AnimationType.SLIDE_IN_LEFT_TWO -> TranslateAnimation(2000f, 0f, 0f, 0f)
                .apply {
                    duration = 1000L
                    startOffset = offset
                }

            AnimationType.SLIDE_UP -> {
                val fromYDelta = 0f
                val toYDelta = -view.height.toFloat() // Adjust the value as needed
                TranslateAnimation(0f, 0f, fromYDelta, toYDelta).apply {
                    duration = 1000L
                    startOffset = offset
                }
            }

            // Add a new case for SLIDE_DOWN
            AnimationType.SLIDE_DOWN -> {
                val fromYDelta = -view.height.toFloat() // Start off the screen above
                val toYDelta = 0f // Drop down to the current position
                TranslateAnimation(0f, 0f, fromYDelta, toYDelta).apply {
                    duration = 1000L
                    startOffset = offset
                }
            }


            AnimationType.BLINK -> AlphaAnimation(0.0f, 1.0f).apply {
                duration = 250 // Half of 500 milliseconds
                startOffset = offset
                repeatMode = Animation.REVERSE
                repeatCount = Animation.INFINITE
            }

            AnimationType.STOP -> AlphaAnimation(1.0f, 1.0f).apply {
                duration = 10000L
            } // Stopping animation (no change)

            AnimationType.DROP -> {
                val fromYDelta = 0f
                val toYDelta = view.height.toFloat() // Adjust the value as needed
                TranslateAnimation(0f, 0f, fromYDelta, toYDelta).apply {
                    duration = 1000L
                    startOffset = offset
                }
            }

            AnimationType.FALL -> {
                val fromYDelta = -2000f
                val toYDelta = 0f // Adjust the value as needed
                TranslateAnimation(0f, 0f, fromYDelta, toYDelta).apply {
                    duration = 1000L // Set the specific duration for this animation
                    startOffset = offset
                }
            }

            AnimationType.REVOLUTIONARY -> {
                // Define your revolutionary animation here
                val revolutionRadius = 10f // Set the desired radius
                val centerX = view.width / 2f
                val centerY = view.height / 2f

                object : Animation() {
                    override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
                        val adjustedInterpolatedTime = interpolatedTime % 1.0f // Keep it within [0, 1]
                        val angle = adjustedInterpolatedTime * 2f * Math.PI // Full revolution

                        val x = centerX + (revolutionRadius * cos(angle)).toFloat()
                        val y = centerY + (revolutionRadius * sin(angle)).toFloat()

                        view.x = x - view.width * -0.6F
                        view.y = y - view.height * -10F
                    }
                }.apply {
                    duration = 1000L // Set the specific duration for this animation
                    repeatMode = Animation.RESTART
                    repeatCount = Animation.INFINITE
                }
            }
        }

        animation.setAnimationListener(object : AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
                // Not used
            }

            override fun onAnimationEnd(animation: Animation?) {
                // Trigger the callback function after the animation ends
                callback?.invoke()
            }

            override fun onAnimationRepeat(animation: Animation?) {
                // Not used
            }
        })

        val animationSet = AnimationSet(true)
        animationSet.addAnimation(animation)
        view.startAnimation(animationSet)
    }
}
