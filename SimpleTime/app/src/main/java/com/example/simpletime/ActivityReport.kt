package com.example.simpletime

import android.content.Intent
import android.os.Bundle
import android.widget.CheckBox
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_report.*
import kotlinx.android.synthetic.main.activity_report.view.*

class ActivityReport : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_report)
        MenuButtonReport.setOnClickListener {
            val intent = Intent(this, ActivityVideoPage::class.java);
            startActivity(intent)
        }
        submitReportButton.setOnClickListener{
            var db = FirebaseFirestore.getInstance()
            db.collection("videos")
            val docRef = db.collection("videos").document("eDKfaQu2mhQt40MR06uTDmtgSBm2")
            docRef.get()
            if(!checkBox.isChecked() or !checkBox2.isChecked() or !checkBox3.isChecked() or !checkBox4.isChecked() or !checkBox5.isChecked() or !checkBox6.isChecked()){
                Toast.makeText(
                    applicationContext, "Select report reason",
                    Toast.LENGTH_LONG
                ).show()
            }

        }
    }
}