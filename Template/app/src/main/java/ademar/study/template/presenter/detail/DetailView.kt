package ademar.study.template.presenter.detail

import ademar.study.template.model.DetailViewModel
import ademar.study.template.presenter.LoadDataView

interface DetailView : LoadDataView {

    fun bindDetail(viewModel: DetailViewModel)

}
