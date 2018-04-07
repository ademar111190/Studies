package ademar.study.test.view.home

import ademar.study.test.R
import ademar.study.test.ext.inflate
import ademar.study.test.model.HelloWorldViewModel
import ademar.study.test.view.base.BaseAdapter
import android.view.ViewGroup
import javax.inject.Inject

class HomeAdapter @Inject constructor() : BaseAdapter<HelloWorldViewModel, HomeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = HomeViewHolder(parent.inflate(R.layout.hello_item))

}
