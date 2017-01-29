package ademar.study.reddit.view.home

import ademar.study.reddit.R
import ademar.study.reddit.model.home.PostViewModel
import ademar.study.reddit.view.common.LoadViewHolder
import android.util.Log
import android.view.View
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.post_item.view.*

class PostViewHolder(view: View) : LoadViewHolder(view), View.OnClickListener {

    init {
        view.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        Log.d("Ademar", "clicked")
    }

    fun bind(viewModel: PostViewModel) {
        itemView.title.text = viewModel.title
        itemView.author.text = viewModel.author
        itemView.created.text = viewModel.created
        itemView.comments.text = viewModel.comments
        itemView.downs.text = viewModel.downs
        itemView.ups.text = viewModel.ups

        Picasso.with(itemView.context)
                .load(viewModel.thumbnail)
                .placeholder(R.drawable.ic_thumbnail_placeholder)
                .error(R.drawable.ic_thumbnail_placeholder)
                .into(itemView.image)
    }

}
