package com.example.simpletime

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_user_home.*
import kotlinx.android.synthetic.main.fragment_first.*

val user = Firebase.auth.currentUser
class UserHomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_home)
        val name = user?.displayName
        userHome_userGreeting.text = "Greetings, $name"
        userHome_btnMood.setOnClickListener{
            val intent = Intent(this, ActivityVideoPage::class.java);
            startActivity(intent)
        }
    }
}