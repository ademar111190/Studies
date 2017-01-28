package ademar.study.reddit.core.interactor

import ademar.study.reddit.core.model.Post
import ademar.study.reddit.core.model.internal.Child
import ademar.study.reddit.core.model.internal.Data
import ademar.study.reddit.core.model.internal.PostResponse
import ademar.study.reddit.core.repository.PostRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(

        private val repository: PostRepository

) {

    fun execute(): Observable<Post> {
        return repository.getPosts()
                .map(PostResponse::data)
                .map(Data::children)
                .flatMapIterable { it }
                .map(Child::post)
    }

}
