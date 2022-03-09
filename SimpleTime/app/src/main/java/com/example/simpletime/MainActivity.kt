package com.example.simpletime

import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.amplifyframework.AmplifyException
import com.amplifyframework.api.aws.AWSApiPlugin
import com.amplifyframework.auth.AuthUserAttributeKey
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin
import com.amplifyframework.auth.options.AuthSignUpOptions
import com.amplifyframework.core.Amplify
import com.amplifyframework.datastore.AWSDataStorePlugin
import com.amplifyframework.datastore.generated.model.Users
import com.amplifyframework.storage.s3.AWSS3StoragePlugin
import com.example.simpletime.databinding.ActivityMainBinding
import java.io.File

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        try {
            Amplify.addPlugin(AWSApiPlugin())
            Amplify.addPlugin(AWSDataStorePlugin())
            Amplify.addPlugin(AWSCognitoAuthPlugin())
            Amplify.addPlugin(AWSS3StoragePlugin())
            Amplify.configure(applicationContext)

            Log.i("Tutorial", "Initialized Amplify")
        } catch (failure: AmplifyException) {
            Log.e("Tutorial", "Could not initialize Amplify", failure)
        }
        Amplify.Auth.fetchAuthSession(
            { Log.i("AmplifyQuickstart", "Auth session = $it") },
            { error -> Log.e("AmplifyQuickstart", "Failed to fetch auth session", error) }
        )
        Amplify.Auth.signIn("jonasmargirisklimas@gmail.com", "Password123$",
            { result ->
                if (result.isSignInComplete) {
                    Log.i("AuthQuickstart", "Sign in succeeded")
                } else {
                    Log.i("AuthQuickstart", "Sign in not complete")
                }
            },
            { Log.e("AuthQuickstart", "Failed to sign in", it) }
        )
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        val item = Users.builder()
            .usrname("gamer69")
            .passw("123123")
            .email("asdasd@gmail.com")
            .build()
//        Amplify.DataStore.save(item,
//            { Log.i("Tutorial", "Saved item: ${item.usrname}") },
//            { Log.e("Tutorial", "Could not save item to DataStore", it) }
//        )
        Amplify.DataStore.observe(Users::class.java,
            { Log.i("Tutorial", "Observation began.") },
            { Log.i("Tutorial", it.item().toString()) },
            { Log.e("Tutorial", "Observation failed.", it) },
            { Log.i("Tutorial", "Observation complete.") }
        )
        uploadFile()
        Amplify.DataStore.query(Users::class.java,
            { todos ->
                while (todos.hasNext()) {
                    val Users: Users = todos.next()
                    Log.i("Tutorial", "==== Todo ====")
                    Log.i("Tutorial", "Name: ${Users.usrname}")
                    Log.i("Tutorial", "Priority: ${Users.passw}")
                    Log.i("Tutorial", "CompletedAt: ${Users.email}")
                }
            },
            { Log.e("Tutorial", "Could not query DataStore", it)  }
        )
//        val options = AuthSignUpOptions.builder()
//            .userAttribute(AuthUserAttributeKey.email(), "jonasmargirisklimas@gmail.com")
//            .userAttribute(AuthUserAttributeKey.nickname(), "XDXDXDXDXD")
//            .build()
//        Amplify.Auth.signUp("jonasmargirisklimas@gmail.com", "Password123$", options,
//            { Log.i("AuthQuickStart", "Sign up succeeded: $it") },
//            { Log.e ("AuthQuickStart", "Sign up failed", it) }
//        )
//        Amplify.Auth.confirmSignUp(
//            "jonasmargirisklimas@gmail.com", "331140",
//            { result ->
//                if (result.isSignUpComplete) {
//                    Log.i("AuthQuickstart", "Confirm signUp succeeded")
//                } else {
//                    Log.i("AuthQuickstart","Confirm sign up not complete")
//                }
//            },
//            { Log.e("AuthQuickstart", "Failed to confirm sign up", it) }
//        )

    }
    private fun uploadFile() {
        val exampleFile = File(applicationContext.filesDir, "ExampleKey")
        exampleFile.writeText("Example file contents")

        Amplify.Storage.uploadFile("ExampleKey", exampleFile,
            { Log.i("MyAmplifyApp", "Successfully uploaded: ${it.key}") },
            { Log.e("MyAmplifyApp", "Upload failed", it) }
        )
    }



    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}

