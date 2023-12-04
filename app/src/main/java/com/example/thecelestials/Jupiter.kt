package com.example.thecelestials

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Jupiter : AppCompatActivity() {
    private val animation = Animation()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jupiter)

        val jupiter = findViewById<ImageView>(R.id.jupiter)
        val jupiterName = getString(R.string.jupiter)
        val jupiterNameSpace = findViewById<TextView>(R.id.jupiterNameSpace)
        val jupiterDialog = getString(R.string.jupiter_dialog)
        val jupiterDialogSpace = findViewById<TextView>(R.id.jupiterDialogSpace)
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
            jupiter.visibility = View.VISIBLE
            animation.animateView(jupiter, Animation.AnimationType.FALL, 1000){
                jupiterNameSpace.visibility = View.VISIBLE
                animateTyping(jupiterNameSpace, jupiterName)
                Handler().postDelayed({
                    jupiterDialogSpace.visibility = View.VISIBLE
                    animateTyping(jupiterDialogSpace, jupiterDialog)
                }, 1000)

            }

            Handler().postDelayed({
                rocketShip.visibility = View.VISIBLE
                animation.animateView(rocketShip, Animation.AnimationType.SLIDE_IN_RIGHT_TWO)
            }, 18000)
        }

        firstAnim()

        rocketShip.setOnClickListener {
            animation.animateView(jupiter, Animation.AnimationType.FADE_OUT){
                jupiter.visibility = View.INVISIBLE
            }

            animation.animateView(jupiterNameSpace, Animation.AnimationType.FADE_OUT){
                jupiterNameSpace.visibility = View.INVISIBLE
            }

            animation.animateView(jupiterDialogSpace, Animation.AnimationType.FADE_OUT){
                jupiterDialogSpace.visibility = View.INVISIBLE
                animation.animateView(rocketShip, Animation.AnimationType.SLIDE_IN_RIGHT) {
                    rocketShip.visibility = View.INVISIBLE
                    val process = Intent(this, JupiterQuiz ::class.java)
                    startActivity(process)
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
                }
            }

            Handler().postDelayed({
                jupiter.visibility = View.VISIBLE
                jupiterNameSpace.visibility = View.VISIBLE
                jupiterDialogSpace.visibility = View.VISIBLE
            }, 5000)

        }


    }
}