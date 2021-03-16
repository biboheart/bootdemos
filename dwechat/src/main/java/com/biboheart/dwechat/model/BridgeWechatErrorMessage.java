package com.biboheart.dwechat.model;

import lombok.Data;

@Data
public class BridgeWechatErrorMessage {
	public final static int EncodeUrlError = 1001;
	public final static int SaveCodeCacheError = 1002;
	public final static int WechatCodeResultErr = 1003;
	public final static int NoCodeCacheErr = 1004;
	public final static int SaveCodeResultError = 1005;
	public final static int NoClientError = 1006;
	public final static int LoadOpenidError = 1007;
	public final static int NoneRedirectUriError = 1008;

	private Integer code;
	
	public static String getMessage(Integer code) {
		switch (code) {
		case EncodeUrlError:
			return "地址转码出错";
		case SaveCodeCacheError:
			return "创建CODE接收器出错了";
		case WechatCodeResultErr:
			return "微信返回CODE结果出错";
		case NoCodeCacheErr:
			return "没有对应的CODE接收器";
		case SaveCodeResultError:
			return "保存CODE结果出错";
		case NoClientError:
			return "没有找到对应客户端";
		case LoadOpenidError:
			return "没有获取到微信用户编号";
		case NoneRedirectUriError:
			return "需要提供跳转地址";
		default:
			return null; // cannot be
		}
	}
}
