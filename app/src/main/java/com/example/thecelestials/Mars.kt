package com.example.thecelestials

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Mars : AppCompatActivity() {
    private var animation = Animation()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mars)

        val mars = findViewById<ImageView>(R.id.mars)
        val marsName = getString(R.string.mars)
        val marsNameSpace = findViewById<TextView>(R.id.marsNameSpace)
        val marsDialog = getString(R.string.mars_dialog)
        val marsDialogSpace = findViewById<TextView>(R.id.marsDialogSpace)
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

        fun firstAnim() {
            animation.animateView(mars, Animation.AnimationType.FALL) {
                marsNameSpace.visibility = View.VISIBLE
                animateTyping(marsNameSpace, marsName)
                Handler().postDelayed({
                    marsDialogSpace.visibility = View.VISIBLE
                    animateTyping(marsDialogSpace, marsDialog)
                }, 1000)

                Handler().postDelayed({
                    rocketShip.visibility = View.VISIBLE
                    animation.animateView(rocketShip, Animation.AnimationType.SLIDE_IN_RIGHT_TWO)
                }, 15000)
            }
        }

        firstAnim()

        rocketShip.setOnClickListener {
            animation.animateView(mars, Animation.AnimationType.FADE_OUT) {
                mars.visibility = View.INVISIBLE

            }

            animation.animateView(marsNameSpace, Animation.AnimationType.FADE_OUT) {
                marsNameSpace.visibility = View.INVISIBLE
            }

            animation.animateView(marsDialogSpace, Animation.AnimationType.FADE_OUT) {
                marsDialogSpace.visibility = View.INVISIBLE
                animation.animateView(rocketShip, Animation.AnimationType.SLIDE_IN_RIGHT) {
                    rocketShip.visibility = View.INVISIBLE

                    val process = Intent(this, MarsQuiz::class.java)
                    startActivity(process)
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out)

                }
            }

            Handler().postDelayed({
                mars.visibility = View.VISIBLE
                marsNameSpace.visibility = View.VISIBLE
                marsDialogSpace.visibility = View.VISIBLE
                rocketShip.visibility = View.VISIBLE
            }, 5000)
        }
    }
}
