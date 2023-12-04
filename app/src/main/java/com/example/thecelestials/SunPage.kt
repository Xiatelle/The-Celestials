package com.example.thecelestials

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SunPage : AppCompatActivity() {

    private val animation = Animation()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sun_page)

        val sun = findViewById<ImageView>(R.id.leftSun)
        val sunDialogSpace = findViewById<TextView>(R.id.sunDialog)
        val sunDialog = getString(R.string.introSun)
        val sunNameSpace = findViewById<TextView>(R.id.sunNameSpace)
        val sunName = getString(R.string.nameSun)
        val rocketShip = findViewById<ImageButton>(R.id.rocketShip)

        animation.animateView(sun, Animation.AnimationType.SLIDE_IN_RIGHT_TWO)

        fun animateTyping(textView: TextView, text: String, delay: Long = 50) {
            var textIndex = 0

            fun animateText() {
                if (textIndex < text.length) {
                    textView.text = text.substring(0, textIndex + 1)
                    textIndex++
                    Handler().postDelayed({ animateText() }, delay)
                }
            }

            animateText()
        }

        fun animateDeleting(textView: TextView, text: String, delay: Long = -100) {
            var textIndex = text.length

            fun animateText() {
                if (textIndex > 0) {
                    textView.text = text.substring(0, textIndex - 1)
                    textIndex--
                    Handler().postDelayed({ animateText() }, delay)
                }
            }

            animateText()
        }

        Handler().postDelayed({
            sunNameSpace.visibility = View.VISIBLE
            animateTyping(sunNameSpace, sunName)
        }, 2000)


        Handler().postDelayed({
            sunDialogSpace.visibility = View.VISIBLE
            animateTyping(sunDialogSpace, sunDialog)

        }, 3500)

        Handler().postDelayed({
            rocketShip.visibility = View.VISIBLE
            animation.animateView(rocketShip, Animation.AnimationType.SLIDE_IN_RIGHT_TWO)
        }, 30000)

        rocketShip.setOnClickListener {
            animation.animateView(sun, Animation.AnimationType.SLIDE_IN_RIGHT){
                animation.animateView(sun, Animation.AnimationType.STOP)
            }
            animateDeleting(sunDialogSpace, sunDialog)

            animation.animateView(sunNameSpace, Animation.AnimationType.SLIDE_IN_RIGHT, 1000){
                animation.animateView(sunNameSpace, Animation.AnimationType.STOP)
            }
            animation.animateView(rocketShip, Animation.AnimationType.SLIDE_IN_RIGHT, 7000){
                animation.animateView(rocketShip, Animation.AnimationType.STOP)
            }

            Handler().postDelayed({
                val process = Intent(this, IntroSolarSystem :: class.java)
                startActivity(process)
                sun.visibility = View.INVISIBLE
                sunNameSpace.visibility = View.INVISIBLE
                sunDialogSpace.visibility = View.INVISIBLE
                rocketShip.visibility = View.INVISIBLE
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            }, 9000)


        }
    }
}