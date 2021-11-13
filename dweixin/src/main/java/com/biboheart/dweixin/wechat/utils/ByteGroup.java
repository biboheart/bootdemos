package com.biboheart.dweixin.wechat.utils;

import java.util.ArrayList;

class ByteGroup {
	ArrayList<Byte> byteContainer = new ArrayList<>();

	/**
	 * 获取字节数组
	 * @return 字节列表转换后的字节数组
	 */
	public byte[] toBytes() {
		byte[] bytes = new byte[byteContainer.size()];
		for (int i = 0; i < byteContainer.size(); i++) {
			bytes[i] = byteContainer.get(i);
		}
		return bytes;
	}

	/**
	 * 添加字节到列表中
	 * @param bytes 字符数组
	 */
	public void addBytes(byte[] bytes) {
		for (byte b : bytes) {
			byteContainer.add(b);
		}
	}

	/**
	 * 字节数
	 * @return 整数，实例中包含的字节数量
	 */
	public int size() {
		return byteContainer.size();
	}
}
