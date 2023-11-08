package com.example.pruebamovie.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.pruebamovie.R
import com.example.pruebamovie.models.Result
import com.example.pruebamovie.ui.activity.DetailsAcvtivity
import com.example.pruebamovie.utils.Constants

class AdapterMovies(val context: Context, var list: List<Result>):RecyclerView.Adapter<AdapterMovies.ViewHolder>(){

    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val cvPeliculas = itemView.findViewById(R.id.card_view) as CardView
        val ivImage = itemView.findViewById(R.id.cv_iv_movie_poster) as ImageView
        var tvTitle = itemView.findViewById(R.id.cv_movie_title) as TextView
        val tvDate = itemView.findViewById(R.id.cv_movie_release_date) as TextView

        fun setListener(list: Result,context: Context) {
            itemView.setOnClickListener{
                val intent = Intent(context, DetailsAcvtivity::class.java)
                intent.putExtra("id", list?.id)
                context.startActivity(intent)
            }
        }
    }
    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.movie_list_item,parent,false)
        return ViewHolder(vista)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pelicula = list[position]
        Glide.with(context).load("${Constants.BASE_URL_IMAGE}${pelicula.posterPath}")
            .apply(RequestOptions().override(Constants.IMAGE_ANCHO,Constants.IMAGE_ALTO))
            .into(holder.ivImage)
        holder.tvTitle.text = pelicula.title
        holder.tvDate.text = pelicula.releaseDate
        holder.setListener(pelicula,context)
    }

}