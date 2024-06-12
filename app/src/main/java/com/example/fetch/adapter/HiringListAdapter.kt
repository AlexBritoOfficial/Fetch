package com.example.fetch.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fetch.data.Item
import com.example.fetch.databinding.HireCardBinding

class HiringListAdapter:RecyclerView.Adapter<HiringListAdapter.ViewHolder>() {

    private var hiringList: List<Item>? = null
    private var groupedItems: Map<Int, List<Item>> = emptyMap()


    class ViewHolder(private val hireCardBinding: HireCardBinding) :
        RecyclerView.ViewHolder(hireCardBinding.root) {

        fun bind(item: Item) {

            hireCardBinding.hireId.text = "ID: " + item.id.toString()
            hireCardBinding.hireListId.text = "List ID: " + item.listId.toString()
            hireCardBinding.name.text = "Name: " + item.name
        }
    }

    fun setHiringList(items: List<Item>?) {
        val filteredItems = items?.filter { !it.name.isNullOrBlank() }

        if (filteredItems != null) {
            groupedItems = filteredItems.groupBy { it.listId }
                .toSortedMap()
                .mapValues { (_, items) -> items.sortedBy { it.name } }
        }

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val hireCardBinding =
            HireCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(hireCardBinding)
    }

    override fun getItemCount(): Int {
        return groupedItems.values.flatten().size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        groupedItems.values.flatten().getOrNull(position)?.let { item ->
            holder.bind(item)
        }
    }
}