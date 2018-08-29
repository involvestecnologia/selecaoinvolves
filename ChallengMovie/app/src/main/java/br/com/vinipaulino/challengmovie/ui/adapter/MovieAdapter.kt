package br.com.vinipaulino.challengmovie.ui.adapter

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.anestech.axcalc.AppConstants
import br.com.vinipaulino.challengmovie.R
import br.com.vinipaulino.challengmovie.model.Movies
import br.com.vinipaulino.challengmovie.ui.activity.ResumeMovieActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.inflate_list.view.*
import kotlinx.android.synthetic.main.layout_item_movies.view.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import java.text.SimpleDateFormat

class MovieAdapter(var context: Context, var list: List<Movies>) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var v = LayoutInflater.from(context).inflate(R.layout.layout_item_movies, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.tvName.text = list[position].title

        Picasso.with(context)
                .load(AppConstants.IMG_BASE_URL + list[position].backdrop_path)
                .resize(300, 390)
                .centerCrop()
                .into(holder.imgMovie)

        holder.cardView.setOnClickListener {
            val intent = Intent(context, ResumeMovieActivity::class.java)
            intent.putExtra("movie_id", list[position].id.toString())
            context.startActivity(intent)
        }

        val dataEmUmFormato = list[position].release_date
        val formato = SimpleDateFormat("yyyy-MM-dd")
        val data = formato.parse(dataEmUmFormato)
        formato.applyPattern("dd/MM/yyyy")
        val dataFormatada = formato.format(data)
        holder.tvDate.text =  dataFormatada

    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName = itemView.txt_title_movies
        val tvDate = itemView.txt_date_realease_movie
        val imgMovie = itemView.img_cover_movie
        val cardView = itemView.cardViewMovie
    }
}