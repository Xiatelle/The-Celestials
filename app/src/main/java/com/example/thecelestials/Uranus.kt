package com.example.thecelestials

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Uranus : AppCompatActivity() {
    private val animation = Animation()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_uranus)

        val uranus = findViewById<ImageView>(R.id.uranus)
        val uranusName = getString(R.string.uranus)
        val uranusNameSpace = findViewById<TextView>(R.id.uranusNameSpace)
        val uranusDialog = getString(R.string.uranus_dialog)
        val uranusDialogSpace = findViewById<TextView>(R.id.uranusDialogSpace)
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
            uranus.visibility = View.VISIBLE
            animation.animateView(uranus, Animation.AnimationType.FALL, 1000){
                uranusNameSpace.visibility = View.VISIBLE
                animateTyping(uranusNameSpace, uranusName)
                Handler().postDelayed({
                    uranusDialogSpace.visibility = View.VISIBLE
                    animateTyping(uranusDialogSpace, uranusDialog)
                }, 1000)

            }

            Handler().postDelayed({
                rocketShip.visibility = View.VISIBLE
                animation.animateView(rocketShip, Animation.AnimationType.SLIDE_IN_RIGHT_TWO)
            }, 18000)
        }

        firstAnim()

        rocketShip.setOnClickListener {
            animation.animateView(uranus, Animation.AnimationType.FADE_OUT){
                uranus.visibility = View.INVISIBLE
            }

            animation.animateView(uranusNameSpace, Animation.AnimationType.FADE_OUT){
                uranusNameSpace.visibility = View.INVISIBLE
            }

            animation.animateView(uranusDialogSpace, Animation.AnimationType.FADE_OUT){
                uranusDialogSpace.visibility = View.INVISIBLE
                animation.animateView(rocketShip, Animation.AnimationType.SLIDE_IN_RIGHT) {
                    rocketShip.visibility = View.INVISIBLE
                    val process = Intent(this, UranusQuiz ::class.java)
                    startActivity(process)
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
                }
            }

            Handler().postDelayed({
                uranus.visibility = View.VISIBLE
                uranusNameSpace.visibility = View.VISIBLE
                uranusDialogSpace.visibility = View.VISIBLE
            }, 5000)

        }


    }
}