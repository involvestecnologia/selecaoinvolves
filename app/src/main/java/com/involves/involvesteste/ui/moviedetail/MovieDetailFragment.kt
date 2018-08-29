package com.involves.involvesteste.ui.moviedetail


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.involves.involvesteste.R
import com.involves.involvesteste.injection.Injection
import com.involves.involvesteste.utils.Constants
import com.involves.involvesteste.utils.Utils
import kotlinx.android.synthetic.main.fragment_movie_detail.*
import kotlinx.android.synthetic.main.include_loader.*

class MovieDetailFragment : Fragment() {

    private var movieId: Int = 0

    private lateinit var viewModel: MovieDetailViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        arguments?.let {
            movieId = it.getInt("movie_id")
        }

        return inflater.inflate(R.layout.fragment_movie_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        showLoading(true)

        context?.let {
            viewModel = ViewModelProviders.of(this, Injection.provideViewModelFactory(it))
                    .get(MovieDetailViewModel::class.java)
        }

        viewModel.movieDetail.observe(this, Observer {

            it?.let {

                setEmptyFields()

                showLoading(false)
                toolbar.title = it.movie.title
                Glide.with(this)
                        .load(Constants.IMG_BASE_URL + it.movie.backdropPath)
                        .apply(RequestOptions().centerCrop()
                                .placeholder(R.drawable.no_image_placeholder))
                        .into(appbar_movie_poster)


                it.movie.releaseDate?.let {
                    txt_release_date.text = String.format(getString(R.string.release_date),
                            Utils.formatDate("yyyy-MM-dd", "dd/MM/yyyy", it))
                }
                if (!TextUtils.isEmpty(it.movie.tagline)) txt_description.text = it.movie.tagline

                txt_genre.text = it.genres.map { it.name }.joinToString(", ")
                txt_overview.text = it.movie.overview
            }
        })

        viewModel.getMovieDetail(movieId)

    }

    private fun setEmptyFields() {
        toolbar.title = " - "
        txt_description.text = " - "
        txt_genre.text = " - "
        txt_overview.text = " - "
    }

    companion object {
        @JvmStatic
        fun newInstance(movieId : Int) = MovieDetailFragment().apply {
            arguments = Bundle().apply {
                putInt("movie_id", movieId)
            }
        }
    }

    private fun showLoading(loading: Boolean) {
        if (loading) {
            progress.visibility = View.VISIBLE
        } else {
            progress.visibility = View.GONE
        }
    }
//    fun build(movieId:Int) {
//
//    }
}
