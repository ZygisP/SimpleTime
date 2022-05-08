package com.example.simpletime

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ListResult
import kotlinx.android.synthetic.main.activity_feed.*
import kotlin.random.Random

class ActivityFeed : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)
        storage = FirebaseStorage.getInstance()
        storageReference = storage.reference
        var db = FirebaseFirestore.getInstance()
        var rand1 = 0
        var rand2 = 0
        var rand3 = 0
        var i = 0
        var j = 0
        db.collection("videos")


//        loadImagePlaceholder();
        val profileRef =
            storageReference.child("videos/")
        val listRef = FirebaseStorage.getInstance().getReference("Videos/")
        val listRefVids = FirebaseStorage.getInstance().getReference("Videos/")

        listRef.listAll()
            .addOnSuccessListener(OnSuccessListener<ListResult> { listResult ->
                Log.d(TAG, listRef.getName())
                for (prefix in listResult.prefixes) {
                    Log.d(TAG, "Folders here: " + prefix.name)
                    val listRefVids = FirebaseStorage.getInstance().getReference("Videos/" + prefix.name + "/")
                    listRefVids.listAll()
                        .addOnSuccessListener(OnSuccessListener<ListResult> { listResult ->
                            Log.d(TAG, listRefVids.getName())
                            for (item in listResult.items) {
                                i = i + 1
                                var vidname = item.name.dropLast(4)
                                Log.d(TAG, "Videos here:" + vidname)
                                rand1 = (0..i).random()
                                rand2 = (0..i).random()
                                rand3 = (0..i).random()
                                Log.d(TAG, "chosen videos:" + rand1 + " " + rand2 + " " + rand3)
                                val chooseVids = FirebaseStorage.getInstance().getReference("Videos/" + prefix.name+"/")
                                chooseVids.listAll()
                                    .addOnSuccessListener { OnSuccessListener<ListResult> { listResult ->
                                        for(item in listResult.items) {
                                            Log.d(TAG, "Checking " + item.name)
                                            j = j + 1
                                            if (j == rand1){
                                                var vidname1 = item.name.dropLast(4)
                                                val docRef = db.collection("videos").document(vidname1)
                                                docRef.get()
                                                    .addOnSuccessListener { document ->
                                                        if (document != null) {
                                                            Log.d(TAG, "Pulled vide:" + vidname1)
                                                            textView5.text = document.getString("title")
                                                        }
                                            }
                                                    .addOnFailureListener{
                                                        Log.d(TAG, "nosuchdoc for 1")
                                                    }
                                        }
                                            else if (j == rand2){
                                                var vidname1 = item.name.dropLast(4)
                                                val docRef = db.collection("videos").document(vidname1)
                                                docRef.get()
                                                    .addOnSuccessListener { document ->
                                                        if (document != null) {
                                                            Log.d(TAG, "Pulled vide:" + vidname1)
                                                            textView10.text = document.getString("title")
                                                        }
                                                    }
                                                    .addOnFailureListener{
                                                        Log.d(TAG, "nosuchdoc for 1")
                                                    }
                                            }
                                            else if (j == rand3){
                                                var vidname1 = item.name.dropLast(4)
                                                val docRef = db.collection("videos").document(vidname1)
                                                docRef.get()
                                                    .addOnSuccessListener { document ->
                                                        if (document != null) {
                                                            Log.d(TAG, "Pulled vide:" + vidname1)
                                                            textView14.text = document.getString("title")
                                                        }
                                                    }
                                                    .addOnFailureListener{
                                                        Log.d(TAG, "nosuchdoc for 1")
                                                    }
                                            }
                                        }
                                    } }
                            }
                        })
                }
                for (item in listResult.items) {
                    Log.d(TAG, "Aint no folders here")
                }
            })
            .addOnFailureListener(OnFailureListener {
                // Uh-oh, an error occurred!
            })
    }
    }
