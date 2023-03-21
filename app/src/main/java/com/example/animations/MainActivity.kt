package com.example.animations

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AccelerateInterpolator
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.animations.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityMainBinding

    private lateinit var ball: TextView

    private lateinit var layoutBase: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        val splashScreen = installSplashScreen()
        setContentView(R.layout.activity_main)
        splashScreen.setKeepOnScreenCondition{false}

        //Assign the layout content to variables
        ball = findViewById(R.id.btnBall)
        layoutBase = findViewById(R.id.constraintLayout)

        ball.setOnClickListener{
            moveBall()
        }
    }

    private fun moveBall()
    {
        //val animation= AnimationUtils.loadAnimation(applicationContext, R.anim.fade_in)

        //statusTV.startAnimation(animation)
        val originx = ball.x
        val originy = ball.y

        val endX = layoutBase.right * 0.8f
        val startX = layoutBase.left.toFloat() - endX * 0.5f
        val endY = layoutBase.bottom * 0.44f
        val startY = layoutBase.minHeight.toFloat() - endY

        val downAnimator = ObjectAnimator.ofFloat(ball , "translationY", endY)

        val rightAnimator = ObjectAnimator.ofFloat(ball , "translationX", (endX * 0.5f))
        val rightAnimator2 = ObjectAnimator.ofFloat(ball , "translationX", 0f)

        val upAnimator = ObjectAnimator.ofFloat(ball , "translationY", startY)
        val upAnimator2 = ObjectAnimator.ofFloat(ball , "translationY", startY)

        val leftAnimator = ObjectAnimator.ofFloat(ball , "translationX", startX)

        val centerAnimator = ObjectAnimator.ofFloat(ball , "translationY", 0f)

        /*
        val rightAnimator = ValueAnimator.ofFloat(ball.x, endx)
        rightAnimator.duration = 2000L
        rightAnimator.startDelay = 500
        rightAnimator.interpolator = AccelerateInterpolator()
        rightAnimator.addUpdateListener {
            ball.x = rightAnimator.animatedValue as Float
        }


        val rightAnimator2 = ValueAnimator.ofFloat(ball.x, originx)
        rightAnimator2.duration = 2000L
        rightAnimator2.startDelay = 1000
        rightAnimator2.interpolator = AccelerateInterpolator()
        rightAnimator2.addUpdateListener {
            ball.x = rightAnimator2.animatedValue as Float
        }

        val centerAnimator = ValueAnimator.ofFloat(ball.y, originy)
        centerAnimator.duration = 2000L
        centerAnimator.startDelay = 1000
        centerAnimator.interpolator = AccelerateInterpolator()
        centerAnimator.addUpdateListener {
            ball.y = centerAnimator.animatedValue as Float
        }
        */

        val animatorSet2 = AnimatorSet()
        animatorSet2.playSequentially(
            upAnimator2,
            rightAnimator2,
            centerAnimator
        )

        val animatorSet = AnimatorSet()
        animatorSet.playSequentially(
            upAnimator,
            rightAnimator,
            downAnimator,
            leftAnimator
        )
        animatorSet.doOnEnd { animatorSet2.start() }
        animatorSet.start()
    }
}
