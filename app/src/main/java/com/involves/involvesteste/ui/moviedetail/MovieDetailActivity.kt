package com.involves.involvesteste.ui.moviedetail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.involves.involvesteste.R

class MovieDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        val movieId = intent.getIntExtra("movie_id", 0)

        supportFragmentManager.beginTransaction()
                .replace(R.id.container_fragment, MovieDetailFragment.newInstance(movieId))
                .commit()


//        var movieDetailFragment = supportFragmentManager.getFragment(R.id.movieDetailFragment)
//        movieDetailFragment.build(movieId)
    }
}
