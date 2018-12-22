package com.redencao.catalogo.catalogo.feature.product.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.redencao.catalogo.catalogo.R
import com.redencao.catalogo.catalogo.feature.database.model.ProductData
import com.redencao.catalogo.catalogo.feature.shared.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel

class ProductFragment: BaseFragment() {

    private val viewModel: ProductViewModel by viewModel()

    companion object {
        fun newInstance(): ProductFragment {
            return ProductFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_product, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.addProduct(
            ProductData(
                0,
                "camisa redenção",
                "camisa",
                "GG",
                "azul"
            )
        )
    }

}