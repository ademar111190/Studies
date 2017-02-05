package ademar.study.reddit.core.interactor

import ademar.study.reddit.core.model.Comment
import ademar.study.reddit.core.model.mapper.CommentMapper
import ademar.study.reddit.core.repository.CommentRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetCommentsUseCase @Inject constructor(

        private val repository: CommentRepository,
        private val mapper: CommentMapper

) {

    fun getComments(postLink: String): Observable<Comment> {
        return repository.getComments(postLink)
                .flatMapIterable { it }
                .map { it.data }
                .filter { it.children != null }
                .map { it.children }
                .flatMapIterable { it }
                .filter { it.isComment() }
                .map { it.comment }
                .map { mapper.transform(it) }
    }

}
