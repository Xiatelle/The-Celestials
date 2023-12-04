package com.example.thecelestials

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Mercury : AppCompatActivity() {

    private var animation = Animation()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mercury)

        val mercury = findViewById<ImageView>(R.id.mercury)
        val mercuryName = getString(R.string.mercury_name)
        val mercuryNameSpace = findViewById<TextView>(R.id.mercuryNameSpace)

        val mercuryDialog = getString(R.string.mercury_dialog)
        val mercuryDialogSpace = findViewById<TextView>(R.id.mercuryDialogSpace)

        val rocketShip = findViewById<ImageButton>(R.id.rocketShip)

        animation.animateView(mercury, Animation.AnimationType.FALL)


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

        Handler().postDelayed({
            mercuryNameSpace.visibility = View.VISIBLE
            animateTyping(mercuryNameSpace, mercuryName)
        },2000)

        Handler().postDelayed({
            mercuryDialogSpace.visibility = View.VISIBLE
            animateTyping(mercuryDialogSpace, mercuryDialog)
        }, 3000)

        Handler().postDelayed({
            rocketShip.visibility = View.VISIBLE
            animation.animateView(rocketShip, Animation.AnimationType.SLIDE_IN_RIGHT_TWO)
        }, 24000)


        rocketShip.setOnClickListener {
            animation.animateView(mercury, Animation.AnimationType.FADE_OUT){
                mercury.visibility = View.INVISIBLE
                Handler().postDelayed({
                    mercury.visibility = View.VISIBLE
                }, 5000)
            }

            animation.animateView(mercuryNameSpace, Animation.AnimationType.FADE_OUT) {
                mercuryNameSpace.visibility = View.INVISIBLE
                Handler().postDelayed({
                    mercuryNameSpace.visibility = View.VISIBLE
                }, 5000)
            }

            animation.animateView(mercuryDialogSpace, Animation.AnimationType.FADE_OUT) {
                mercuryDialogSpace.visibility = View.INVISIBLE
                Handler().postDelayed({
                    mercuryDialogSpace.visibility = View.VISIBLE
                }, 5000)
            }

            Handler().postDelayed({
                animation.animateView(rocketShip, Animation.AnimationType.SLIDE_IN_RIGHT) {
                    rocketShip.visibility = View.INVISIBLE
                    Handler().postDelayed({
                        val process = Intent(this, MercuryQuiz :: class.java)
                        startActivity(process)
                        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
                    }, 1000)

                    Handler().postDelayed({
                        rocketShip.visibility = View.VISIBLE
                    }, 2000)
                }
            }, 1000)


        }
    }
}