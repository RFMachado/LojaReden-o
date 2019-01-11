package com.redencao.catalogo.catalogo.feature.product.detail.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.redencao.catalogo.catalogo.R
import com.redencao.catalogo.catalogo.feature.catalog.domain.Product
import com.redencao.catalogo.catalogo.feature.product.zoom.ProductZoomActivity
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
        setupViewPager()
        setupRecyclerView()

        txtDescription.text = product.description
        txtPrice.text = getString(R.string.product_value, product.value)
    }

    private fun bindListeners() {
        btnArrow.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setupViewPager() {
        viewPager.adapter = ViewPagerAdapter(product.images) { imageList, position ->
            val intent = ProductZoomActivity.launchIntent(this, imageList, position)
            startActivity(intent)
        }

        indicator.setViewPager(viewPager)
    }

    private fun setupRecyclerView() {
        val sizes = resources.getStringArray(R.array.product_size)

        recyclerView.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        recyclerView.adapter = SizeAdapter(sizes)
    }

}