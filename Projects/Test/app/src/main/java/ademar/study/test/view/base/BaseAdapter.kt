package ademar.study.test.view.base

import android.support.v7.widget.RecyclerView
import java.util.*

abstract class BaseAdapter<Data, ViewHolder : BaseViewHolder<Data>> : RecyclerView.Adapter<ViewHolder>() {

    var listener: (Data) -> Unit = {}

    private val data: ArrayList<Data> = arrayListOf()

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val item = data[position]
        holder?.bind(data[position]) {
            listener(item)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun addItem(item: Data) {
        data.add(item)
        notifyItemInserted(data.size - 1)
    }

    fun clear() {
        data.clear()
        notifyDataSetChanged()
    }

}
