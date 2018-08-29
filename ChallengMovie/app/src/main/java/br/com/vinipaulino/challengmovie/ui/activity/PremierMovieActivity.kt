package br.com.vinipaulino.challengmovie.ui.activity

import android.app.SearchManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import br.com.anestech.axcalc.database.MovieDAO
import br.com.anestech.axreg_droid.retrofit.response.CallbackResponse
import br.com.anestech.axreg_droid.retrofit.webclient.MovieWebClient
import br.com.vinipaulino.challengmovie.R
import br.com.vinipaulino.challengmovie.extensions.setupToolbar
import br.com.vinipaulino.challengmovie.model.Movies
import br.com.vinipaulino.challengmovie.ui.adapter.MovieAdapter
import br.com.vinipaulino.challengmovie.util.AndroidUtils
import br.com.vinipaulino.challengmovie.util.ListItemDecorations
import io.realm.Case
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_premier_movie.*
import org.jetbrains.anko.toast
import java.lang.Exception
import java.util.*
import kotlin.concurrent.schedule

class PremierMovieActivity : BaseActivity() {

    lateinit var myCustomAdapter: MovieAdapter
    private lateinit var movies: MutableList<Movies>
    private var listMovies: MutableList<Movies>? = null

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_premier_movie)
        setupToolbar(R.id.toolbar, "Filmes a estrear")

        configRecycleView()

        val internetOk = AndroidUtils.isNetworkAvailable(this)

        if (internetOk) {
            getMovieWebClient()
        } else {
            getMoviesBD()
        }
    }

    private fun getMoviesBD() {
        val realm = Realm.getDefaultInstance()
        movies = realm.copyFromRealm(realm.where(Movies::class.java).findAll())
        realm.close()
        myCustomAdapter = MovieAdapter(applicationContext, movies)
        recyclerView.adapter = myCustomAdapter
        progressBar.visibility = View.GONE
    }

    private fun getMovieWebClient() {
        MovieWebClient().getMoviesPremier(object : CallbackResponse<List<Movies>> {
            override fun success(response: List<Movies>) {
                MovieDAO.saveList(response)
                movies = response as MutableList<Movies>
                myCustomAdapter = MovieAdapter(applicationContext, movies)
                recyclerView.setAdapter(myCustomAdapter)
                progressBar.visibility = View.GONE
            }

            override fun failure(throwable: Throwable) {
                toast("Não foi possivel conectar ao servidor")
            }

            override fun responseFailure() {
                toast("Filme não Encontrado")
            }
        })
    }

    private fun configRecycleView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(ListItemDecorations(20))
        recyclerView.setHasFixedSize(true)
    }

    var timer : Timer? = null
    private var searchMenuItem: MenuItem? = null
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchMenuItem = menu?.findItem(R.id.search)
        val searchView = searchMenuItem!!.actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.isSubmitButtonEnabled = false
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(contentToSearch: String): Boolean {
                onSearchTextChanged(contentToSearch)

                try {
                    if (timer == null) {
                        timer = Timer()
                    } else {
                        timer!!.cancel()
                        timer!!.purge()
                        timer = null
                        timer = Timer()
                    }

                    timer!!.schedule(3000) {
                        hideKeyboard()
                    }
                }catch (ex: Exception) {
                    ex.printStackTrace()
                }
                return true
            }
        })

        return true
    }

    fun onSearchTextChanged(contentToSearch: String){
        listMovies?.clear()
        if (contentToSearch.trim().isNotEmpty()){

            val realm = Realm.getDefaultInstance()

            listMovies = realm.copyFromRealm(realm.where(Movies::class.java)
                    .contains("title", contentToSearch, Case.INSENSITIVE)
                    .findAll())


            myCustomAdapter = MovieAdapter(applicationContext, (listMovies)!!)
            recyclerView.adapter = myCustomAdapter
            progressBar.visibility = View.GONE
        } else {
            movies.clear()
            getMoviesBD()
        }

    }

    private fun hideKeyboard() {
        runOnUiThread {
            val view = this.currentFocus
            if (view != null) {
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view.windowToken, 0)
            }
        }
    }
}
