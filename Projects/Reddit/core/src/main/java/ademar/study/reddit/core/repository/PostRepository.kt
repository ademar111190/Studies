package ademar.study.reddit.core.repository

import ademar.study.reddit.core.ext.observeBody
import ademar.study.reddit.core.model.internal.PostResponse
import ademar.study.reddit.core.repository.datasource.PostCloudRepository
import io.reactivex.Observable
import retrofit2.Retrofit
import javax.inject.Inject

class PostRepository @Inject constructor(

        private val retrofit: Retrofit,
        private val cloud: PostCloudRepository

) {

    fun getPosts(after: String? = null): Observable<PostResponse> {
        return cloud.getPosts(after)
                .flatMap { retrofit.observeBody(it) }
    }

}
