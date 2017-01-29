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

    private var lastReference: String? = null

    fun currentPage(): Observable<Post> {
        return repository.getPosts()
                .map(PostResponse::data)
                .map(Data::children)
                .flatMapIterable { it }
                .map(Child::post)
                .doOnNext { lastReference = it.reference }
    }

    fun previousPage(): Observable<Post> {
        return repository.getPosts(lastReference)
                .map(PostResponse::data)
                .map(Data::children)
                .flatMapIterable { it }
                .map(Child::post)
                .doOnNext { lastReference = it.reference }
    }

}
