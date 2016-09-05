package tech.thdev.base.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import tech.thdev.base.model.BaseItem
import java.util.*

/**
 * Created by Tae-hwan on 7/21/16.
 */
abstract class BaseRecyclerAdapter<ITEM : BaseItem>(val context: Context) : RecyclerView.Adapter<BaseRecyclerViewHolder<ITEM>>() {

    private val itemList: MutableList<ITEM> = ArrayList()

    override fun onBindViewHolder(holder: BaseRecyclerViewHolder<ITEM>?, position: Int) {
        holder?.onViewHolder(getItem(position), position)
    }

    // ?.let { } - safe call == if (null != obj) { }
    override fun getItemViewType(position: Int): Int {
        getItem(position)?.let { return it.viewType } ?: return super.getItemViewType(position)
    }

    // Position >= or Position < 0 is null return
    fun getItem(position: Int) = itemList.getOrNull(position)

    fun removeItem(position: Int) {
        itemList.removeAt(position)
    }

    fun addItem(item: ITEM) {
        itemList.add(item)
    }

    fun clean() {
        itemList.clear()
    }

    override fun getItemCount(): Int = itemList.size
}