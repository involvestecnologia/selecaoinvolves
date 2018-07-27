package com.involves.involvesteste

import android.os.Bundle
import com.involves.involvesteste.base.BaseActivity
import com.involves.involvesteste.ui.movies.MoviesFragment

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpToolbar()

        supportActionBar?.title = "Pay Attention"

        supportFragmentManager.beginTransaction()
                .replace(R.id.container_fragment, MoviesFragment.newInstance())
                .commit()
    }
}
