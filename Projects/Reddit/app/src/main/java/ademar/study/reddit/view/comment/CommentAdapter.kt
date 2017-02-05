package ademar.study.reddit.view.comment

import ademar.study.reddit.R
import ademar.study.reddit.model.comment.CommentViewModel
import ademar.study.reddit.plataform.ext.inflate
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import javax.inject.Inject

class CommentAdapter @Inject constructor() : RecyclerView.Adapter<CommentViewHolder>() {

    var comments = arrayListOf<CommentViewModel>()

    override fun onBindViewHolder(holder: CommentViewHolder?, position: Int) {
        holder?.bind(comments[position])
    }

    override fun getItemCount(): Int {
        return comments.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CommentViewHolder {
        return CommentViewHolder(parent.inflate(R.layout.comment_item))
    }

}
