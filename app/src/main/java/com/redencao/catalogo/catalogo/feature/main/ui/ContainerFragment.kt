package com.redencao.catalogo.catalogo.feature.main.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.redencao.catalogo.catalogo.R
import com.redencao.catalogo.catalogo.feature.catalog.ui.CatalogFragment
import com.redencao.catalogo.catalogo.feature.order.ui.OrderFragment
import com.redencao.catalogo.catalogo.feature.product.create.ui.ProductFragment
import com.redencao.catalogo.catalogo.feature.shared.BaseFragment

class ContainerFragment : BaseFragment() {

    private val fragmentType by lazy { arguments!!.getInt(EXTRA_FRAGMENT_TYPE, 0) }

    var currentFragment: Fragment? = null
        private set

    private var fragmentContainer: View? = null

    val fragmentStackSize: Int
        get() = childFragmentManager.backStackEntryCount

    enum class FragmentType {
        CATALOG {
            override val instance: Fragment
                get() = CatalogFragment.newInstance()
        },
        PRODUCT {
            override val instance: Fragment
                get() = ProductFragment.newInstance()
        },
        ORDER {
            override val instance: Fragment
                get() = OrderFragment.newInstance()
        },
        NONE {
            override val instance: Fragment?
                get() = null
        };

        abstract val instance: Fragment?
    }

    companion object {
        private const val EXTRA_FRAGMENT_TYPE = "fragmentType"

        fun newInstance(fragmentType: FragmentType): ContainerFragment {
            val args = Bundle()
            args.putInt(EXTRA_FRAGMENT_TYPE, fragmentType.ordinal)

            val fragment = ContainerFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentContainer = inflater.inflate(R.layout.fragment_container, container, false)

        val fragmentManager = childFragmentManager
        fragmentManager.addOnBackStackChangedListener {
            currentFragment = fragmentManager.findFragmentById(R.id.container)
        }

        return fragmentContainer
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        currentFragment = childFragmentManager.findFragmentById(R.id.container)
        if (currentFragment == null) {
            val types = FragmentType.values()

            getFragmentByType(types[fragmentType])?.let { fragment ->
                openFragment(fragment)
            }
        }
    }

    fun openFragment(fragment: Fragment, addToStack: Boolean = true) {
        if (!isAdded)
            return

        val fragmentManager = childFragmentManager
        val addToBackStack = currentFragment != null

        currentFragment = fragment
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        fragmentTransaction.replace(R.id.container, currentFragment!!)

        if (addToBackStack && addToStack)
            fragmentTransaction.addToBackStack(null)

        fragmentTransaction.commit()

        fragmentManager.executePendingTransactions()
    }

    private fun getFragmentByType(fragmentType: FragmentType, args: Bundle? = null): Fragment? {
        val fragment = fragmentType.instance

        if (args != null)
            fragment?.arguments = args

        return fragment
    }

    fun popFragment(): Boolean {
        val fragmentManager = childFragmentManager
        if (fragmentManager.backStackEntryCount > 0) {
            fragmentManager.popBackStackImmediate()

            return true
        }

        return false
    }

    fun cleanStack() {
        while (popFragment());
    }

}