package com.example.simpletime

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_video_page.*
import java.util.concurrent.TimeUnit


var db = FirebaseFirestore.getInstance()
var storage = FirebaseStorage.getInstance()
var storageReference = storage.getReference()
class ActivityVideoPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db.collection("videos")
        val docRef = db.collection("videos").document("eDKfaQu2mhQt40MR06uTDmtgSBm2")
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    Log.d(TAG, "DocumentSnapshot data: ${document.data}")
                    docRef.update("views", document.getLong("views")?.plus(1))
                    videopage_title.setText(document.getString("title"))
                    videoDesc.setText(document.getString("desc"))
                    viewsVideo.setText(document.get("views").toString())
                    val rating = document.getDouble("rating")
                    val timesRated = document.getDouble("timesRated")
                    val calcRating = timesRated?.let { rating?.div(it) }
                    ratingNumb.setText(calcRating.toString())
                    uploader.setText(document.getString("uploader"))
                    db.collection("users").whereEqualTo("username", "${document.getString("uploader")}")
                    val uploaderPic = document.id
                    val profileImage = findViewById<ImageView>(R.id.uploaderPicture)
                    val profileRef = storageReference.child(
                        "users/" + uploaderPic + "/profile.jpg"
                    )
                    profileRef.downloadUrl.addOnSuccessListener { uri ->
                        Picasso.get().load(uri).into(profileImage)
                    }
                } else {
                    Log.d(TAG, "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "get failed with ", exception)
            }
        setContentView(R.layout.activity_video_page)
        ReportButton.setOnClickListener {
            val intent = Intent(this, ActivityReport::class.java);
            startActivity(intent)
        }
        imageButton11.setOnClickListener {
            val intent = Intent(this, ActivityComments::class.java);
            startActivity(intent)
        }
        PopUp1.setOnClickListener {
            PopUp1.setImageResource(R.drawable.star)
            PopUp2.setImageResource(R.drawable.star2)
            PopUp3.setImageResource(R.drawable.star2)
            PopUp4.setImageResource(R.drawable.star2)
            PopUp5.setImageResource(R.drawable.star2)
            val docRef = db.collection("videos").document("eDKfaQu2mhQt40MR06uTDmtgSBm2")
            docRef.get()
                .addOnSuccessListener { document ->
                    if (document != null) {
                        val currentRating = document.getDouble("rating")
                        docRef.update("rating", document.getDouble("rating")?.plus(1))
                        docRef.update("timesRated", document.getDouble("timesRated")?.plus(1))
                    }
                    }
            PopUp1.setImageDrawable(null)
            PopUp2.setImageDrawable(null)
            PopUp3.setImageDrawable(null)
            PopUp4.setImageDrawable(null)
            PopUp5.setImageDrawable(null)
            ReportButton.x = 600F
            PopUp3.x = 0F
            PopUp2.x = 0F
        }
        PopUp2.setOnClickListener {
            PopUp1.setImageResource(R.drawable.star)
            PopUp2.setImageResource(R.drawable.star)
            PopUp3.setImageResource(R.drawable.star2)
            PopUp4.setImageResource(R.drawable.star2)
            PopUp5.setImageResource(R.drawable.star2)
            val docRef = db.collection("videos").document("eDKfaQu2mhQt40MR06uTDmtgSBm2")
            docRef.get()
                .addOnSuccessListener { document ->
                    if (document != null) {
                        val currentRating = document.getDouble("rating")
                        docRef.update("rating", document.getDouble("rating")?.plus(2))
                        docRef.update("timesRated", document.getDouble("timesRated")?.plus(1))
                    }
                }
            PopUp1.setImageDrawable(null)
            PopUp2.setImageDrawable(null)
            PopUp3.setImageDrawable(null)
            PopUp4.setImageDrawable(null)
            PopUp5.setImageDrawable(null)
            ReportButton.x = 600F
            PopUp3.x = 0F
            PopUp2.x = 0F
        }
        PopUp3.setOnClickListener {
            PopUp1.setImageResource(R.drawable.star)
            PopUp2.setImageResource(R.drawable.star)
            PopUp3.setImageResource(R.drawable.star)
            PopUp4.setImageResource(R.drawable.star2)
            PopUp5.setImageResource(R.drawable.star2)
            val docRef = db.collection("videos").document("eDKfaQu2mhQt40MR06uTDmtgSBm2")
            docRef.get()
                .addOnSuccessListener { document ->
                    if (document != null) {
                        val currentRating = document.getDouble("rating")
                        docRef.update("rating", document.getDouble("rating")?.plus(3))
                        docRef.update("timesRated", document.getDouble("timesRated")?.plus(1))
                    }
                }
            PopUp1.setImageDrawable(null)
            PopUp2.setImageDrawable(null)
            PopUp3.setImageDrawable(null)
            PopUp4.setImageDrawable(null)
            PopUp5.setImageDrawable(null)
            ReportButton.x = 600F
            PopUp3.x = 0F
            PopUp2.x = 0F
        }
        PopUp4.setOnClickListener {
            PopUp1.setImageResource(R.drawable.star)
            PopUp2.setImageResource(R.drawable.star)
            PopUp3.setImageResource(R.drawable.star)
            PopUp4.setImageResource(R.drawable.star)
            PopUp5.setImageResource(R.drawable.star2)
            val docRef = db.collection("videos").document("eDKfaQu2mhQt40MR06uTDmtgSBm2")
            docRef.get()
                .addOnSuccessListener { document ->
                    if (document != null) {
                        val currentRating = document.getDouble("rating")
                        docRef.update("rating", document.getDouble("rating")?.plus(4))
                        docRef.update("timesRated", document.getDouble("timesRated")?.plus(1))
                    }
                }
            PopUp1.setImageDrawable(null)
            PopUp2.setImageDrawable(null)
            PopUp3.setImageDrawable(null)
            PopUp4.setImageDrawable(null)
            PopUp5.setImageDrawable(null)
            ReportButton.x = 600F
            PopUp3.x = 0F
            PopUp2.x = 0F
        }
        PopUp5.setOnClickListener {
            PopUp1.setImageResource(R.drawable.star)
            PopUp2.setImageResource(R.drawable.star)
            PopUp3.setImageResource(R.drawable.star)
            PopUp4.setImageResource(R.drawable.star)
            PopUp5.setImageResource(R.drawable.star)
            val docRef = db.collection("videos").document("eDKfaQu2mhQt40MR06uTDmtgSBm2")
            docRef.get()
                .addOnSuccessListener { document ->
                    if (document != null) {
                        val currentRating = document.getDouble("rating")
                        docRef.update("rating", document.getDouble("rating")?.plus(5))
                        docRef.update("timesRated", document.getDouble("timesRated")?.plus(1))
                    }
                }
            PopUp1.setImageDrawable(null)
            PopUp2.setImageDrawable(null)
            PopUp3.setImageDrawable(null)
            PopUp4.setImageDrawable(null)
            PopUp5.setImageDrawable(null)
            ReportButton.x = 600F
            PopUp3.x = 0F
            PopUp2.x = 0F
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