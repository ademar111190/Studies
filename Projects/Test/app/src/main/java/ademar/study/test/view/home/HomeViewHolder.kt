package ademar.study.test.view.home

import ademar.study.test.model.HelloWorldViewModel
import ademar.study.test.view.base.BaseViewHolder
import android.view.View
import kotlinx.android.synthetic.main.hello_item.view.*

class HomeViewHolder(itemView: View) : BaseViewHolder<HelloWorldViewModel>(itemView) {

    override fun bind(viewModel: HelloWorldViewModel, listener: () -> Unit) {
        itemView.text.text = viewModel.message
        itemView.setOnClickListener { listener() }
    }

}
