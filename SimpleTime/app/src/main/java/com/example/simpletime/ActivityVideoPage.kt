package com.example.simpletime

import android.content.ContentValues.TAG
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.MediaMetadata
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.ui.PlayerView
import com.google.common.collect.ImmutableList
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_video_page.*
import java.util.concurrent.TimeUnit
import android.widget.ProgressBar
import android.widget.TextView
import com.google.firebase.storage.StorageReference
import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.math.round
import kotlin.math.roundToInt


var db = FirebaseFirestore.getInstance()
var storage = FirebaseStorage.getInstance()
var storageReference = storage.getReference()




class ActivityVideoPage : AppCompatActivity(), Player.Listener {
    private lateinit var player2: ExoPlayer
    private lateinit var playerView2: PlayerView
    private lateinit var progressBar2: ProgressBar
    private lateinit var titleTv2: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_page)

        progressBar2 = findViewById(R.id.progressBar2)

        titleTv2 = findViewById(R.id.title2)

        getUrlAsync("1650383898885")

        //setupPlayer()
        //addMP4Files()


        // restore player state on Rotation
        if (savedInstanceState != null) {
            if (savedInstanceState.getInt("mediaItem") != 0) {
                val restoredMediaItem = savedInstanceState.getInt("mediaItem")
                val seekTime = savedInstanceState.getLong("SeekTime")
                player2.seekTo(restoredMediaItem, seekTime)
                player2.play()
            }
        }



        db.collection("videos")
        val docRef = db.collection("videos").document("eDKfaQu2mhQt40MR06uTDmtgSBm2")
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    Log.d(TAG, "DocumentSnapshot data: ${document.data}")
                    docRef.update("views", document.getLong("views")?.plus(1))
                    videopage_title.setText(document.getString("title"))
                    videoPage_description.setText(document.getString("desc"))
                    videoPage_viewCount.setText(document.get("views").toString())
                    val rating = document.getDouble("rating")
                    val timesRated = document.getDouble("timesRated")
                    val calcRating = timesRated?.let { BigDecimal(rating?.div(it)!!).setScale(2, RoundingMode.HALF_EVEN) }
                    videoPage_rating.setText(calcRating.toString())
                    videoPage_uploaderName.setText(document.getString("uploader"))
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

    fun Double.round(decimals: Int): Double {
        var multiplier = 1.0
        repeat(decimals) { multiplier *= 10 }
        return round(this * multiplier) / multiplier
    }

    private fun addMP4Files(getLink: String) {
        //var getLink = getUrlAsync("1650382289065")
        val mediaItem = MediaItem.fromUri(Uri.parse(getLink))
        val newItems: List<MediaItem> = ImmutableList.of(
            mediaItem
        )
        player2.addMediaItems(newItems)
        player2.prepare()

    }

    private fun getRefLink(name: String): String {
        var storageRef = FirebaseStorage.getInstance().getReference()
        var nameRef = storageRef.child("Videos/" + name + ".mp4")
        return nameRef.toString()
    }

    private fun getUrlAsync(name: String): String {
        var tempLink = "link"
        var storageRef = FirebaseStorage.getInstance().getReference()
        var nameRef = storageRef.child("Videos").child("eDKfaQu2mhQt40MR06uTDmtgSBm2/" + name + ".mp4")
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
        player2 = ExoPlayer.Builder(this).build()
        playerView2 = findViewById(R.id.video_view2)
        playerView2.player = player2
        player2.addListener(this)
    }


    override fun onStop() {
        super.onStop()
        player2.release()
    }

    override fun onResume() {
        super.onResume()
        getUrlAsync("1650383898885")
    }

    // handle loading
    override fun onPlaybackStateChanged(state: Int) {
        when (state) {
            Player.STATE_BUFFERING -> {
                progressBar2.visibility = View.VISIBLE

            }
            Player.STATE_READY -> {
                progressBar2.visibility = View.INVISIBLE
            }
            Player.STATE_ENDED -> {

            }
            Player.STATE_IDLE -> {

            }
        }
    }

    //get Title from metadata
    override fun onMediaMetadataChanged(mediaMetadata: MediaMetadata) {

        titleTv2.text = mediaMetadata.title ?: mediaMetadata.displayTitle ?: "no title found"

    }

    // save details if Activity is destroyed
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG, "onSaveInstanceState: " + player2.currentPosition)
        // current play position
        outState.putLong("SeekTime", player2.currentPosition)
        // current mediaItem
        outState.putInt("mediaItem", player2.currentMediaItemIndex)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onSaveInstanceState: " + player2.currentPosition)
    }

    companion object {
        private const val TAG = "VideoPlayerActivity"
    }


}