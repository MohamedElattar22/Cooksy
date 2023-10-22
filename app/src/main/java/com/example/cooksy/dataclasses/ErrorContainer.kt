package com.example.cooksy.dataclasses

data class ErrorContainer(
    val message: String?,
    val throwable: Throwable ?= null ,
    var onTryAgainClickListener: OnTryAgainClickListener?=null
)

fun interface OnTryAgainClickListener {
    fun onTryAgainClick()
}

