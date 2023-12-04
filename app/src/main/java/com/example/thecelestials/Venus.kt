package com.example.thecelestials

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Venus : AppCompatActivity() {

    private val animation = Animation()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_venus)

        val venus = findViewById<ImageView>(R.id.venus)
        val venusName = getString(R.string.venus)
        val venusNameSpace = findViewById<TextView>(R.id.venusNameSpace)
        val venusDialog = getString(R.string.venus_dialog)
        val venusDialogSpace = findViewById<TextView>(R.id.venusDialogSpace)
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
            venus.visibility = View.VISIBLE
            animation.animateView(venus, Animation.AnimationType.FALL, 1000){
                venusNameSpace.visibility = View.VISIBLE
                animateTyping(venusNameSpace, venusName)
                Handler().postDelayed({
                    venusDialogSpace.visibility = View.VISIBLE
                    animateTyping(venusDialogSpace, venusDialog)
                }, 1000)

            }

            Handler().postDelayed({
                rocketShip.visibility = View.VISIBLE
                animation.animateView(rocketShip, Animation.AnimationType.SLIDE_IN_RIGHT_TWO)
            }, 18000)
        }

        firstAnim()

        rocketShip.setOnClickListener {
            animation.animateView(venus, Animation.AnimationType.FADE_OUT){
                venus.visibility = View.INVISIBLE
            }

            animation.animateView(venusNameSpace, Animation.AnimationType.FADE_OUT){
                venusNameSpace.visibility = View.INVISIBLE
            }

            animation.animateView(venusDialogSpace, Animation.AnimationType.FADE_OUT){
                venusDialogSpace.visibility = View.INVISIBLE
                animation.animateView(rocketShip, Animation.AnimationType.SLIDE_IN_RIGHT) {
                    rocketShip.visibility = View.INVISIBLE
                    val process = Intent(this, VenusQuiz ::class.java)
                    startActivity(process)
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
                }
            }

            Handler().postDelayed({
                venus.visibility = View.VISIBLE
                venusNameSpace.visibility = View.VISIBLE
                venusDialogSpace.visibility = View.VISIBLE
            }, 5000)

        }

    }
}