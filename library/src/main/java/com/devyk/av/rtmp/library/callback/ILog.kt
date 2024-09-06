package com.devyk.av.rtmp.library.callback

/**
 * <pre>
 *     author  : devyk on 2020-07-15 21:26
 *     blog    : https://juejin.im/user/578259398ac2470061f3a3fb/posts
 *     github  : https://github.com/yangkun19921001
 *     mailbox : yang1001yk@gmail.com
 *     desc    : This is ILog
 * </pre>
 */
interface ILog {
    fun i(tag: String = javaClass.simpleName, info: String?);
    fun e(tag: String = javaClass.simpleName, info: String?);
    fun w(tag: String = javaClass.simpleName, info: String?);
    fun d(tag: String = javaClass.simpleName, info: String?);
}