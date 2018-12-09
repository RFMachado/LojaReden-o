package com.redencao.catalogo.catalogo.main.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.redencao.catalogo.catalogo.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainListener {

    companion object {
        fun launchIntent(context: Context) = Intent(context, MainActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            containerView.setCurrentFragment(0)
        }

        bindListener()
    }

    private fun bindListener() {
        arrayOf(btnCatalog, btnProduct, btnOrder).mapIndexed { index, btnAction ->
            btnAction.setOnClickListener {
                gotoTab(index)
            }
        }
    }

    private fun gotoTab(position: Int) {
        containerView.currentFragment?.let { currentContainer ->
            if (containerView.currentPosition == position) {
                return
            }
        }

        containerView.setCurrentFragment(position)
    }

    override fun openFragment(fragment: Fragment?) {
        val containerFragment = containerView.currentFragment

        if (containerFragment != null && fragment != null) {
            containerFragment.openFragment(fragment)
        }
    }

}
