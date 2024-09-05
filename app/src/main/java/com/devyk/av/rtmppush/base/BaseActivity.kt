package com.devyk.av.rtmppush.base

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Chronometer
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.devyk.av.rtmppush.R
import com.devyk.av.rtmppush.SPUtils
import com.tbruyelle.rxpermissions2.RxPermissions

/**
 * <pre>
 *     author  : devyk on 2020-05-24 23:40
 *     blog    : https://juejin.im/user/578259398ac2470061f3a3fb/posts
 *     github  : https://github.com/yangkun19921001
 *     mailbox : yang1001yk@gmail.com
 *     desc    : This is BaseActivity
 * </pre>
 */

abstract class BaseActivity<T> : AppCompatActivity() {
    protected val TAG = javaClass.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onContentViewBefore()

        if (getLayoutId() is Int) {
            setContentView(getLayoutId() as Int)
        } else if (getLayoutId() is View) {
            setContentView(getLayoutId() as View)
        }
        checkPermission()
        init()
        initListener()
        initData()
    }

    /**
     * 在 setContentView 之前需要做的初始化
     */
    protected open fun onContentViewBefore() {

    }

    abstract fun initListener()

    abstract fun initData()

    abstract fun init()

    abstract fun getLayoutId(): T


    protected fun setNotTitleBar() {
        val window = window
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS or WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
        window.getDecorView().setSystemUiVisibility(
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        )
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.setStatusBarColor(Color.TRANSPARENT)
        window.setNavigationBarColor(Color.TRANSPARENT)
        //去掉标题栏
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
    }

    /**
     * 检查权限
     */
    @SuppressLint("CheckResult")
    protected fun checkPermission() {
        if (SPUtils.getInstance().getBoolean(getString(R.string.OPEN_PERMISSIONS))) {
            return
        }
        val rxPermissions = RxPermissions(this)
        rxPermissions.requestEach(
            android.Manifest.permission.READ_EXTERNAL_STORAGE,
            android.Manifest.permission.RECORD_AUDIO,
            android.Manifest.permission.CAMERA,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE
        ).subscribe {
            if (it.granted) {
                SPUtils.getInstance().put(getString(R.string.OPEN_PERMISSIONS), true)
                Toast.makeText(this, getString(R.string.GET_PERMISSION_ERROR), Toast.LENGTH_SHORT)
                    .show()
            } else if (it.shouldShowRequestPermissionRationale) {
                Toast.makeText(this, getString(R.string.GET_PERMISSION_ERROR), Toast.LENGTH_SHORT)
                    .show()
                SPUtils.getInstance().put(getString(R.string.OPEN_PERMISSIONS), false)
            }
        }
    }

    fun startTime(timer: Chronometer) {
        val hour = ((SystemClock.elapsedRealtime() - timer.base) / 1000 / 60).toInt()
        timer.setFormat("0${hour}:%s")
        timer.start()
    }

    fun cleanTime(timer: Chronometer) {
        timer.setBase(SystemClock.elapsedRealtime())
        timer.stop()
    }
}