package com.redencao.catalogo.catalogo.feature.product.detail.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.redencao.catalogo.catalogo.R
import com.redencao.catalogo.catalogo.feature.catalog.domain.Product
import kotlinx.android.synthetic.main.activity_product_detail.*

class ProductDetailActivity: AppCompatActivity() {
    private val product by lazy { intent.getParcelableExtra(EXTRA_PRODUCT_DATA) as Product }

    companion object {
        const val EXTRA_PRODUCT_DATA = "productData"

        fun launchIntent(context: Context, product: Product): Intent {
            val intent = Intent(context, ProductDetailActivity::class.java)
            intent.putExtra(EXTRA_PRODUCT_DATA, product)

            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        bindListeners()
    }

    private fun bindListeners() {
        btnArrow.setOnClickListener {
            onBackPressed()
        }
    }

}