package com.example.simpletime

import android.content.ContentValues.TAG
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.MediaMetadata
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.common.collect.ImmutableList
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ListResult
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_donation.*
import kotlinx.android.synthetic.main.activity_feed.*
import kotlinx.android.synthetic.main.activity_video_page.*
import kotlin.random.Random

class ActivityFeed : AppCompatActivity(), Player.Listener {
    private lateinit var player3: ExoPlayer
    private lateinit var playerView3: PlayerView
    private lateinit var progressBar3: ProgressBar
    private lateinit var titleTv3: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)
        storage = FirebaseStorage.getInstance()
        storageReference = storage.reference
//        loadImagePlaceholder();
        progressBar3 = findViewById(R.id.progressBar3)

        titleTv3 = findViewById(R.id.title3)
        fillFeed()

        //setupPlayer()
        //addMP4Files()

        // restore player state on Rotation
        if (savedInstanceState != null) {
            if (savedInstanceState.getInt("mediaItem") != 0) {
                val restoredMediaItem = savedInstanceState.getInt("mediaItem")
                val seekTime = savedInstanceState.getLong("SeekTime")
                player3.seekTo(restoredMediaItem, seekTime)
                player3.play()
            }
        }

        imageButton13.setOnClickListener {
            player3.stop()
            fillFeed()
        }
        reportButton.setOnClickListener {
            val intent = Intent(this, ActivityReport::class.java);
            startActivity(intent)
        }
        imageButton16.setOnClickListener {
            val intent = Intent(this, ActivityComments::class.java);
            startActivity(intent)
        }
    }
    private fun addMP4Files(getLink: String) {
        //var getLink = getUrlAsync("1650382289065")
        val mediaItem = MediaItem.fromUri(Uri.parse(getLink))
        val newItems: List<MediaItem> = ImmutableList.of(
            mediaItem
        )
        player3.addMediaItems(newItems)
        player3.prepare()

    }

    private fun getRefLink(name: String): String {
        var storageRef = FirebaseStorage.getInstance().getReference()
        var nameRef = storageRef.child("Videos/" + name + ".mp4")
        return nameRef.toString()
    }

    private fun getUrlAsync(name: String): String {
        var tempLink = "link"
        var storageRef = FirebaseStorage.getInstance().getReference()
        var nameRef = storageRef.child("Videos").child(name + ".mp4")
        nameRef.downloadUrl.addOnSuccessListener {
            Log.d(TAG, it.toString())
            tempLink = it.toString()
            setupPlayer()
            addMP4Files(tempLink)
        }.addOnFailureListener {
            Log.d(TAG, "file with this name does not exist")
        }

        return tempLink
    }


    private fun setupPlayer() {
        player3 = ExoPlayer.Builder(this).build()
        playerView3 = findViewById(R.id.videoFeed)
        playerView3.player = player3
        playerView3.hideController()
        player3.addListener(this)
        player3.playWhenReady = true
    }


    override fun onStop() {
        super.onStop()
        player3.release()
    }

    override fun onResume() {
        super.onResume()
    }

    // handle loading
    override fun onPlaybackStateChanged(state: Int) {
        when (state) {
            Player.STATE_BUFFERING -> {
                progressBar3.visibility = View.INVISIBLE
            }
            Player.STATE_READY -> {
                progressBar3.visibility = View.INVISIBLE
            }
            Player.STATE_ENDED -> {
                fillFeed()
            }
            Player.STATE_IDLE -> {
                progressBar3.visibility = View.INVISIBLE
            }
        }
    }

    //get Title from metadata
    override fun onMediaMetadataChanged(mediaMetadata: MediaMetadata) {

        titleTv3.text = mediaMetadata.title ?: mediaMetadata.displayTitle ?: "no title found"

    }

    // save details if Activity is destroyed
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG, "onSaveInstanceState: " + player3.currentPosition)
        // current play position
        outState.putLong("SeekTime", player3.currentPosition)
        // current mediaItem
        outState.putInt("mediaItem", player3.currentMediaItemIndex)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onSaveInstanceState: " + player3.currentPosition)
    }

    companion object {
        private const val TAG = "VideoPlayerActivity"
    }
//    private fun sortByViews(vidname: String) {
//            getUrlAsync(vidname)
//            videopage_title2.text = document.getString("title")
//            videopage_description.text = document.getString("desc")
//            val uploaderPic = document.getString("uploaderID")
//            val profileImage =
//                findViewById<ImageView>(R.id.uploaderPicture2)
//            val profileRef = storageReference.child(
//                "users/" + uploaderPic + "/profile.jpg"
//            )
//            profileRef.downloadUrl.addOnSuccessListener { uri ->
//                Picasso.get().load(uri).into(profileImage)
//            }
//    }


    private fun fillFeed() {
        val listRefVids = FirebaseStorage.getInstance().getReference("Videos/")
        var db = FirebaseFirestore.getInstance()
        val sortType = intent.extras
        com.example.simpletime.db.collection("videos")
        var rand1 = 0
        var i = 0
        var j = 0
        if(sortType?.getString("sort") == "mood"){
        listRefVids.listAll()
            .addOnSuccessListener(OnSuccessListener<ListResult> { listResult ->
                Log.d(TAG, listRefVids.getName())
                for (item in listResult.items) {
                    i = i + 1
                    rand1 = (1..i).random()
                }
                Log.d(TAG, "chosen videos:" + rand1)
                for (item in listResult.items) {
                    j = j + 1
                    var vidname = item.name.dropLast(4)
                    Log.d(TAG, "Videos here:" + vidname)
                    Log.d(TAG, "Checking " + item.name)
                    if (j == rand1) {
                        val docRef = db.collection("videos").document(vidname)
                        docRef.get()
                            .addOnSuccessListener { document ->
                                if (document != null) {
                                    Log.d(TAG, "Pulled video for 1:" + vidname)
                                    getUrlAsync(vidname)
                                    videopage_title2.text = document.getString("title")
                                    videopage_description.text = document.getString("desc")
                                    val uploaderPic = document.getString("uploaderID")
                                    val profileImage =
                                        findViewById<ImageView>(R.id.uploaderPicture2)
                                    val profileRef = storageReference.child(
                                        "users/" + uploaderPic + "/profile.jpg"
                                    )
                                    profileRef.downloadUrl.addOnSuccessListener { uri ->
                                        Picasso.get().load(uri).into(profileImage)
                                    }
                                }
                            }
                    }
                }

            })}
//        else if (sortType?.getString("sort") == "dailytop"){
//            val colref = db.collection("videos").orderBy("views")
//            colref.get()
//                .addOnSuccessListener { documents ->
//                    for(document in documents) {
//                        val videoID = document.id
//                        getUrlAsync(videoID)
//                        videopage_title2.text = document.getString("title")
//                        videopage_description.text = document.getString("desc")
//                        val uploaderPic = document.getString("uploaderID")
//                        val profileImage =
//                            findViewById<ImageView>(R.id.uploaderPicture2)
//                        val profileRef = storageReference.child(
//                            "users/" + uploaderPic + "/profile.jpg"
//                        )
//                        profileRef.downloadUrl.addOnSuccessListener { uri ->
//                            Picasso.get().load(uri).into(profileImage)
//                        }
//                    }
//                }
//            var vidname = item.name.dropLast(4)
//            Log.d(TAG, "Videos here:" + vidname)
//            Log.d(TAG, "Checking " + item.name)
//            val docRef = db.collection("videos").document(vidname)
//        }
    }

}