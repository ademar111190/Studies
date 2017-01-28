package ademar.study.reddit.core.repository.datasource

import ademar.study.reddit.core.model.internal.PostResponse
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET

interface PostCloudRepository {

    @GET("/new")
    fun getPosts(): Observable<Response<PostResponse>>

}
