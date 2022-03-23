package com.example.simpletime

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Button
import android.widget.PopupMenu
import kotlinx.android.synthetic.main.activity_video_page.*

class ActivityVideoPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_page)
        ReportButton.setOnClickListener {
            val intent = Intent(this, ActivityReport::class.java);
            startActivity(intent)
        }
        PopUp1.setOnClickListener {
            val intent = Intent(this, ActivityRatePopup::class.java);
            startActivity(intent)
        }
        PopUp2.setOnClickListener {
            val intent = Intent(this, ActivityRatePopup::class.java);
            startActivity(intent)
        }
        PopUp3.setOnClickListener {
            val intent = Intent(this, ActivityRatePopup::class.java);
            startActivity(intent)
        }
        PopUp4.setOnClickListener {
            val intent = Intent(this, ActivityRatePopup::class.java);
            startActivity(intent)
        }
        PopUp5.setOnClickListener {
            val intent = Intent(this, ActivityRatePopup::class.java);
            startActivity(intent)
        }
        imageButton18.setOnClickListener{
            val intent = Intent(this, ActivityDonation::class.java);
            startActivity(intent)
        }
        imageButton19.setOnClickListener{
            val intent = Intent(this, ActivitySaveVideo::class.java);
            startActivity(intent)
        }
    }
}