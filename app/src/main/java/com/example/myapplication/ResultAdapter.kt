package com.example.myapplication

import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ResultAdapter(val data: List<Result>) :
    RecyclerView.Adapter<ResultAdapter.ResultViewHolder>() {
    inner class ResultViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val coreNumber = view.findViewById<TextView>(R.id.textView2)
        val container = view.findViewById<LinearLayout>(R.id.container)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ResultViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_result, parent, false)
    )

    override fun onBindViewHolder(holder: ResultViewHolder, position: Int) {
        holder.coreNumber.text = data[position].coreNumber.toString()
        data[position].items.forEach {
            val frameLayout = FrameLayout(holder.container.context)
            frameLayout.setBackgroundColor(it.color)
            val params = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT)
            params.weight = it.size
            frameLayout.layoutParams = params
            val text = TextView(holder.container.context)
            text.layoutParams = FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            ).apply {
                gravity = Gravity.CENTER
            }
            if (it.size == it.totalSize) {
                text.text = it.size.toString()
            } else {
                text.text = it.size.toString() + "/" + it.totalSize.toString()
            }
            frameLayout.addView(text)
            holder.container.addView(frameLayout)
        }
    }

    override fun getItemCount() = data.size

    companion object Builder {
        fun build(
            recyclerView: RecyclerView,
            data: List<Result>
        ) = ResultAdapter(data).apply {
            recyclerView.layoutManager =
                LinearLayoutManager(recyclerView.context, LinearLayoutManager.VERTICAL, false)
            recyclerView.adapter = this
        }
    }

}