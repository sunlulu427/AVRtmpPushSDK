package com.devyk.av.rtmp.library.controller

import android.content.Context
import android.media.MediaCodec
import android.media.MediaFormat
import com.devyk.av.rtmp.library.callback.IController
import com.devyk.av.rtmp.library.callback.OnVideoEncodeListener
import com.devyk.av.rtmp.library.camera.CameraRecorder
import com.devyk.av.rtmp.library.camera.Watermark
import com.devyk.av.rtmp.library.config.VideoConfiguration
import java.nio.ByteBuffer
import javax.microedition.khronos.egl.EGLContext

/**
 * <pre>
 *     author  : devyk on 2020-07-15 22:07
 *     blog    : https://juejin.im/user/578259398ac2470061f3a3fb/posts
 *     github  : https://github.com/yangkun19921001
 *     mailbox : yang1001yk@gmail.com
 *     desc    : This is VideoController Camera 预览和 H264 编码的控制
 * </pre>
 */

class VideoController(
    context: Context,
    textureId: Int,
    eglContext: EGLContext?,
    videoConfiguration: VideoConfiguration
) : IController, OnVideoEncodeListener {


    private var mCameraVideoController: CameraRecorder? = null

    private var mListener: IController.OnVideoDataListener? = null

    init {
        mCameraVideoController = CameraRecorder(context, textureId, eglContext)
        mCameraVideoController?.prepare(videoConfiguration)
        mCameraVideoController?.setOnVideoEncodeListener(this)
    }

    override fun start() {
        mCameraVideoController?.start()
    }

    override fun stop() {
        mCameraVideoController?.stop()
    }

    override fun pause() {
        mCameraVideoController?.pause()
    }

    override fun resume() {
        mCameraVideoController?.resume()
    }


    override fun onVideoEncode(bb: ByteBuffer?, bi: MediaCodec.BufferInfo?) {
        mListener?.onVideoData(bb, bi)
    }

    override fun onVideoOutformat(outputFormat: MediaFormat?) {
        mListener?.onVideoOutformat(outputFormat)
    }


    override fun setVideoBps(bps: Int) {
        mCameraVideoController?.setEncodeBps(bps)
    }

    override fun setVideoDataListener(videoDataListener: IController.OnVideoDataListener) {
        mListener = videoDataListener
    }

    fun setWatermark(watermark: Watermark) {
        mCameraVideoController?.setWatermark(watermark)
    }
}
