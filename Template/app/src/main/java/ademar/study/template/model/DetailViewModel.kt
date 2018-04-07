package ademar.study.template.model

data class DetailViewModel(

        var focusedIndex: Int,
        var focused: HelloWorldViewModel,
        val items: List<HelloWorldViewModel>

)
