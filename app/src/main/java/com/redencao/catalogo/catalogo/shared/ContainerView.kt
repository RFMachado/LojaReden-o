package com.redencao.catalogo.catalogo.shared

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.support.v4.view.AbsSavedState
import android.util.AttributeSet
import android.widget.FrameLayout

class ContainerView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0): FrameLayout(context, attrs, defStyleAttr) {

    private val fragmentManager: FragmentManager by lazy { (context as FragmentActivity).supportFragmentManager }
    var currentFragment: ContainerFragment? = null
        private set

    var currentPosition = 0
        private set

    private var listener: ((Int) -> Unit)? = null

    fun getItem(position: Int): Fragment? = when (position) {
        0 -> ContainerFragment.newInstance(ContainerFragment.FragmentType.CATALOG)
        1 -> ContainerFragment.newInstance(ContainerFragment.FragmentType.PRODUCT)
        2 -> ContainerFragment.newInstance(ContainerFragment.FragmentType.ORDER)
        else -> null
    }

    fun setCurrentFragment(position: Int) {
        val currentTransaction = fragmentManager.beginTransaction()

        currentFragment?.let {
            currentTransaction.detach(it)
        }

        val name = makeFragmentName(id, position)
        var fragment = fragmentManager.findFragmentByTag(name)

        if (fragment != null) {
            currentTransaction.attach(fragment)
        }
        else {
            fragment = getItem(position)
            currentTransaction.add(id, fragment, name)
        }

        currentFragment = fragment as? ContainerFragment
        currentPosition = position

        currentTransaction.commitNowAllowingStateLoss()

        listener?.invoke(position)
    }

    fun setOnChangeListener(onChangeListener: ((Int) -> Unit)?) {
        listener = onChangeListener
    }

    override fun onSaveInstanceState(): Parcelable {
        val superState = super.onSaveInstanceState()
        val ss = SavedState(superState)
        ss.position = currentPosition
        return ss
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        if (state !is SavedState) {
            super.onRestoreInstanceState(state)
            return
        }

        super.onRestoreInstanceState(state.superState)
        currentPosition = state.position
        setCurrentFragment(currentPosition)
    }

    class SavedState: AbsSavedState {
        var position: Int = 0
        lateinit var loader: ClassLoader

        constructor(superState: Parcelable): super(superState)

        constructor(parcel: Parcel, classLoader: ClassLoader?): super(parcel, classLoader) {
            val loader = classLoader ?: javaClass.classLoader

            position = parcel.readInt()
            this.loader = loader
        }

        override fun writeToParcel(out: Parcel, flags: Int) {
            super.writeToParcel(out, flags)
            out.writeInt(position)
        }

        override fun toString(): String {
            return ("ContainerHomeView.SavedState{"
                    + Integer.toHexString(System.identityHashCode(this))
                    + " position=" + position + "}")
        }

        companion object {
            @JvmField
            val CREATOR: Parcelable.Creator<SavedState> = object: Parcelable.ClassLoaderCreator<SavedState> {
                override fun createFromParcel(parcel: Parcel, loader: ClassLoader): SavedState {
                    return SavedState(parcel, loader)
                }

                override fun createFromParcel(parcel: Parcel): SavedState {
                    return SavedState(parcel, null)
                }

                override fun newArray(size: Int): Array<SavedState?> {
                    return arrayOfNulls(size)
                }
            }
        }
    }


    private fun makeFragmentName(viewId: Int, id: Int): String {
        return "android:switcher:$viewId:$id"
    }
}