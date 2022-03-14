package com.example.simpletime

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_first.*
import kotlinx.android.synthetic.main.fragment_second.*


private lateinit var auth: FirebaseAuth
class SecondFragment : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_second)

        auth = FirebaseAuth.getInstance()

        signup_btnBack.setOnClickListener{

            val intent = Intent(this, MainActivity::class.java);
            startActivity(intent)

        }
        signup_btnSignup.setOnClickListener{
            register(it)
        }
    }

    fun register(view: View)
    {
        val email = signup_enterEmail.text.toString()
        val name = signup_enterName.text.toString()
        val password = signup_enterPassword.text.toString();

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener() {      task ->

        if(task.isSuccessful)
            {
                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }.addOnFailureListener { exception ->
            Toast.makeText(applicationContext,exception.localizedMessage,Toast.LENGTH_LONG).show()
        }
    }

}