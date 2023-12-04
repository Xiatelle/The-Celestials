package com.example.thecelestials

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class IntroOne : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro_one)

        Handler().postDelayed({
            val process = Intent(this, IntroThree::class.java)
            startActivity(process)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }, 3000)
    }
}