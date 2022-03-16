package com.example.simpletime

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_registration.*


private lateinit var auth: FirebaseAuth


class ActivityRegistration : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        auth = FirebaseAuth.getInstance()

        signup_btnBack.setOnClickListener {

            val intent = Intent(this, MainActivity::class.java);
            startActivity(intent)

        }
        signup_btnSignup.setOnClickListener {
            register(it)
        }
    }

    fun register(view: View) {
        val email = signup_enterEmail.text.toString()
        val name = signup_enterName.text.toString()
        val password = signup_enterPassword.text.toString();
        if (email.isNotEmpty() && password.isNotEmpty() && name.isNotEmpty())
            {
                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener() { task ->

                    if (task.isSuccessful) {
                        val user = auth.currentUser
                         user?.updateProfile(userProfileChangeRequest { displayName = name })
                        sendEmailVerification()

                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                }.addOnFailureListener { exception ->
                    Toast.makeText(applicationContext, exception.localizedMessage, Toast.LENGTH_LONG).show()
                }
            }
        else
        {
            Toast.makeText(
                applicationContext, "All fields must be filled out",
                Toast.LENGTH_LONG
            ).show()
        }

    }

    private fun sendEmailVerification() {
        // [START send_email_verification]
        val user = Firebase.auth.currentUser

        user!!.sendEmailVerification()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "Email sent.")
                }
            }.addOnFailureListener { exception ->
                Toast.makeText(applicationContext, exception.localizedMessage, Toast.LENGTH_LONG)
                    .show()
                // [END send_email_verification]
            }

    }
}