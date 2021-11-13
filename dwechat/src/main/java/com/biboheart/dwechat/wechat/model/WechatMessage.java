package com.biboheart.dwechat.wechat.model;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.io.xml.DomDriver;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@XStreamAlias("xml")
public class WechatMessage implements Serializable {
	private static final long serialVersionUID = -642068279107886194L;
	@XStreamAlias("AppId")
	private String appid; // 微信appid
	@XStreamAlias("ToUserName")
	private String toUserName; // 开发者微信号
	@XStreamAlias("FromUserName")
	private String fromUserName; // 发送方帐号（一个OpenID）
	@XStreamAlias("CreateTime")
	private Long createTime; // 消息创建时间
	@XStreamAlias("MsgType")
	private String msgType; // 消息类型，event
	@XStreamAlias("Content")
	private String content; // 文本消息内容
	@XStreamAlias("MsgId")
	private Long msgId; // 消息id，64位整型
	@XStreamAlias("MsgID")
	private Long msgIdSecond; // 消息id，64位整型
	@XStreamAlias("Event")
	private String event; // 事件类型，subscribe(订阅)、unsubscribe(取消订阅)
	@XStreamAlias("EventKey")
	private String eventKey; // 事件主键
	@XStreamAlias("Ticket")
	private String ticket;
	@XStreamAlias("PicUrl")
	private String pic;
	@XStreamAlias("MediaId")
	private String mediaId;
	@XStreamAlias("Status")
	private String status;
	@XStreamAlias("Latitude")
	private String latitude;
	@XStreamAlias("Longitude")
	private String longitude;
	@XStreamAlias("Precision")
	private String precision;
	@XStreamAlias("MenuId")
	private String menuId;
	
	public static WechatMessage fromXML(String xml) {
		XStream xs = new XStream(new DomDriver());
		XStream.setupDefaultSecurity(xs);
		xs.allowTypes(new Class[]{WechatMessage.class});
		xs.processAnnotations(WechatMessage.class);
		return (WechatMessage) xs.fromXML(xml);
	}
}
