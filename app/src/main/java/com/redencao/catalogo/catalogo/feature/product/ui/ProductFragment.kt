package com.redencao.catalogo.catalogo.feature.product.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.redencao.catalogo.catalogo.R
import com.redencao.catalogo.catalogo.feature.shared.BaseFragment
import kotlinx.android.synthetic.main.fragment_product.*
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

        setupSpinner()

//        viewModel.addProduct(
//            ProductData(
//                0,
//                "camisa redenção",
//                "camisa",
//                "GG",
//                "azul"
//            )
//        )
    }

    fun setupSpinner() = with(spinnerType) {
        val spinnerTypeAdapter = ArrayAdapter.createFromResource(context, R.array.product_type, android.R.layout.simple_spinner_item)
        spinnerTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        adapter = spinnerTypeAdapter

    }

}