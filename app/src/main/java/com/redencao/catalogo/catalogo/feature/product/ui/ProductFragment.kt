package com.redencao.catalogo.catalogo.feature.product.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.esafirm.imagepicker.features.ImagePicker
import com.esafirm.imagepicker.model.Image
import com.redencao.catalogo.catalogo.R
import com.redencao.catalogo.catalogo.feature.database.model.ProductData
import com.redencao.catalogo.catalogo.feature.shared.BaseFragment
import kotlinx.android.synthetic.main.fragment_product.*
import org.koin.android.viewmodel.ext.android.viewModel
import permissions.dispatcher.NeedsPermission
import permissions.dispatcher.RuntimePermissions


@RuntimePermissions
class ProductFragment: BaseFragment() {

    private val viewModel: ProductViewModel by viewModel()
    val imagesFromGallery = ArrayList<Image>()

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
        bindListeners()
    }

    private fun bindListeners() {

        btnGallery.setOnClickListener {
            openGallery()
        }

        btnAdd.setOnClickListener {
            viewModel.addProduct(
                ProductData(
                    0,
                    "camisa redenção",
                    "camisa",
                    "GG",
                    "azul",
                    imagesFromGallery[0].path,
                    imagesFromGallery[1].path,
                    imagesFromGallery[2].path
                )
            )
        }

    }

    @NeedsPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
    fun openGallery() {
        ImagePicker.create(this)
            .multi()
            .start()
    }

    fun setupSpinner() = with(spinnerType) {
        val spinnerTypeAdapter = ArrayAdapter.createFromResource(context, R.array.product_type, android.R.layout.simple_spinner_item)
        spinnerTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        adapter = spinnerTypeAdapter

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (ImagePicker.shouldHandle(requestCode, resultCode, data)) {

            val images = ImagePicker.getImages(data)

            imagesFromGallery.clear()
            imagesFromGallery.addAll(images)
        }

        super.onActivityResult(requestCode, resultCode, data)
    }

}