package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ProcessAdapter : RecyclerView.Adapter<ProcessAdapter.ProcessViewHolder>() {

    inner class ProcessViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val text = view.findViewById<TextView>(R.id.text)
    }

    private val data = arrayListOf<Float>()

    fun addItem(item: Float) {
        data.add(item)
        this.notifyDataSetChanged()
    }
    fun getData()=data

    fun clear() {
        data.clear()
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ProcessViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_process, parent, false)
        )

    override fun onBindViewHolder(holder: ProcessViewHolder, position: Int) {
        holder.text.text = data[position].toString()
    }

    override fun getItemCount() = data.size

    companion object Builder {
        fun build(
            recyclerView: RecyclerView
        ) =
            ProcessAdapter().apply {
                recyclerView.layoutManager =
                    LinearLayoutManager(recyclerView.context, LinearLayoutManager.VERTICAL, false)
                recyclerView.adapter = this
            }
    }


}