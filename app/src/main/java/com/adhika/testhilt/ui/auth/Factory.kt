package com.adhika.testhilt.ui.auth

interface Factory<T> {
    fun create() : T
}