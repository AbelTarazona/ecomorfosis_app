package com.ecomorfosis.global.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.*
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.viewbinding.ViewBinding

/**
 * Created by AbelTarazona on 27/08/2020
 */

fun ViewGroup.inflate(@LayoutRes layoutRes: Int) : View =
    LayoutInflater.from(context).inflate(layoutRes, this, false)

inline fun <T : ViewBinding> AppCompatActivity.viewBinding(
    crossinline bindingInflater: (LayoutInflater) -> T
) =
    lazy(LazyThreadSafetyMode.NONE) {
        bindingInflater.invoke(layoutInflater)
    }

fun AppCompatActivity.replaceFragment(fragment: Fragment, frameId: Int) {
    supportFragmentManager.inTransaction { replace(frameId, fragment, fragment.javaClass.simpleName) }
}
inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
    beginTransaction().func().commit()
}

inline fun <reified T : Any> Context.launchActivity(
    noinline modify: Intent.() -> Unit = {}
) {
    val intent = Intent(this, T::class.java)
    intent.modify()
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    startActivity(intent)
}

fun Context.getDrawableImg(@DrawableRes id: Int) = ContextCompat.getDrawable(this, id)

fun Activity.statusBarColorNew(color: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        val window: Window = window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, color)
    }
}