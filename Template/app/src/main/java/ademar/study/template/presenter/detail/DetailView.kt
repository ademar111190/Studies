package ademar.study.template.presenter.detail

import ademar.study.template.model.DetailViewModel
import ademar.study.template.model.HelloWorldViewModel
import ademar.study.template.presenter.LoadDataView

interface DetailView : LoadDataView {

    fun bindFocus(viewModel: HelloWorldViewModel)

    fun bindDetail(viewModel: DetailViewModel)

}
