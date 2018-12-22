package com.redencao.catalogo.catalogo.feature.catalog.ui

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.redencao.catalogo.catalogo.R
import com.redencao.catalogo.catalogo.feature.catalog.domain.Product
import com.redencao.catalogo.catalogo.feature.shared.BaseFragment
import kotlinx.android.synthetic.main.fragment_catalog.*
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber

class CatalogFragment: BaseFragment() {

    private val viewModel: CatalogViewModel by viewModel()
    var items =  arrayListOf<Product>()

    companion object {
        fun newInstance(): CatalogFragment {
            return CatalogFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_catalog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setuRecyclerView()
        setupViewModel()

        viewModel.fetchAllProducts()
    }

    private fun setuRecyclerView() = with(recyclerView) {
        layoutManager = LinearLayoutManager(context)
        adapter = CatalogAdapter(items)
    }

    private fun setupViewModel() {
        viewModel.uiData.observe(this, Observer { uiData ->
            when{
                uiData?.data != null -> showData(uiData.data)
                uiData?.error is Throwable -> Timber.e(uiData.error)
            }
        })
    }

    private fun showData(products: List<Product>) {
        items.clear()
        items.addAll(products)
        recyclerView?.adapter?.notifyDataSetChanged()
    }

}