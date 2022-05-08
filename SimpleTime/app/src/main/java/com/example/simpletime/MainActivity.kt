package com.example.simpletime

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.actionCodeSettings
import kotlinx.android.synthetic.main.activity_login.*


class MainActivity : AppCompatActivity() {

//    private lateinit var appBarConfiguration: AppBarConfiguration
//    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()


        login_btnCreateAcc.setOnClickListener{
            val intent = Intent(this, ActivityRegistration::class.java);
            startActivity(intent)
        }

        login_btnLogin.setOnClickListener{
         login(it)
        }

        login_btnForgotPassword.setOnClickListener{
            val intent = Intent(this, ActivityForgotPassword::class.java);
            startActivity(intent)
        }
        login_btnForgotUsername.setOnClickListener{
            val intent = Intent(this, ActivityFeed::class.java)
            startActivity(intent)
        }
    }
    fun login(view: View) {
        val email = login_enterName.text.toString()
        val password = login_enterPassword.text.toString()
        if (email.isNotEmpty() && password.isNotEmpty())
        {
            auth.signInWithEmailAndPassword(email,password).addOnCompleteListener { task ->
                if(task.isSuccessful){
                    val intent= Intent(this,ActivityUserHome::class.java)
                    startActivity(intent)
                    finish()
                }
            }.addOnFailureListener { exception ->
                Toast.makeText(applicationContext,exception.localizedMessage, Toast.LENGTH_LONG).show()
            }
        }
        else {
            Toast.makeText(
                applicationContext, "Email and password fields cannot be empty",
                Toast.LENGTH_LONG
            ).show()
        }

    }
  private fun buildActionCodeSettings() {
        // [START auth_build_action_code_settings]
        val actionCodeSettings = actionCodeSettings {
            // URL you want to redirect back to. The domain (www.example.com) for this
            // URL must be whitelisted in the Firebase Console.
            url = "https://www.example.com/finishSignUp?cartId=1234"
            // This must be true
            handleCodeInApp = true
            setIOSBundleId("com.example.ios")
            setAndroidPackageName(
                "com.example.android",
                true, /* installIfNotAvailable */
                "12" /* minimumVersion */)
        }
        // [END auth_build_action_code_settings]
    }



}
