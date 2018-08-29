package com.involves.involvesteste.base

import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.include_toolbar.*


open class BaseActivity : AppCompatActivity() {

    protected fun setUpToolbar() {
        if (toolbar != null) {
            setSupportActionBar(toolbar)
        }
    }
}