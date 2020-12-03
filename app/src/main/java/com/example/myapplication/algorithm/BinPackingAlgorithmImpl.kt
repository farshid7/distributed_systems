package com.example.myapplication.algorithm

import com.example.myapplication.ItemResult
import com.example.myapplication.RandomColor
import com.example.myapplication.Result
import java.math.RoundingMode

class BinPackingAlgorithmImpl : Algorithm {

    override fun calculate(tasks: List<Float>, numberOfCore: Int): List<Result> {
        var currentCore = 0
        val map = hashMapOf<Int, Result>()
        val maxTask = tasks.maxOrNull() ?: 0F
        val average =
            (tasks.sum() / numberOfCore).toBigDecimal().setScale(2, RoundingMode.UP).toFloat()
        val totalSize = if (maxTask > average) {
            maxTask
        } else {
            average
        }
        for (i in 0 until numberOfCore) {
            map[i] = Result(i, totalSize, 0F)
        }
        tasks.forEach {
            if (map[currentCore]!!.totalSize - map[currentCore]!!.usedSize >= it) {
                map[currentCore]!!.items.add(ItemResult(it, it,color = RandomColor.GetRandomColor()))
                map[currentCore]!!.usedSize = map[currentCore]!!.usedSize + it
                if (map[currentCore]!!.totalSize == map[currentCore]!!.usedSize) {
                    currentCore += 1
                }
            } else {
                val color = RandomColor.GetRandomColor()
                val canBeAddSize = map[currentCore]!!.totalSize - map[currentCore]!!.usedSize
                map[currentCore]!!.items.add(ItemResult(it, canBeAddSize,color))
                map[currentCore]!!.usedSize = map[currentCore]!!.totalSize
                val remain = it - canBeAddSize
                currentCore += 1
                map[currentCore]!!.items.add(ItemResult(it, remain,color))
                map[currentCore]!!.usedSize = map[currentCore]!!.usedSize + remain
            }
        }
        val data = arrayListOf<Result>()
        map.forEach {
            data.add(it.value)
        }
        return data
    }
}