package ademar.study.template.view.home

import ademar.study.template.R
import ademar.study.template.ext.inflate
import ademar.study.template.model.HelloWorldViewModel
import ademar.study.template.view.base.BaseAdapter
import android.view.ViewGroup
import javax.inject.Inject

class HomeAdapter @Inject constructor() : BaseAdapter<HelloWorldViewModel, HomeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = HomeViewHolder(parent.inflate(R.layout.hello_item))

}
