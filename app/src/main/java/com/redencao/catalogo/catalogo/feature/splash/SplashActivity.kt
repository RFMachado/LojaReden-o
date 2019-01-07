package com.redencao.catalogo.catalogo.feature.splash

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.redencao.catalogo.catalogo.R
import com.redencao.catalogo.catalogo.feature.main.ui.MainActivity

class SplashActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            gotoMainActivity()
        }, 700)
    }

    fun gotoMainActivity() {
        val intent = MainActivity.launchIntent(this)
        startActivity(intent)

        finish()
    }

}