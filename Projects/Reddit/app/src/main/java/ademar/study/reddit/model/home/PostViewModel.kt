package ademar.study.reddit.model.home

data class PostViewModel(

        val title: String,
        val author: String,
        val thumbnail: String,
        val created: Long,
        val comments: Long,
        val downs: Long,
        val ups: Long

)
