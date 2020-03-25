package net.hafiznaufalr.mobilequestion.ui.favorite

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_movie.view.*
import net.hafiznaufalr.mobilequestion.R
import net.hafiznaufalr.mobilequestion.model.Model
import net.hafiznaufalr.mobilequestion.ui.detail.DetailActivity
import net.hafiznaufalr.mobilequestion.util.Constant

class FavoriteAdapter(private val context: Context,
                  private val data : List<Model.Movie>
): RecyclerView.Adapter<FavoriteAdapter.MyHolder>(){

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int)
            = MyHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_movie,p0,false))


    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val view = holder.itemView
        view.tv_title.text = data[position].title
        view.tv_release_date.text = data[position].releaseDate
        view.tv_overview.text = data [position].overview
        Glide.with(context).load(Constant.BASE_IMAGE_URL + data[position].posterPath).into(view.iv_poster)

        view.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("idMovie", data[position].id)
            context.startActivity(intent)
        }

    }

    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


}