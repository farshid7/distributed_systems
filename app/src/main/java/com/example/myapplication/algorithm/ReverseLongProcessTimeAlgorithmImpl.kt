package com.example.myapplication.algorithm

import com.example.myapplication.ItemResult
import com.example.myapplication.RandomColor
import com.example.myapplication.Result

class ReverseLongProcessTimeAlgorithmImpl : Algorithm {

    override fun calculate(tasks: List<Float>, numberOfCore: Int): List<Result> {
        val sortedList = tasks.sortedBy { it }
        val map = hashMapOf<Int, Result>()

        for (i in 0 until numberOfCore) {
            map[i] = Result(i, 0F, 0F)
        }


        sortedList.forEach { item ->
            val coreId = map.toList().sortedBy {
                it.second.usedSize
            }[0].first

            map[coreId]!!.items.add(ItemResult(item, item,color = RandomColor.GetRandomColor()))
            map[coreId]!!.usedSize = map[coreId]!!.usedSize + item
            map[coreId]!!.totalSize = map[coreId]!!.totalSize + item
        }

        val data = arrayListOf<Result>()
        map.forEach {
            val item = it.value
            val items:ArrayList<ItemResult> = arrayListOf()
            item.items.asReversed().forEach {
                items.add(it)
            }
            item.items=items
            data.add(it.value)
        }
        return data
    }


}