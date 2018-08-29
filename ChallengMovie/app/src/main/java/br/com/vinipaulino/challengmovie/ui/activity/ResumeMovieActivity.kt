package br.com.vinipaulino.challengmovie.ui.activity

import android.annotation.TargetApi
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import br.com.anestech.axcalc.AppConstants
import br.com.anestech.axcalc.database.MovieDAO
import br.com.anestech.axreg_droid.retrofit.response.CallbackResponse
import br.com.anestech.axreg_droid.retrofit.webclient.MovieWebClient
import br.com.vinipaulino.challengmovie.R
import br.com.vinipaulino.challengmovie.extensions.setupToolbar
import br.com.vinipaulino.challengmovie.model.MovieDetails
import br.com.vinipaulino.challengmovie.model.Movies
import br.com.vinipaulino.challengmovie.util.AndroidUtils
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_resume_movie.*
import org.jetbrains.anko.toast
import java.text.SimpleDateFormat

class ResumeMovieActivity : AppCompatActivity() {

    private var movie: Movies? = null
    private var movieDetails: MovieDetails? = null
    private var backdrop: String? = ""
    private var movieId: String? = ""

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resume_movie)
        setupToolbar(R.id.toolbar, "Resumo do filme", true)

        val intent = intent
        this.movieId = intent.getStringExtra("movie_id")

        val internetOk = AndroidUtils.isNetworkAvailable(this)
        if (internetOk) {
            if (movieId != null) {
                getMovieDetails()
            }
        } else {
            loadMovieBd()
            carregaImagem()
            progressBarResume.visibility = View.GONE
        }

    }

    private fun getMovieDetails() {
        MovieWebClient().getMovieDetails(movieId!!, object : CallbackResponse<MovieDetails> {
            override fun success(response: MovieDetails) {
                loadMovieDetails(response)
                carregaImagem()
                progressBarResume.visibility = View.GONE
            }

            override fun failure(throwable: Throwable) {
                toast("Não foi possivel realizar a comunicação com o servidor")
                progressBarResume.visibility = View.GONE
            }

            override fun responseFailure() {
                toast("Filme não encontrado")
                progressBarResume.visibility = View.GONE
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId
        if (id == R.id.home) {
            onBackPressed()
            return true
        }
        return false
    }

    private fun loadMovieDetails(response: MovieDetails) {
        movieDetails = response
        backdrop = movieDetails?.backdrop_path
        txt_title_movie.text = movieDetails?.title
        txt_sipnose.text = movieDetails?.overview
        val dataEmUmFormato = movieDetails?.release_date
        val formato = SimpleDateFormat("yyyy-MM-dd")
        val data = formato.parse(dataEmUmFormato)
        formato.applyPattern("dd/MM/yyyy")
        val dataFormatada = formato.format(data)
        txt_datalancamento.text = dataFormatada
        txt_title_sipnose.visibility = View.VISIBLE
        txt_title_date_release.visibility = View.VISIBLE
    }

    private fun loadMovieBd() {
        movie = MovieDAO.findMovie(movie_id = movieId?.toLong())
        backdrop = movie?.backdrop_path

        txt_title_movie.text = movie?.title
        txt_sipnose.text = movie?.overview

        val dataEmUmFormato = movie?.release_date
        val formato = SimpleDateFormat("yyyy-MM-dd")
        val data = formato.parse(dataEmUmFormato)
        formato.applyPattern("dd/MM/yyyy")
        val dataFormatada = formato.format(data)
        txt_datalancamento.text = dataFormatada
        txt_title_sipnose.visibility = View.VISIBLE
        txt_title_date_release.visibility = View.VISIBLE

    }

    private fun carregaImagem() {
        Picasso.with(this)
                .load(AppConstants.IMG_BASE_URL + backdrop)
                .resize(1100, 500)
                .centerCrop()
                .into(img_resume_movie)
    }

    override fun onBackPressed() {
        finish()
    }
}
