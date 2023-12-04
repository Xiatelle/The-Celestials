package com.example.thecelestials

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Earth : AppCompatActivity() {

    private var animation = Animation()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_earth)

        val earth = findViewById<ImageView>(R.id.earth)
        val earthName = getString(R.string.earth)
        val earthNameSpace = findViewById<TextView>(R.id.earthNameSpace)
        val earthDialog = getString(R.string.earth_dialog)
        val earthDialogSpace = findViewById<TextView>(R.id.earthDialogSpace)
        val rocketShip = findViewById<ImageButton>(R.id.rocketShip)

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

        fun firstAnim(){
            animation.animateView(earth, Animation.AnimationType.FALL){
                earthNameSpace.visibility = View.VISIBLE
                animateTyping(earthNameSpace, earthName)
                Handler().postDelayed({
                    earthDialogSpace.visibility = View.VISIBLE
                    animateTyping(earthDialogSpace, earthDialog)
                }, 1000)

                Handler().postDelayed({
                    rocketShip.visibility = View.VISIBLE
                    animation.animateView(rocketShip, Animation.AnimationType.SLIDE_IN_RIGHT_TWO)
                }, 20000)
            }
        }

        firstAnim()

        rocketShip.setOnClickListener {
            animation.animateView(earth, Animation.AnimationType.FADE_OUT) {
                earth.visibility = View.INVISIBLE

            }

            animation.animateView(earthNameSpace, Animation.AnimationType.FADE_OUT){
                earthNameSpace.visibility = View.INVISIBLE
            }

            animation.animateView(earthDialogSpace, Animation.AnimationType.FADE_OUT) {
                earthDialogSpace.visibility = View.INVISIBLE
                animation.animateView(rocketShip, Animation.AnimationType.SLIDE_IN_RIGHT ) {
                    rocketShip.visibility = View.INVISIBLE

                    val process = Intent(this, EarthQuiz :: class.java)
                    startActivity(process)
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out)

                }
            }

            Handler().postDelayed({
                earth.visibility = View.VISIBLE
                earthNameSpace.visibility = View.VISIBLE
                earthDialogSpace.visibility = View.VISIBLE
                rocketShip.visibility = View.VISIBLE
            }, 5000)

        }
    }
}