package com.involves.involvesteste.ui.movies

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.arch.paging.PagedList
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import com.involves.involvesteste.R
import com.involves.involvesteste.base.BaseFragment
import com.involves.involvesteste.data.model.Movie
import com.involves.involvesteste.injection.Injection
import com.involves.involvesteste.ui.movies.adapter.MoviesAdapter
import kotlinx.android.synthetic.main.fragment_movies.*
import kotlinx.android.synthetic.main.include_error_layout.*
import kotlinx.android.synthetic.main.include_loader.*


class MoviesFragment : BaseFragment() {

    private lateinit var recyclerView : RecyclerView
    private lateinit var viewModel : MoviesViewModel
    val adapter = MoviesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        context?.let {
            viewModel = ViewModelProviders.of(this, Injection.provideViewModelFactory(it))
                    .get(MoviesViewModel::class.java)
        }

        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showLoading(true)
        recyclerView = recyclerView_movies
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager
        configAdapter()

        viewModel.searchMovies("")
        showLoading(true)

    }

    private fun configAdapter() {
        recyclerView.adapter = adapter
        viewModel.movies.observe(this, Observer<PagedList<Movie>> {
            if (it?.size == 0) {
                showError(true)
            } else {
                showError(false)
                adapter.submitList(it)
            }
        })
    }

    private fun showError(hasError: Boolean) {
        showLoading(false)
        if (hasError) {
            error_layout.visibility = VISIBLE
            btn_try_again.setOnClickListener {
                viewModel.searchMovies("")
                showLoading(true)
                error_layout.visibility = GONE
            }
        } else {
            error_layout.visibility = GONE
        }
    }

    private fun showLoading(loading: Boolean) {
        if (loading) {
            progress.visibility = VISIBLE
        } else {
            progress.visibility = GONE
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
                MoviesFragment().apply {
                    arguments = Bundle().apply {
                    }
                }
    }
}
