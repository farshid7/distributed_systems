package com.example.myapplication.algorithm

import com.example.myapplication.Result

interface Algorithm {
    fun calculate(tasks: List<Float>, numberOfCore: Int): List<Result>
}