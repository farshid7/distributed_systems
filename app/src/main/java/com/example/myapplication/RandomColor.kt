package com.example.myapplication

import android.graphics.Color
import java.util.*

class RandomColor {
    companion object {
        fun GetRandomColor(): Int {
            val rnd = Random()
            return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
        }
    }
}