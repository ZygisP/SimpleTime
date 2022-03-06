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
import com.amplifyframework.core.Amplify
import com.amplifyframework.datastore.AWSDataStorePlugin
import com.amplifyframework.datastore.generated.model.Users
import com.example.simpletime.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        try {
            Amplify.addPlugin(AWSDataStorePlugin())
            Amplify.configure(applicationContext)

            Log.i("Tutorial", "Initialized Amplify")
        } catch (failure: AmplifyException) {
            Log.e("Tutorial", "Could not initialize Amplify", failure)
        }
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
       Amplify.DataStore.save(item,
         { Log.i("Tutorial", "Saved item: ${item.usrname}") },
           { Log.e("Tutorial", "Could not save item to DataStore", it) }
       )
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
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}
