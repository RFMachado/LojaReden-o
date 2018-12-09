package com.redencao.catalogo.catalogo.shared

import android.content.Context
import android.support.v4.app.Fragment
import com.redencao.catalogo.catalogo.main.ui.MainListener

open class BaseFragment: Fragment() {

    var mainListener: MainListener? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        if (context is MainListener)
            mainListener = context
    }

}