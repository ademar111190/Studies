package ademar.study.reddit.core.repository

import ademar.study.reddit.core.ext.observeBody
import ademar.study.reddit.core.model.internal.PostDetailResponse
import ademar.study.reddit.core.repository.datasource.CommentCloudRepository
import io.reactivex.Observable
import retrofit2.Retrofit
import javax.inject.Inject

class CommentRepository @Inject constructor(

        private val retrofit: Retrofit,
        private val cloud: CommentCloudRepository

) {

    fun getComments(postLink: String): Observable<List<PostDetailResponse>> {
        return cloud.getComments(postLink)
                .flatMap { retrofit.observeBody(it) }
    }

}
