package ademar.study.reddit.mapper.home

import ademar.study.reddit.core.model.Post
import ademar.study.reddit.model.home.PostViewModel
import javax.inject.Inject

class PostMapper @Inject constructor() {

    fun transform(post: Post): PostViewModel {
        return PostViewModel(post.title, post.author, post.thumbnail, post.created, post.comments, post.downs, post.ups)
    }

}
