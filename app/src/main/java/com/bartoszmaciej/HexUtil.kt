package com.bartoszmaciej

private const val HEX = "0123456789ABCDEF"
private val HEX_CHARS = HEX.toCharArray()

fun toHexString(data: ByteArray?): String = StringBuilder().apply {
  data?.let {
    for (octet in data){
      val firstIndex: Int = octet.toInt() and 0xF0 ushr 4
      val secondIndex = octet.toInt() and 0x0F

      data.joinToString (""){ "%02x".format(it)}

      append(HEX_CHARS[firstIndex])
      append(HEX_CHARS[secondIndex])
    }
  }
}.toString()