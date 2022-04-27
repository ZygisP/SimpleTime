package com.example.simpletime

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.exoplayer2.util.Log
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_user_home.*

val user = Firebase.auth.currentUser

class ActivityUserHome : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_home)

        val db = FirebaseFirestore.getInstance()
        val docRef = db.collection("users").document(user?.uid.toString())
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    val name = document.getString("username").toString()
                    userHome_userGreeting.text = "Greetings, $name"
                    Log.d(TAG, "DocumentSnapshot data: ${document.data}")
                } else {
                    Log.d(TAG, "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "get failed with ", exception)
            }

        userHome_btnMood.setOnClickListener{
            val intent = Intent(this, ActivityVideoPage::class.java);
            startActivity(intent)
        }
        userHome_btnMoodFollowing.setOnClickListener {
            val intent = Intent(this, VideoPlayerActivity::class.java)
            startActivity(intent)
        }
        userHome_btnOfficial.setOnClickListener {
            val intent = Intent(this, ActivityUserProfile::class.java);
            startActivity(intent)
        }
        userHome_btnMoodDailytop.setOnClickListener{
            val intent = Intent(this, uploadVideo::class.java)
            startActivity(intent)

        }
    }
}

