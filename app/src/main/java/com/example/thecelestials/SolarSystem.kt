package com.example.thecelestials

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class SolarSystem : AppCompatActivity() {

    private val animation = Animation()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_solar_system)

        val mercuryRotation = AnimationUtils.loadAnimation(this, R.anim.mercury_rotate)
        val venusRotation = AnimationUtils.loadAnimation(this, R.anim.venus_rotate)
        val earthRotation = AnimationUtils.loadAnimation(this, R.anim.earth_rotate)
        val marsRotation = AnimationUtils.loadAnimation(this, R.anim.mars_rotate)
        val jupiterRotation = AnimationUtils.loadAnimation(this, R.anim.jupiter_rotate)
        val saturnRotation = AnimationUtils.loadAnimation(this, R.anim.saturn_rotate)
        val uranusRotation = AnimationUtils.loadAnimation(this, R.anim.uranus_rotate)
        val neptuneRotation = AnimationUtils.loadAnimation(this, R.anim.neptune_rotate)


        val mercury = findViewById<ImageView>(R.id.slantMercury)
        val venus = findViewById<ImageView>(R.id.slantVenus)
        val earth = findViewById<ImageView>(R.id.slantEarth)
        val mars = findViewById<ImageView>(R.id.slantMars)
        val jupiter = findViewById<ImageView>(R.id.slantJupiter)
        val saturn = findViewById<ImageView>(R.id.slantSaturn)
        val uranus = findViewById<ImageView>(R.id.slantUranus)
        val neptune = findViewById<ImageView>(R.id.slantNeptune)
        val sun = findViewById<ImageView>(R.id.slantSun)

        val mercuryName = findViewById<ImageView>(R.id.nameMercury)
        val venusName = findViewById<ImageView>(R.id.nameVenus)
        val earthName = findViewById<ImageView>(R.id.nameEarth)
        val marsName = findViewById<ImageView>(R.id.nameMars)
        val jupiterName = findViewById<ImageView>(R.id.nameJupiter)
        val saturnName = findViewById<ImageView>(R.id.nameSaturn)
        val uranusName = findViewById<ImageView>(R.id.nameUranus)
        val neptuneName = findViewById<ImageView>(R.id.nameNeptune)
        val sunName = findViewById<ImageView>(R.id.nameSun)

        val rocketShip = findViewById<ImageButton>(R.id.rocketShip)

        val solar = findViewById<ImageView>(R.id.solar)
        val system = findViewById<ImageView>(R.id.system)



        fun firstAnim() {
            animation.animateView(mercury, Animation.AnimationType.FADE_IN) {
                mercuryName.visibility = View.VISIBLE
                animation.animateView(mercuryName, Animation.AnimationType.FADE_IN, 1000) {
                    mercury.startAnimation(mercuryRotation)
                }
            }

            animation.animateView(venus, Animation.AnimationType.FADE_IN, 3000) {
                venusName.visibility = View.VISIBLE
                animation.animateView(venusName, Animation.AnimationType.FADE_IN, 1000) {
                    venus.startAnimation(venusRotation)
                }
            }

            animation.animateView(earth, Animation.AnimationType.FADE_IN, 6000) {
                earthName.visibility = View.VISIBLE
                animation.animateView(earthName, Animation.AnimationType.FADE_IN, 1000){
                    earth.startAnimation(earthRotation)
                }

            }
            animation.animateView(mars, Animation.AnimationType.FADE_IN, 9000) {
                marsName.visibility = View.VISIBLE
                animation.animateView(marsName, Animation.AnimationType.FADE_IN, 1000) {
                    mars.startAnimation(marsRotation)
                }

            }
            animation.animateView(jupiter, Animation.AnimationType.FADE_IN, 12000){
                jupiterName.visibility = View.VISIBLE
                animation.animateView(jupiterName, Animation.AnimationType.FADE_IN, 1000){
                   jupiter.startAnimation(jupiterRotation)
                }

            }
            animation.animateView(saturn, Animation.AnimationType.FADE_IN, 15000) {
                saturnName.visibility = View.VISIBLE
                animation.animateView(saturnName, Animation.AnimationType.FADE_IN, 1000){
                   saturn.startAnimation(saturnRotation)
                }

            }

            animation.animateView(uranus, Animation.AnimationType.FADE_IN, 18000){
                uranusName.visibility = View.VISIBLE
                animation.animateView(uranusName, Animation.AnimationType.FADE_IN, 1000){
                   uranus.startAnimation(uranusRotation)
                }

            }

            animation.animateView(neptune, Animation.AnimationType.FADE_IN, 21000){
                neptuneName.visibility = View.VISIBLE
                animation.animateView(neptuneName, Animation.AnimationType.FADE_IN, 1000){
                   neptune.startAnimation(neptuneRotation)
                }

            }

            animation.animateView(sun, Animation.AnimationType.FADE_IN, 24000){
                sunName.visibility = View.VISIBLE
                animation.animateView(sunName, Animation.AnimationType.FADE_IN, 1000){
                    solar.visibility = View.VISIBLE
                    animation.animateView(solar, Animation.AnimationType.FADE_IN, 1000) {
                        system.visibility = View.VISIBLE
                        animation.animateView(system, Animation.AnimationType.FADE_IN, 1000) {
                            rocketShip.visibility = View.VISIBLE
                            animation.animateView(rocketShip, Animation.AnimationType.SLIDE_IN_RIGHT_TWO, 1000)
                        }
                    }
                }

            }



        }

        firstAnim()

        fun secondAnim() {
            animation.animateView(mercuryName, Animation.AnimationType.FADE_OUT) {
                mercuryName.visibility = View.INVISIBLE
                animation.animateView(mercury, Animation.AnimationType.SLIDE_UP) {
                    animation.animateView(mercury, Animation.AnimationType.STOP)
                    animation.animateView(rocketShip, Animation.AnimationType.SLIDE_IN_RIGHT, 1000)
                    Handler().postDelayed({
                        val process = Intent(this, Mercury :: class.java)
                        startActivity(process)
                        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
                    }, 2000)


                }
            }
        }

        rocketShip.setOnClickListener {
            secondAnim()
        }

    }

}