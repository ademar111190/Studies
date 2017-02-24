package ademar.study.test.view.base

import android.support.v7.widget.RecyclerView
import android.view.View

abstract class BaseViewHolder<in Data>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    abstract fun bind(viewModel: Data, listener: () -> Unit)

}
