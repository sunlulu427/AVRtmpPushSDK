package com.devyk.av.rtmp.library.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView


/**
 * <pre>
 *     author  : devyk on 2020-07-09 21:38
 *     blog    : https://juejin.im/user/578259398ac2470061f3a3fb/posts
 *     github  : https://github.com/yangkun19921001
 *     mailbox : yang1001yk@gmail.com
 *     desc    : This is BitmapUtils
 * </pre>
 */
object BitmapUtils {
    /**
     * 将文字 生成 文字图片 生成显示编码的Bitmap,目前这个方法是可用的
     * 
     * @param contents
     * @param context
     * @return
     */
    fun creatBitmap(contents: String, context: Context, testSize: Int, testColor: Int, bg: Int): Bitmap {
        val scale = context.getResources().getDisplayMetrics().scaledDensity
        val tv = TextView(context)
        val layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        tv.layoutParams = layoutParams
        tv.text = contents
        tv.textSize = scale * testSize
        tv.gravity = Gravity.CENTER_HORIZONTAL
        tv.setDrawingCacheEnabled(true)
        tv.setTextColor(testColor)
        tv.measure(
            View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
            View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        )
        tv.layout(0, 0, tv.getMeasuredWidth(), tv.getMeasuredHeight())
        tv.setBackgroundColor(bg)
        tv.buildDrawingCache()
        return tv.getDrawingCache()
    }

    fun changeBitmapSize(context: Context, src: Int, width: Float, height: Float): Bitmap {
        val bitmap = BitmapFactory.decodeResource(context.applicationContext.resources, src)
        return getBitmap(bitmap,width, height)
    }

    fun getBitmap(bitmap: Bitmap,width: Float,height: Float): Bitmap {
        var bitmap1 = bitmap
        val oldWidth = bitmap1.width
        val oldHeight = bitmap1.height
        //设置想要的大小

        //计算压缩的比率
        val scaleWidth = (width) / oldWidth
        val scaleHeight = (height) / oldHeight

        //获取想要缩放的matrix
        val matrix = Matrix()
        matrix.postScale(scaleWidth, scaleHeight)
        //获取新的bitmap
        bitmap1 = Bitmap.createBitmap(bitmap, 0, 0, oldWidth, oldHeight, matrix, true)
        return bitmap1
    }
}