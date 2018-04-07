package ademar.study.test.model

data class DetailViewModel(

        var focusedIndex: Int,
        var focused: HelloWorldViewModel,
        val items: List<HelloWorldViewModel>

)
