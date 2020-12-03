package com.example.myapplication.algorithm

import com.example.myapplication.ItemResult
import com.example.myapplication.RandomColor
import com.example.myapplication.Result

class ShortProcessTimeAlgorithmImpl : Algorithm {


    override fun calculate(tasks: List<Float>, numberOfCore: Int): List<Result> {
        val sortedList = tasks.sortedByDescending { it }
        val map = hashMapOf<Int, Result>()

        for (i in 0 until numberOfCore) {
            map[i] = Result(i, 0F, 0F)
        }


        sortedList.forEach { item ->
            val coreId = map.toList().sortedBy {
                it.second.usedSize
            }[0].first

            map[coreId]!!.items.add(ItemResult(item, item,RandomColor.GetRandomColor()))
            map[coreId]!!.usedSize = map[coreId]!!.usedSize + item
            map[coreId]!!.totalSize = map[coreId]!!.totalSize + item
        }

        val data = arrayListOf<Result>()
        map.forEach {
            data.add(it.value)
        }
        return data
    }


}