package ademar.study.reddit.view.comment

import ademar.study.reddit.R
import ademar.study.reddit.model.comment.CommentViewModel
import ademar.study.reddit.view.common.LoadViewHolder
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import kotlinx.android.synthetic.main.comment_item.view.*

class CommentViewHolder(view: View) : LoadViewHolder(view) {

    fun bind(viewModel: CommentViewModel) {
        itemView.text.text = viewModel.text
        itemView.author.text = viewModel.author
        itemView.downs.text = viewModel.downs
        itemView.ups.text = viewModel.ups

        val space = viewModel.level * itemView.context.resources.getDimension(R.dimen.comment_base_space).toInt()
        val params = itemView.space.layoutParams
        params.width = space
        itemView.space.layoutParams = params
        itemView.divider.visibility = if (space > 0) GONE else VISIBLE
    }

}
