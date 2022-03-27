package com.example.simpletime

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Button
import android.widget.PopupMenu
import android.widget.Toast
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_save_video.*
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
            PopUp1.setImageResource(R.drawable.star)
            PopUp2.setImageResource(R.drawable.star2)
            PopUp3.setImageResource(R.drawable.star2)
            PopUp4.setImageResource(R.drawable.star2)
            PopUp5.setImageResource(R.drawable.star2)
        }
        PopUp2.setOnClickListener {
            PopUp1.setImageResource(R.drawable.star)
            PopUp2.setImageResource(R.drawable.star)
            PopUp3.setImageResource(R.drawable.star2)
            PopUp4.setImageResource(R.drawable.star2)
            PopUp5.setImageResource(R.drawable.star2)
        }
        PopUp3.setOnClickListener {
            PopUp1.setImageResource(R.drawable.star)
            PopUp2.setImageResource(R.drawable.star)
            PopUp3.setImageResource(R.drawable.star)
            PopUp4.setImageResource(R.drawable.star2)
            PopUp5.setImageResource(R.drawable.star2)
        }
        PopUp4.setOnClickListener {
            PopUp1.setImageResource(R.drawable.star)
            PopUp2.setImageResource(R.drawable.star)
            PopUp3.setImageResource(R.drawable.star)
            PopUp4.setImageResource(R.drawable.star)
            PopUp5.setImageResource(R.drawable.star2)
        }
        PopUp5.setOnClickListener {
            PopUp1.setImageResource(R.drawable.star)
            PopUp2.setImageResource(R.drawable.star)
            PopUp3.setImageResource(R.drawable.star)
            PopUp4.setImageResource(R.drawable.star)
            PopUp5.setImageResource(R.drawable.star)
        }
        imageButton18.setOnClickListener{
            val intent = Intent(this, ActivityDonation::class.java);
            startActivity(intent)
        }
        imageButton19.setOnClickListener{
            var popup = PopupMenu(this, imageButton19)
            popup.inflate(R.menu.savepopup)
            popup.setOnMenuItemClickListener {
                Toast.makeText(this, "Item : " + it.title, Toast.LENGTH_SHORT).show()
                true
            }
            popup.show()
    }

    }
}