package com.redencao.catalogo.catalogo.feature.product.ui

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.redencao.catalogo.catalogo.R
import com.redencao.catalogo.catalogo.feature.database.model.ProductData
import com.redencao.catalogo.catalogo.feature.shared.BaseFragment
import kotlinx.android.synthetic.main.fragment_product.*
import org.koin.android.viewmodel.ext.android.viewModel
import permissions.dispatcher.NeedsPermission
import permissions.dispatcher.RuntimePermissions
import timber.log.Timber
import java.io.IOException


@RuntimePermissions
class ProductFragment: BaseFragment() {

    private val viewModel: ProductViewModel by viewModel()
    val imagesUri = ArrayList<String>()

    companion object {
        const val OPEN_GALLERY = 2

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
                    imagesUri[0],
                    imagesUri[1],
                    imagesUri[2]
                )
            )
        }

    }

    @NeedsPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
    fun openGallery() {
        val intent = Intent().apply {
            type = "image/*"
            putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
            action = Intent.ACTION_GET_CONTENT
        }

        startActivityForResult(Intent.createChooser(intent, "Select Picture"), OPEN_GALLERY)
    }

    fun setupSpinner() = with(spinnerType) {
        val spinnerTypeAdapter = ArrayAdapter.createFromResource(context, R.array.product_type, android.R.layout.simple_spinner_item)
        spinnerTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        adapter = spinnerTypeAdapter

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {

            if(requestCode == OPEN_GALLERY && data != null) {
                try {
                    imagesUri.clear()
                    if (data.clipData != null) {
                        val count =
                            data.clipData.itemCount

                        for (i in 0 until count)
                            imagesUri.add(data.clipData.getItemAt(i).uri.toString())

                    }

                } catch (e: IOException) {
                    Timber.e(e)
                }
            }

        }

    }

}