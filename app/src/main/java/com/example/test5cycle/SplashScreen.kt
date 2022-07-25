package com.example.test5cycle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

//test splash screen
class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        //hide the action bar on top
        supportActionBar?.hide()
        Handler().postDelayed({
            // from splash screen to chooseloginregister
            val intent = Intent(this@SplashScreen,ChooseLoginRegister::class.java)
            startActivity(intent)
            //if back, user wont be backed to splash screen
            finish()
            //set splash screen delay 2000 millisecond
        },2000)
    }
}