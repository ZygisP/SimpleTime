package com.example.simpletime

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ListResult
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_donation.*
import kotlinx.android.synthetic.main.activity_feed.*
import kotlin.random.Random

class ActivityFeed : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)
        storage = FirebaseStorage.getInstance()
        storageReference = storage.reference
        fillFeed()

//        loadImagePlaceholder();

        imageButton13.setOnClickListener{
            fillFeed()
        }
    }
     private fun fillFeed(){
        val listRefVids = FirebaseStorage.getInstance().getReference("Videos/")
        var db = FirebaseFirestore.getInstance()
        com.example.simpletime.db.collection("videos")
        var rand1 = 0
        var rand2 = 0
        var rand3 = 0
        var i = 0
        var j = 0
        listRefVids.listAll()
            .addOnSuccessListener(OnSuccessListener<ListResult> { listResult ->
                Log.d(TAG, listRefVids.getName())
                for (item in listResult.items){
                    i=i+1
                    rand1 = (1..i).random()
                    rand2 = (1..i).random()
                    rand3 = (1..i).random()
                    if(rand1 == rand2 || rand2 == rand3 || rand1 == rand3) {
                        rand1 = (1..i).random()
                        rand2 = (1..i).random()
                        rand3 = (1..i).random()
                    }
                    if(rand1 == rand2 || rand2 == rand3 || rand1 == rand3) {
                        rand1 = (1..i).random()
                        rand2 = (1..i).random()
                        rand3 = (1..i).random()
                    }
                    if(rand1 == rand2 || rand2 == rand3 || rand1 == rand3) {
                        rand1 = (1..i).random()
                        rand2 = (1..i).random()
                        rand3 = (1..i).random()
                    }
                }
                Log.d(TAG, "chosen videos:" + rand1 + " " + rand2 + " " + rand3)
                for (item in listResult.items) {
                    j = j +1
                    var vidname = item.name.dropLast(4)
                    Log.d(TAG, "Videos here:" + vidname)
                    Log.d(TAG, "Checking " + item.name)
                    if (j == rand1){
                        val docRef = db.collection("videos").document(vidname)
                        docRef.get()
                            .addOnSuccessListener { document ->
                                if (document != null) {
                                    Log.d(TAG, "Pulled video for 1:" + vidname)
                                    textView5.text = document.getString("title")
                                    val uploaderPic = document.getString("uploaderID")
                                    val profileImage = findViewById<ImageView>(R.id.imageView8)
                                    val profileRef = storageReference.child(
                                        "users/" + uploaderPic + "/profile.jpg"
                                    )
                                    profileRef.downloadUrl.addOnSuccessListener { uri ->
                                        Picasso.get().load(uri).into(profileImage)
                                    }
                                }
                            }
                            .addOnFailureListener{
                                Log.d(TAG, "nosuchdoc for 1")
                            }
                    }
                    else if (j == rand2){
                        val docRef = db.collection("videos").document(vidname)
                        docRef.get()
                            .addOnSuccessListener { document ->
                                if (document != null) {
                                    Log.d(TAG, "Pulled video for 2:" + vidname)
                                    textView10.text = document.getString("title")
                                    val uploaderPic = document.getString("uploaderID")
                                    val profileImage = findViewById<ImageView>(R.id.imageView19)
                                    val profileRef = storageReference.child(
                                        "users/" + uploaderPic + "/profile.jpg"
                                    )
                                    profileRef.downloadUrl.addOnSuccessListener { uri ->
                                        Picasso.get().load(uri).into(profileImage)
                                    }
                                }
                            }
                            .addOnFailureListener{
                                Log.d(TAG, "nosuchdoc for 1")
                            }
                    }
                    else if (j == rand3){
                        val docRef = db.collection("videos").document(vidname)
                        docRef.get()
                            .addOnSuccessListener { document ->
                                if (document != null) {
                                    Log.d(TAG, "Pulled video for 3:" + vidname)
                                    textView14.text = document.getString("title")
                                    val uploaderPic = document.getString("uploaderID")
                                    val profileImage = findViewById<ImageView>(R.id.imageView23)
                                    val profileRef = storageReference.child(
                                        "users/" + uploaderPic + "/profile.jpg"
                                    )
                                    profileRef.downloadUrl.addOnSuccessListener { uri ->
                                        Picasso.get().load(uri).into(profileImage)
                                    }
                                }
                            }
                            .addOnFailureListener{
                                Log.d(TAG, "nosuchdoc for 1")
                            }
                    }
                } })
    }

    }
