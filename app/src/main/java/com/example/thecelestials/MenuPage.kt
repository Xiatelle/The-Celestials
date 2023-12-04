package com.example.thecelestials

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MenuPage : AppCompatActivity() {

    private val animation = Animation()
    private val animations = Animations()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_page)

        val sun = findViewById<ImageView>(R.id.smallSun2)
        val mercury = findViewById<ImageView>(R.id.smallMercury2)
        val venus = findViewById<ImageView>(R.id.smallVenus2)
        val earth = findViewById<ImageView>(R.id.smallEarth2)
        val mars = findViewById<ImageView>(R.id.smallMars2)
        val jupiter = findViewById<ImageView>(R.id.smallJupiter2)
        val saturn = findViewById<ImageView>(R.id.smallSaturn2)
        val uranus = findViewById<ImageView>(R.id.smallUranus2)
        val neptune = findViewById<ImageView>(R.id.smallNeptune2)
        val titleOne = findViewById<ImageView>(R.id.titleOne)
        val titleTwo = findViewById<ImageView>(R.id.titleTwo)
        val tapToBegin = findViewById<ImageButton>(R.id.imgButton)


        fun secondAnim() {
            val revolution = AnimationUtils.loadAnimation(this, R.anim.revolution)

            sun.startAnimation(revolution)
            mercury.startAnimation(revolution)
            venus.startAnimation(revolution)
            earth.startAnimation(revolution)
            mars.startAnimation(revolution)
            jupiter.startAnimation(revolution)
            saturn.startAnimation(revolution)
            uranus.startAnimation(revolution)
            neptune.startAnimation(revolution)

            titleOne.visibility = View.VISIBLE
            animation.animateView(titleOne, Animation.AnimationType.FADE_IN, 500)

            titleTwo.visibility = View.VISIBLE
            animation.animateView(titleTwo, Animation.AnimationType.FADE_IN, 1500)

            Handler().postDelayed({
                tapToBegin.visibility = View.VISIBLE
                animation.animateView(tapToBegin, Animation.AnimationType.BLINK, )
            }, 3000)


            tapToBegin.setOnClickListener {
                animations.animateView(tapToBegin,Animations.Companion.AnimationType.STOP)
                animations.animateView(sun, Animations.Companion.AnimationType.STOP) {
                    animation.animateView(sun, Animation.AnimationType.SLIDE_IN_RIGHT) {
                        sun.visibility = View.INVISIBLE
                        mercury.visibility = View.INVISIBLE
                        venus.visibility = View.INVISIBLE
                        mars.visibility = View.INVISIBLE
                        jupiter.visibility = View.INVISIBLE
                        saturn.visibility = View.INVISIBLE
                        uranus.visibility = View.INVISIBLE
                        neptune.visibility = View.INVISIBLE
                        titleOne.visibility = View.INVISIBLE
                        titleTwo.visibility = View.INVISIBLE

                        val process = Intent(this, SunPage :: class.java)
                        startActivity(process)
                        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)

                        Handler().postDelayed({
                            sun.visibility = View.VISIBLE
                            mercury.visibility = View.VISIBLE
                            venus.visibility = View.VISIBLE
                            mars.visibility = View.VISIBLE
                            jupiter.visibility = View.VISIBLE
                            saturn.visibility = View.VISIBLE
                            uranus.visibility = View.VISIBLE
                            neptune.visibility = View.VISIBLE
                            titleOne.visibility = View.VISIBLE
                            titleTwo.visibility = View.VISIBLE
                        }, 2000)
                    }
                }

            }

        }



        fun firstAnim() {
            animation.animateView(sun, Animation.AnimationType.FALL, 500)
            animation.animateView(mercury, Animation.AnimationType.FALL, 1500)
            animation.animateView(venus, Animation.AnimationType.FALL, 2000)
            animation.animateView(earth, Animation.AnimationType.FALL, 2500)
            animation.animateView(mars, Animation.AnimationType.FALL, 3000)
            animation.animateView(jupiter, Animation.AnimationType.FALL, 3500)
            animation.animateView(saturn, Animation.AnimationType.FALL, 4000)
            animation.animateView(uranus, Animation.AnimationType.FALL, 4500)
            animation.animateView(neptune, Animation.AnimationType.FALL, 5000) {
                secondAnim()
            }
        }

        firstAnim()
    }
}