package com.example.thecelestials

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class IntroThree : AppCompatActivity() {

    private val animation = Animation() // Assuming you have this class defined

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sun = findViewById<ImageView>(R.id.smallSun)
        val mercury = findViewById<ImageView>(R.id.smallMercury)
        val venus = findViewById<ImageView>(R.id.smallVenus)
        val earth = findViewById<ImageView>(R.id.smallEarth)
        val mars = findViewById<ImageView>(R.id.smallMars)
        val jupiter = findViewById<ImageView>(R.id.smallJupiter)
        val saturn = findViewById<ImageView>(R.id.smallSaturn)
        val uranus = findViewById<ImageView>(R.id.smallUranus)
        val neptune = findViewById<ImageView>(R.id.smallNeptune)

        fun thirdAnim(){
            val revolution = AnimationUtils.loadAnimation(this, R.anim.rotation)

            sun.startAnimation(revolution)
            mercury.startAnimation(revolution)
            venus.startAnimation(revolution)
            earth.startAnimation(revolution)
            mars.startAnimation(revolution)
            jupiter.startAnimation(revolution)
            saturn.startAnimation(revolution)
            uranus.startAnimation(revolution)
            neptune.startAnimation(revolution)
        }

        fun secondAnim() {
            animation.animateView(sun, Animation.AnimationType.SLIDE_UP, 500) {
                animation.animateView(sun, Animation.AnimationType.STOP)

            }

            animation.animateView(mercury, Animation.AnimationType.SLIDE_UP, 1000){
                animation.animateView(mercury, Animation.AnimationType.STOP)

            }

            animation.animateView(venus, Animation.AnimationType.SLIDE_UP, 1500){

                animation.animateView(venus, Animation.AnimationType.STOP)
            }
            animation.animateView(earth, Animation.AnimationType.SLIDE_UP, 2000){
                animation.animateView(earth, Animation.AnimationType.STOP)

            }

            animation.animateView(mars, Animation.AnimationType.SLIDE_UP, 2500){
                animation.animateView(mars, Animation.AnimationType.STOP)
            }

            animation.animateView(jupiter, Animation.AnimationType.SLIDE_UP, 3000){
                animation.animateView(jupiter, Animation.AnimationType.STOP)

            }

            animation.animateView(saturn, Animation.AnimationType.SLIDE_UP, 3500){
                animation.animateView(saturn, Animation.AnimationType.STOP)

            }

            animation.animateView(uranus, Animation.AnimationType.SLIDE_UP, 4000){
                animation.animateView(uranus, Animation.AnimationType.STOP)

            }

            animation.animateView(neptune, Animation.AnimationType.SLIDE_UP, 4500){
                animation.animateView(neptune, Animation.AnimationType.STOP)

            }

            Handler().postDelayed({
                val process = Intent(this, MenuPage :: class.java)
                startActivity(process)
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            }, 6000)

            Handler().postDelayed({
                thirdAnim()
            },7500)

        }



        fun firstAnim() {
            animation.animateView(sun, Animation.AnimationType.FADE_IN, 0)
            animation.animateView(mercury, Animation.AnimationType.FADE_IN, 500)
            animation.animateView(venus, Animation.AnimationType.FADE_IN, 1000)
            animation.animateView(earth, Animation.AnimationType.FADE_IN, 1500)
            animation.animateView(mars, Animation.AnimationType.FADE_IN, 2000)
            animation.animateView(jupiter, Animation.AnimationType.FADE_IN, 2500)
            animation.animateView(saturn, Animation.AnimationType.FADE_IN, 3000)
            animation.animateView(uranus, Animation.AnimationType.FADE_IN, 3500)
            animation.animateView(neptune, Animation.AnimationType.FADE_IN, 4000) {
            secondAnim()
            }

        }
        firstAnim()


    }
}