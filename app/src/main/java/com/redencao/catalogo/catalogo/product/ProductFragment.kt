package com.redencao.catalogo.catalogo.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.redencao.catalogo.catalogo.R
import com.redencao.catalogo.catalogo.shared.BaseFragment

class ProductFragment: BaseFragment() {

    companion object {
        fun newInstance(): ProductFragment {
            return ProductFragment()
        }
    }

    private var verticalOffset = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_product, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }

}