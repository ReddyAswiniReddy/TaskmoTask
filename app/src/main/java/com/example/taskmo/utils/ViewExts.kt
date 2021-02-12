package com.example.taskmo.utils

import android.view.View

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun String?.isRequiredField(): Boolean {
    return if (this == null) {
        false
    } else
        isNotEmpty() && isNotBlank()
}

fun String?.validate(): String {

    return this ?: ""

}
