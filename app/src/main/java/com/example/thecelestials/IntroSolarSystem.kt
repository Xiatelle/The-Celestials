package com.example.thecelestials

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class IntroSolarSystem : AppCompatActivity() {

    private var animation = Animation()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro_solar_system)

        val sun = findViewById<ImageView>(R.id.sun)
        val mercury = findViewById<ImageView>(R.id.mercury)
        val venus = findViewById<ImageView>(R.id.venus)
        val earth = findViewById<ImageView>(R.id.earth)
        val mars = findViewById<ImageView>(R.id.mars)
        val jupiter = findViewById<ImageView>(R.id.jupiter)
        val saturn = findViewById<ImageView>(R.id.saturn)
        val uranus = findViewById<ImageView>(R.id.uranus)
        val neptune = findViewById<ImageView>(R.id.neptune)

        val mercuryName = findViewById<ImageView>(R.id.mercuryName)
        val venusName = findViewById<ImageView>(R.id.venusName)
        val earthName = findViewById<ImageView>(R.id.earthName)
        val marsName = findViewById<ImageView>(R.id.marsName)
        val jupiterName = findViewById<ImageView>(R.id.jupiterName)
        val saturnName = findViewById<ImageView>(R.id.saturnName)
        val uranusName = findViewById<ImageView>(R.id.uranusName)
        val neptuneName = findViewById<ImageView>(R.id.neptuneName)

        val solar =  findViewById<ImageView>(R.id.titleThree)
        val system = findViewById<ImageView>(R.id.titleFour)

        val rocketShip = findViewById<ImageButton>(R.id.rocketShip)

        fun thirdAnim(){
            solar.visibility = View.VISIBLE
            animation.animateView(solar, Animation.AnimationType.FADE_IN){
                system.visibility = View.VISIBLE
                animation.animateView(system, Animation.AnimationType.FADE_IN) {
                    rocketShip.visibility = View.VISIBLE
                    animation.animateView(rocketShip, Animation.AnimationType.SLIDE_IN_RIGHT_TWO)
                }
            }
        }

        fun secondAnim(){
            mercuryName.visibility = View.VISIBLE
            venusName.visibility = View.VISIBLE
            earthName.visibility = View.VISIBLE
            marsName.visibility = View.VISIBLE
            jupiterName.visibility = View.VISIBLE
            saturnName.visibility = View.VISIBLE
            uranusName.visibility = View.VISIBLE
            neptuneName.visibility = View.VISIBLE
            animation.animateView(mercuryName, Animation.AnimationType.FADE_IN, 700)
            animation.animateView(venusName, Animation.AnimationType.FADE_IN, 1000)
            animation.animateView(earthName, Animation.AnimationType.FADE_IN, 1300)
            animation.animateView(marsName, Animation.AnimationType.FADE_IN, 1600)
            animation.animateView(jupiterName, Animation.AnimationType.FADE_IN, 1900)
            animation.animateView(saturnName, Animation.AnimationType.FADE_IN, 2100)
            animation.animateView(uranusName, Animation.AnimationType.FADE_IN, 2400)
            animation.animateView(neptuneName, Animation.AnimationType.FADE_IN, 2700){
                thirdAnim()
            }

        }

        fun firstAnim() {

            animation.animateView(sun, Animation.AnimationType.SLIDE_IN_RIGHT_TWO)
            animation.animateView(mercury, Animation.AnimationType.FADE_IN, 1000)
            animation.animateView(venus, Animation.AnimationType.FADE_IN, 1200)
            animation.animateView(earth, Animation.AnimationType.FADE_IN, 1400)
            animation.animateView(mars, Animation.AnimationType.FADE_IN, 1600)
            animation.animateView(jupiter, Animation.AnimationType.FADE_IN, 1800)
            animation.animateView(saturn, Animation.AnimationType.FADE_IN, 2000)
            animation.animateView(uranus, Animation.AnimationType.FADE_IN, 2200)
            animation.animateView(neptune, Animation.AnimationType.FADE_IN, 2400){
                secondAnim()
            }
        }

        firstAnim()
        

        
        fun fifthAnim(){
            animation.animateView(mercury, Animation.AnimationType.SLIDE_IN_RIGHT, 700) {
                mercury.visibility = View.INVISIBLE
            }
            animation.animateView(venus, Animation.AnimationType.SLIDE_IN_RIGHT, 1000){
                venus.visibility = View.INVISIBLE
            }
            animation.animateView(earth, Animation.AnimationType.SLIDE_IN_RIGHT, 1300){
                earth.visibility = View.INVISIBLE
            }
            animation.animateView(mars, Animation.AnimationType.SLIDE_IN_RIGHT, 1600) {
                mars.visibility = View.INVISIBLE
            }
            animation.animateView(jupiter, Animation.AnimationType.SLIDE_IN_RIGHT, 1900){
                jupiter.visibility = View.INVISIBLE
            }
            animation.animateView(saturn, Animation.AnimationType.SLIDE_IN_RIGHT, 2100){
                saturn.visibility = View.INVISIBLE
            }
            animation.animateView(uranus, Animation.AnimationType.SLIDE_IN_RIGHT, 2400){
                uranus.visibility = View.INVISIBLE
            }
            animation.animateView(neptune, Animation.AnimationType.SLIDE_IN_RIGHT, 2700){
                neptune.visibility = View.INVISIBLE
            }
            animation.animateView(sun, Animation.AnimationType.SLIDE_IN_RIGHT, 3000) {
                sun.visibility = View.INVISIBLE
                animation.animateView(solar, Animation.AnimationType.FADE_OUT, 0) {
                    solar.visibility = View.INVISIBLE
                }
                animation.animateView(system, Animation.AnimationType.FADE_OUT, 100) {
                    system.visibility = View.INVISIBLE
                    animation.animateView(rocketShip, Animation.AnimationType.SLIDE_IN_RIGHT, 1000) {
                        rocketShip.visibility = View.INVISIBLE
                    }
                }

            }

        }

        fun fourthAnim() {
            animation.animateView(mercuryName, Animation.AnimationType.FADE_OUT, 700) {
                mercuryName.visibility = View.INVISIBLE
            }
            animation.animateView(venusName, Animation.AnimationType.FADE_OUT, 1000){
                venusName.visibility = View.INVISIBLE
            }

            animation.animateView(earthName, Animation.AnimationType.FADE_OUT, 1300) {
                earthName.visibility = View.INVISIBLE
            }
            animation.animateView(marsName, Animation.AnimationType.FADE_OUT, 1600){
                marsName.visibility = View.INVISIBLE
            }

            animation.animateView(jupiterName, Animation.AnimationType.FADE_OUT, 1900){
                jupiterName.visibility = View.INVISIBLE
            }
            animation.animateView(saturnName, Animation.AnimationType.FADE_OUT, 2100) {
                saturnName.visibility = View.INVISIBLE
            }
            animation.animateView(uranusName, Animation.AnimationType.FADE_OUT, 2400){
                uranusName.visibility = View.INVISIBLE
            }
            animation.animateView(neptuneName, Animation.AnimationType.FADE_OUT, 2700) {
                neptuneName.visibility = View.INVISIBLE
                fifthAnim()

            }

        }
        rocketShip.setOnClickListener { 
            fourthAnim()
            Handler().postDelayed({
                val process = Intent(this, SolarSystem :: class.java)
                startActivity(process)
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            }, 12000)
        }

    }
}