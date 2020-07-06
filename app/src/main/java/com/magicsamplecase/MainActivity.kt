package com.magicsamplecase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpInitialScreen()
    }

    private fun setUpInitialScreen() =
        supportFragmentManager.beginTransaction()
            .add(R.id.main_container, MainContainerFragment())
            .commit()

    override fun onBackPressed() {
        // get MainContainerFragment this way
        val childFragment = supportFragmentManager.findFragmentById(R.id.main_container) as HandleBackButtom
        // Tells MainActivity to call fragment's onBackPressed()
        childFragment.onBackPressed()

        Log.d("DEBUG_ACTIVITY", childFragment.toString())
    }
}