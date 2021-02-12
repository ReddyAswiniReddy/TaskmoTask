package com.example.taskmo.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.taskmo.R


const val fragmentContainerId = R.id.frame_layout

fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
    beginTransaction().func().commitAllowingStateLoss()
}

fun FragmentActivity.pushFragment(fragment: Fragment, addToBackStack: Boolean = false, ignoreIfCurrent: Boolean,
                                  justAdd: Boolean = true) {

    val currentFragment = supportFragmentManager.findFragmentById(fragmentContainerId)

    if (ignoreIfCurrent && currentFragment != null) {
        if (fragment.javaClass.canonicalName.equals(currentFragment.tag)) {
            return
        }
    }
    supportFragmentManager.inTransaction {

        if (currentFragment != null) {
            hide(currentFragment)
        }

        if (addToBackStack) {
            addToBackStack(fragment.javaClass.canonicalName)
        } else {
            if (currentFragment != null) {
                remove(currentFragment)
            }
        }

        if (justAdd) {
            add(fragmentContainerId, fragment, fragment.javaClass.canonicalName)
        } else {
            replace(fragmentContainerId, fragment, fragment.javaClass.canonicalName)
        }
    }
}

fun FragmentActivity.addFragment(fragment: Fragment, addToBackStack: Boolean = false, ignoreIfCurrent: Boolean = true) {
    pushFragment(fragment, addToBackStack, ignoreIfCurrent)
}
