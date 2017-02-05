package ademar.study.reddit.core.repository.datasource

import ademar.study.reddit.core.model.internal.PostDetailResponse
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CommentCloudRepository {

    @GET("{link}/.json")
    fun getComments(
            @Path("link", encoded = true) link: String
    ): Observable<Response<List<PostDetailResponse>>>

}
