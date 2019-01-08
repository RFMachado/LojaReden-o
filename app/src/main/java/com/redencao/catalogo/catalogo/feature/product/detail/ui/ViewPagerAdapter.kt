package com.redencao.catalogo.catalogo.feature.product.detail.ui

import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.redencao.catalogo.catalogo.R
import com.redencao.catalogo.catalogo.util.extensions.inflate
import kotlinx.android.synthetic.main.view_pager_item.view.*

class ViewPagerAdapter(
    private val dataList: List<String>,
    private val listener: (List<String>, Int) -> Unit) : PagerAdapter() {

    override fun isViewFromObject(view: View, item: Any) = view == item
    override fun getCount() = dataList.size

    override fun destroyItem(container: ViewGroup, position: Int, item: Any) {
        container.removeView(item as View)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val itemView = container.inflate(R.layout.view_pager_item)
        val data = dataList[position]

        itemView.apply {
            Glide.with(context)
                .load(data)
                .into(imgProduct)

            setOnClickListener {
                listener.invoke(dataList, position)
            }
        }

        container.addView(itemView)
        return itemView
    }

}