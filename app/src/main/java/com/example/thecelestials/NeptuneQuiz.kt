package com.example.thecelestials

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class NeptuneQuiz : AppCompatActivity() {
    private var animation = Animation()

    private lateinit var optionOne: TextView
    private lateinit var optionTwo: TextView
    private lateinit var neptuneQuizSpace: TextView
    private lateinit var quizRight: ImageView
    private lateinit var quizWrong: ImageView
    private lateinit var choiceAImage : ImageView
    private lateinit var choiceBImage : ImageView

    private val correct = "BLUE GIANT"
    private val incorrect = "ICE GIANT"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_neptune_quiz)

        choiceAImage = findViewById(R.id.choiceAImage)
        choiceBImage = findViewById(R.id.choiceBImage)
        neptuneQuizSpace = findViewById(R.id.neptuneQuizSpace)
        optionOne = findViewById(R.id.optionOne)
        optionTwo = findViewById(R.id.optionTwo)
        quizRight = findViewById(R.id.quizRight)
        quizWrong = findViewById(R.id.quizWrong)
        val neptuneQuiz = getString(R.string.neptune_quiz)

        randomOptions()

        findViewById<ImageView>(R.id.choiceAImage).setOnClickListener {
            onSelect(optionOne.text.toString())
        }

        findViewById<ImageView>(R.id.choiceBImage).setOnClickListener {
            onSelect(optionTwo.text.toString())
        }

        fun animateTyping(textView: TextView, text: String, delay: Long = 100) {
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

            Handler().postDelayed({
                neptuneQuizSpace.visibility = View.VISIBLE
                animateTyping(neptuneQuizSpace, neptuneQuiz)
            }, 1000)

            Handler().postDelayed({
                optionOne.visibility = View.VISIBLE
                choiceAImage.visibility = View.VISIBLE
                animation.animateView(choiceAImage, Animation.AnimationType.FADE_IN)
                animation.animateView(optionOne, Animation.AnimationType.FADE_IN)
            }, 7000)

            Handler().postDelayed({
                optionTwo.visibility = View.VISIBLE
                choiceBImage.visibility = View.VISIBLE
                animation.animateView(choiceBImage, Animation.AnimationType.FADE_IN)
                animation.animateView(optionTwo, Animation.AnimationType.FADE_IN)
            }, 9000)

        }

        firstAnim()
    }

    private fun randomOptions() {
        val random = java.util.Random()
        if (random.nextBoolean()) {
            setButtonOptions(correct, incorrect)
        } else {
            setButtonOptions(incorrect, correct)
        }
    }

    private fun setButtonOptions(option1: String, option2: String) {
        optionOne.text = option1
        optionTwo.text = option2
    }


    private fun onSelect(selectedAnswer: String) {
        val correctAnswerWithoutNewline = correct.replace("\n", "").trim().toUpperCase()
        val selectedAnswerWithoutNewline = selectedAnswer.replace("\n", "").trim().toUpperCase()

        if (selectedAnswerWithoutNewline == correctAnswerWithoutNewline) {
            animateViewsOnCorrectAnswer()
        } else {
            animateViewsOnIncorrectAnswer()
        }
    }


    private fun animateViewsOnCorrectAnswer() {
        animateViewsOut()
        quizRight.visibility = View.VISIBLE
        animation.animateView(quizRight, Animation.AnimationType.FADE_IN)

        Handler().postDelayed({
            val process = Intent(this, Neptune:: class.java)
            startActivity(process)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }, 2000)
    }

    private fun animateViewsOnIncorrectAnswer() {
        animateViewsOut()
        quizWrong.visibility = View.VISIBLE
        animation.animateView(quizWrong, Animation.AnimationType.FADE_IN)

        Handler().postDelayed({
            val process = Intent(this, Neptune :: class.java)
            startActivity(process)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }, 2000)
    }

    private fun animateViewsOut() {
        animation.animateView(findViewById(R.id.choiceAImage), Animation.AnimationType.FADE_OUT) {
            findViewById<ImageView>(R.id.choiceAImage).visibility = View.INVISIBLE
        }

        animation.animateView(findViewById(R.id.choiceBImage), Animation.AnimationType.FADE_OUT) {
            findViewById<ImageView>(R.id.choiceBImage).visibility = View.INVISIBLE
        }

        animation.animateView(neptuneQuizSpace, Animation.AnimationType.FADE_OUT) {
            neptuneQuizSpace.visibility = View.INVISIBLE
        }

        optionOne.visibility = View.INVISIBLE
        optionTwo.visibility = View.INVISIBLE
    }
}