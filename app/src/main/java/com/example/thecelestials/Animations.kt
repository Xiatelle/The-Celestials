package com.example.thecelestials

import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.RotateAnimation
import android.view.animation.ScaleAnimation
import android.view.animation.Transformation
import android.view.animation.TranslateAnimation
import kotlin.math.cos
import kotlin.math.sin

class Animations {

    companion object {
        const val ANIMATION_DURATION = 1000L // You can adjust the duration in milliseconds

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
            BLINK,
            STOP,
            DROP,
            REVOLUTIONARY// New dropping animation
        }
    }

    fun animateView(
        view: View,
        animationType: AnimationType,
        offset: Long = 0,
        callback: (() -> Unit)? = null
    ) {
        val animation: Animation = when (animationType) {
            AnimationType.FADE_IN -> AlphaAnimation(0.0f, 1.0f)
            AnimationType.FADE_OUT -> AlphaAnimation(1.0f, 0.0f)
            AnimationType.TRANSLATE -> TranslateAnimation(0f, 0f, offset.toFloat(), 0f)
            AnimationType.SCALE -> ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f)
            AnimationType.ROTATE -> RotateAnimation(0f, 360f)
            AnimationType.SLIDE_IN_RIGHT -> TranslateAnimation(-2000f, 1000f, 0f, 0f).apply {
                repeatMode = Animation.RESTART
                repeatCount = Animation.INFINITE
            }
            AnimationType.SLIDE_IN_RIGHT_TWO -> TranslateAnimation(-2000f, 0f, 0f, 0f)
            AnimationType.SLIDE_IN_LEFT -> TranslateAnimation(2000f, -1000f, 0f, 0f).apply {
                repeatMode = Animation.RESTART
                repeatCount = Animation.INFINITE
            }
            AnimationType.SLIDE_IN_LEFT_TWO -> TranslateAnimation(2000f, 0f, 0f, 0f)
            AnimationType.SLIDE_UP -> {
                val fromYDelta = 0f // Start from the current Y position
                val toYDelta = -550f // Move up by 550 pixels
                val slideUpAnimation = TranslateAnimation(0f, 0f, fromYDelta, toYDelta)
                slideUpAnimation.fillAfter = true
                slideUpAnimation
            }

            AnimationType.BLINK -> AlphaAnimation(0.0f, 1.0f).apply {
                duration = 250 // Half of 500 milliseconds
                startOffset = offset
                repeatMode = Animation.REVERSE
                repeatCount = Animation.INFINITE
            }
            AnimationType.STOP -> AlphaAnimation(1.0f, 1.0f) // Stopping animation (no change)
            AnimationType.DROP -> TranslateAnimation(0f, 0f, -view.height.toFloat(), 0f)
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

        animation.duration = ANIMATION_DURATION
        animation.startOffset = offset

        // Set the callback function to trigger after the animation ends
        animation.setAnimationListener(object : Animation.AnimationListener {
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