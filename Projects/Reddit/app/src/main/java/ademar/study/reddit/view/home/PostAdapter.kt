package ademar.study.reddit.view.home

import ademar.study.reddit.R
import ademar.study.reddit.model.home.PostViewModel
import ademar.study.reddit.plataform.ext.inflate
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import javax.inject.Inject

class PostAdapter @Inject constructor() : RecyclerView.Adapter<PostViewHolder>() {

    var listener: ((String) -> Unit)? = null
    var posts = arrayListOf<PostViewModel>()

    override fun onBindViewHolder(holder: PostViewHolder?, position: Int) {
        holder?.bind(posts[position])
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): PostViewHolder {
        return PostViewHolder(parent.inflate(R.layout.post_item)) { link ->
            listener?.let { listener ->
                listener(link)
            }
        }
    }

}
