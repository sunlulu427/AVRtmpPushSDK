package com.devyk.av.rtmp.library.stream.sender

import com.devyk.av.rtmp.library.stream.PacketType

/**
 * <pre>
 *     author  : devyk on 2020-07-16 21:26
 *     blog    : https://juejin.im/user/578259398ac2470061f3a3fb/posts
 *     github  : https://github.com/yangkun19921001
 *     mailbox : yang1001yk@gmail.com
 *     desc    : This is Sender
 * </pre>
 */
interface Sender {
    fun onData(data: ByteArray, type: PacketType)
    fun onData(sps: ByteArray, pps: ByteArray, type: PacketType) {}
}