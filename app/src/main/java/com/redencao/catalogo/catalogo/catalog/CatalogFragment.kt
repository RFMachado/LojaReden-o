package com.redencao.catalogo.catalogo.catalog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.redencao.catalogo.catalogo.R
import com.redencao.catalogo.catalogo.shared.BaseFragment

class CatalogFragment: BaseFragment() {

    companion object {
        fun newInstance(): CatalogFragment {
            return CatalogFragment()
        }
    }

    private var verticalOffset = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_catalog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }

}