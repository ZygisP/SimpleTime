package com.example.simpletime

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_user_home.*
import kotlinx.android.synthetic.main.activity_login.*

val user = Firebase.auth.currentUser
class ActivityUserHome : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_home)
        val name = user?.displayName
        userHome_userGreeting.text = "Greetings, $name"
        userHome_btnMood.setOnClickListener{
            val intent = Intent(this, ActivityVideoPage::class.java);
            startActivity(intent)
        }
        userHome_btnMoodDailytop.setOnClickListener{
            val intent = Intent(this, ActivityUserProfile::class.java);
            startActivity(intent)
        }
        userHome_btnMoodFollowing.setOnClickListener {
            val intent = Intent(this, VideoPlayerActivity::class.java)
            startActivity(intent)
        }
    }
}