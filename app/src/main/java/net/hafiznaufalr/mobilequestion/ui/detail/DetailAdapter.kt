package net.hafiznaufalr.mobilequestion.ui.detail

import android.content.Context
import android.os.Build
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_reviews.view.*
import net.hafiznaufalr.mobilequestion.R
import net.hafiznaufalr.mobilequestion.model.Model.Review

class DetailAdapter(
    private val context: Context,
    private val data: List<Review>
) : RecyclerView.Adapter<DetailAdapter.MyHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int) =
        MyHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_reviews, p0, false))


    override fun getItemCount(): Int {
        return data.size
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val view = holder.itemView
        view.tv_author.text = data[position].author
        view.tv_review.text = data[position].content
        handleCard(view)
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    private fun handleCard(view: View) {
        view.btn_arrow.setOnClickListener {
            if (view.expandable_view.visibility == View.GONE) {
                TransitionManager.beginDelayedTransition(view.cv_review, AutoTransition())
                view.expandable_view.visibility = View.VISIBLE
                view.btn_arrow.setBackgroundResource(R.drawable.ic_keyboard_arrow_up_black_24dp)
            } else {
                TransitionManager.beginDelayedTransition(view.cv_review, AutoTransition())
                view.expandable_view.visibility = View.GONE
                view.btn_arrow.setBackgroundResource(R.drawable.ic_keyboard_arrow_down_black_24dp)
            }
        }
        view.setOnClickListener {
            if (view.expandable_view.visibility == View.GONE) {
                TransitionManager.beginDelayedTransition(view.cv_review, AutoTransition())
                view.expandable_view.visibility = View.VISIBLE
                view.btn_arrow.setBackgroundResource(R.drawable.ic_keyboard_arrow_up_black_24dp)
            } else {
                TransitionManager.beginDelayedTransition(view.cv_review, AutoTransition())
                view.expandable_view.visibility = View.GONE
                view.btn_arrow.setBackgroundResource(R.drawable.ic_keyboard_arrow_down_black_24dp)
            }
        }
    }


    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}