package ademar.study.reddit.core.repository.datasource

import ademar.study.reddit.core.model.internal.PostResponse
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PostCloudRepository {

    @GET("/new/.json")
    fun getPosts(
            @Query("before") before: String? = null
    ): Observable<Response<PostResponse>>

}
