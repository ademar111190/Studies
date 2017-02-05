package ademar.study.reddit.model.comment

data class CommentViewModel(

        val author: String,
        val text: String,
        val downs: String,
        val ups: String,
        val level: Int,
        val replies: List<CommentViewModel>

)
