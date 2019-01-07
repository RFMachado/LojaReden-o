package com.redencao.catalogo.catalogo.feature.product.create.ui

import androidx.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.esafirm.imagepicker.features.ImagePicker
import com.redencao.catalogo.catalogo.R
import com.redencao.catalogo.catalogo.feature.database.model.ProductData
import com.redencao.catalogo.catalogo.feature.shared.BaseFragment
import com.redencao.catalogo.catalogo.util.extensions.toast
import kotlinx.android.synthetic.main.fragment_product.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import permissions.dispatcher.NeedsPermission
import permissions.dispatcher.RuntimePermissions
import timber.log.Timber


@RuntimePermissions
class ProductFragment: BaseFragment() {

    private val viewModel: ProductViewModel by viewModel()
    private val imagesFromGallery = ArrayList<String>()

    companion object {

        fun newInstance(): ProductFragment {
            return ProductFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupViewModel()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_product, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupSpinner()
        setuRecyclerView()
        bindListeners()
    }

    private fun bindListeners() {

        btnGallery.setOnClickListener {
            openGallery()
        }

        btnAdd.setOnClickListener {

            if (!edtDescription.text!!.isEmpty() && !edtValue.text!!.isEmpty()) {
                viewModel.addProduct(
                    ProductData(
                        0,
                        edtDescription.text.toString(),
                        spinnerCategory.selectedItem.toString(),
                        "",
                        "",
                        edtValue.text.toString().toDouble(),
                        imagesFromGallery
                    )
                )
            } else {
                context?.toast(R.string.missing_data)
            }
        }

    }

    @NeedsPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
    fun openGallery() {
        ImagePicker.create(this)
            .multi()
            .start()
    }

    private fun setuRecyclerView() = with(recyclerView) {
        layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        adapter = ImagesPickerAdapter(imagesFromGallery)
    }

    private fun setupSpinner() = with(spinnerCategory) {
        val spinnerCategoryAdapter = ArrayAdapter.createFromResource(context, R.array.product_type, android.R.layout.simple_spinner_item)
        spinnerCategoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        adapter = spinnerCategoryAdapter

    }

    private fun setupViewModel() {
        viewModel.uiData.observe(this, Observer { uiData ->
            when {
                uiData?.data == true -> {
                    showResult()
                }
                uiData?.error is Throwable -> Timber.e(uiData.error)
            }
        })
    }

    private fun showResult() {
        context?.toast(R.string.data_ok)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (ImagePicker.shouldHandle(requestCode, resultCode, data)) {

            val images = ImagePicker.getImages(data)

            imagesFromGallery.clear()

            images.forEach { image ->
                imagesFromGallery.add(image.path)
            }

            recyclerView.adapter?.notifyDataSetChanged()

        }

        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onDestroyView() {
        viewModel.unbind()
        super.onDestroyView()
    }

}