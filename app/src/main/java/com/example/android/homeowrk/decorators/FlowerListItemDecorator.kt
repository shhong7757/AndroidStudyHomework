package com.example.android.homeowrk.decorators

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import androidx.recyclerview.widget.RecyclerView

class FlowerListItemDecorator: RecyclerView.ItemDecoration() {
    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)

        val paint = Paint()
        paint.color = Color.GRAY

        val left = 0f
        val right = parent.width.toFloat()

        for(i in 0 until parent.childCount) {
            val child = parent.getChildAt(i)
            val top = child.bottom.toFloat()
            val bottom = top + 1f

            c.drawRect(left, top, right, bottom, paint)
        }
    }
}