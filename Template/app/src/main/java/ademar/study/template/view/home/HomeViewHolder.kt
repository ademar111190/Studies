package ademar.study.template.view.home

import ademar.study.template.model.HelloWorldViewModel
import ademar.study.template.view.base.BaseViewHolder
import android.view.View
import kotlinx.android.synthetic.main.hello_item.view.*

class HomeViewHolder(itemView: View) : BaseViewHolder<HelloWorldViewModel>(itemView) {

    override fun bind(viewModel: HelloWorldViewModel, listener: () -> Unit) {
        itemView.text.text = viewModel.message
        itemView.setOnClickListener { listener() }
    }

}
