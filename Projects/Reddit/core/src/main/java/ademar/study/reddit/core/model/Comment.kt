package ademar.study.reddit.core.model

data class Comment(

        val author: String,
        val text: String,
        val downs: Long,
        val ups: Long,
        val comments: List<Comment>
)
