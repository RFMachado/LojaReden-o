package com.redencao.catalogo.catalogo.feature.shared

import android.content.Context
import android.support.v4.app.Fragment
import com.redencao.catalogo.catalogo.feature.main.ui.MainListener

open class BaseFragment: Fragment() {

    var mainListener: MainListener? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        if (context is MainListener)
            mainListener = context
    }

}