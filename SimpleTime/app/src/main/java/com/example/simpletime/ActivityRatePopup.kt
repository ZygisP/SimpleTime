package com.example.simpletime
import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.ColorUtils
import kotlinx.android.synthetic.main.activity_rate_popup.*

class ActivityRatePopup : AppCompatActivity() {
//    var popupTitle = ""
//    var popupText = ""
//    var popupButton = ""
//    var darkStatusBar = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(0,0)
        setContentView(R.layout.activity_rate_popup)

//        val bundle = intent.extras
//        popupTitle = bundle?.getString("popuptitle", "Title") ?: ""
//        popupText = bundle?.getString("popuptext", "Text") ?: ""
//        popupButton = bundle?.getString("popupbtn", "Button") ?: ""
//        darkStatusBar = bundle?.getBoolean("darkstatusbar", false) ?: false
//
//        popup_window_title.text = popupTitle
//        popup_window_text.text = popupText
//        popup_window_button.text = popupButton
        val alpha = 100 //between 0-255
        val alphaColor = ColorUtils.setAlphaComponent(Color.parseColor("#000000"), alpha)
        val colorAnimation = ValueAnimator.ofObject(ArgbEvaluator(), Color.TRANSPARENT, alphaColor)
        colorAnimation.duration = 500 // milliseconds
        colorAnimation.addUpdateListener { animator ->
            popup_window_background.setBackgroundColor(animator.animatedValue as Int)
        }

    }

}
