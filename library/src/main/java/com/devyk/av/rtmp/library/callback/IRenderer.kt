package com.devyk.av.rtmp.library.callback

import android.opengl.GLES20

/**
 * <pre>
 *     author  : devyk on 2020-07-06 11:05
 *     blog    : https://juejin.im/user/578259398ac2470061f3a3fb/posts
 *     github  : https://github.com/yangkun19921001
 *     mailbox : yang1001yk@gmail.com
 *     desc    : This is IRenderer
 *
 *
 *     OpenGL ES 坐标系：
 *     @see ![](https://devyk.oss-cn-qingdao.aliyuncs.com/blog/20200706174743.png)
 * </pre>
 */
interface IRenderer {
    /**
     * 当 Surface 创建的时候
     */
    fun onSurfaceCreate(width: Int, height: Int) {}

    /**
     * 当 surface 窗口改变的时候
     */
    fun onSurfaceChange(width: Int, height: Int) {
        GLES20.glViewport(0, 0, width, height)
    }

    /**
     * 绘制的时候
     */
    fun onDraw() {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT)
        GLES20.glClearColor(0f, 1f, 0f, 1f)
    }
}