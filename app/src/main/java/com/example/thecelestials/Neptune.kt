package com.example.thecelestials

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Neptune : AppCompatActivity() {
    private val animation = Animation()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_neptune)

        val neptune = findViewById<ImageView>(R.id.neptune)
        val neptuneName = getString(R.string.neptune)
        val neptuneNameSpace = findViewById<TextView>(R.id.neptuneNameSpace)
        val neptuneDialog = getString(R.string.neptune_dialog)
        val neptuneDialogSpace = findViewById<TextView>(R.id.neptuneDialogSpace)
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
            neptune.visibility = View.VISIBLE
            animation.animateView(neptune, Animation.AnimationType.FALL, 1000){
                neptuneNameSpace.visibility = View.VISIBLE
                animateTyping(neptuneNameSpace, neptuneName)
                Handler().postDelayed({
                    neptuneDialogSpace.visibility = View.VISIBLE
                    animateTyping(neptuneDialogSpace, neptuneDialog)
                }, 1000)

            }

            Handler().postDelayed({
                rocketShip.visibility = View.VISIBLE
                animation.animateView(rocketShip, Animation.AnimationType.SLIDE_IN_RIGHT_TWO)
            }, 18000)
        }

        firstAnim()

        rocketShip.setOnClickListener {
            animation.animateView(neptune, Animation.AnimationType.FADE_OUT){
                neptune.visibility = View.INVISIBLE
            }

            animation.animateView(neptuneNameSpace, Animation.AnimationType.FADE_OUT){
                neptuneNameSpace.visibility = View.INVISIBLE
            }

            animation.animateView(neptuneDialogSpace, Animation.AnimationType.FADE_OUT){
                neptuneDialogSpace.visibility = View.INVISIBLE
                animation.animateView(rocketShip, Animation.AnimationType.SLIDE_IN_RIGHT) {
                    rocketShip.visibility = View.INVISIBLE
                    val process = Intent(this, NeptuneQuiz ::class.java)
                    startActivity(process)
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
                }
            }

            Handler().postDelayed({
                neptune.visibility = View.VISIBLE
                neptuneNameSpace.visibility = View.VISIBLE
                neptuneDialogSpace.visibility = View.VISIBLE
            }, 5000)

        }


    }
}