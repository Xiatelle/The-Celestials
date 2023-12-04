package com.example.thecelestials

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Saturn : AppCompatActivity() {
    private val animation = Animation()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saturn)

        val saturn = findViewById<ImageView>(R.id.saturn)
        val saturnName = getString(R.string.saturn)
        val saturnNameSpace = findViewById<TextView>(R.id.saturnNameSpace)
        val saturnDialog = getString(R.string.saturn_dialog)
        val saturnDialogSpace = findViewById<TextView>(R.id.saturnDialogSpace)
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
            saturn.visibility = View.VISIBLE
            animation.animateView(saturn, Animation.AnimationType.FALL, 1000){
                saturnNameSpace.visibility = View.VISIBLE
                animateTyping(saturnNameSpace, saturnName)
                Handler().postDelayed({
                    saturnDialogSpace.visibility = View.VISIBLE
                    animateTyping(saturnDialogSpace, saturnDialog)
                }, 1000)

            }

            Handler().postDelayed({
                rocketShip.visibility = View.VISIBLE
                animation.animateView(rocketShip, Animation.AnimationType.SLIDE_IN_RIGHT_TWO)
            }, 18000)
        }

        firstAnim()

        rocketShip.setOnClickListener {
            animation.animateView(saturn, Animation.AnimationType.FADE_OUT){
                saturn.visibility = View.INVISIBLE
            }

            animation.animateView(saturnNameSpace, Animation.AnimationType.FADE_OUT){
                saturnNameSpace.visibility = View.INVISIBLE
            }

            animation.animateView(saturnDialogSpace, Animation.AnimationType.FADE_OUT){
                saturnDialogSpace.visibility = View.INVISIBLE
                animation.animateView(rocketShip, Animation.AnimationType.SLIDE_IN_RIGHT) {
                    rocketShip.visibility = View.INVISIBLE
                    val process = Intent(this, SaturnQuiz ::class.java)
                    startActivity(process)
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
                }
            }

            Handler().postDelayed({
                saturn.visibility = View.VISIBLE
                saturnNameSpace.visibility = View.VISIBLE
                saturnDialogSpace.visibility = View.VISIBLE
            }, 5000)

        }


    }
}