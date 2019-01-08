package com.redencao.catalogo.catalogo.feature.product.zoom

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.redencao.catalogo.catalogo.R
import kotlinx.android.synthetic.main.activity_zoom.*

class ProductZoomActivity: AppCompatActivity() {

    private val imageList by lazy { intent.getStringArrayListExtra(EXTRA_PRODUCT_IMAGES) }
    private val position by lazy { intent.getIntExtra(EXTRA_IMAGE_SELECTED_POSITION, 0) }

    companion object {
        const val EXTRA_PRODUCT_IMAGES = "productImages"
        const val EXTRA_IMAGE_SELECTED_POSITION = "imageSelectedPosition"

        fun launchIntent(context: Context, imageList: List<String>, position: Int): Intent {
            val intent = Intent(context, ProductZoomActivity::class.java)
            intent.putStringArrayListExtra(EXTRA_PRODUCT_IMAGES, imageList as ArrayList<String>)
            intent.putExtra(EXTRA_IMAGE_SELECTED_POSITION, position)

            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_zoom)

        setupViewPager()
        bindListener()
    }

    private fun setupViewPager() {
        viewPager.adapter = ViewPagerZoomAdapter(imageList)
        viewPager.setCurrentItem(position, false)
    }

    private fun bindListener() {
        btnArrow.setOnClickListener {
            onBackPressed()
        }
    }

}