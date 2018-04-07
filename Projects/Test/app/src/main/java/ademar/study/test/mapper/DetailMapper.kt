package ademar.study.test.mapper

import ademar.study.test.core.model.HelloWorld
import ademar.study.test.model.DetailViewModel
import javax.inject.Inject

class DetailMapper @Inject constructor(

        private val helloWorldMapper: HelloWorldMapper

) {

    fun transform(focused: HelloWorld, others: List<HelloWorld>) = DetailViewModel(
            others.indexOf(focused),
            helloWorldMapper.transform(focused),
            others.map {
                helloWorldMapper.transform(it)
            })

}
