package com.biboheart.dwechat.wechat.config;

import com.biboheart.dwechat.wechat.model.PayResult;
import com.biboheart.dwechat.wechat.model.QueryOrderResult;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class XStreamWechatMessageConfiguration {
	
	@Bean(name = "payResultXstream")
	public XStream payResultXstream() {
		XStream xs = new XStream(new DomDriver());
		XStream.setupDefaultSecurity(xs);
		xs.allowTypes(new Class[]{PayResult.class});
		xs.processAnnotations(PayResult.class);
		return xs;
	}
	
	@Bean(name = "queryOrderResult")
	public XStream queryOrderResult() {
		XStream xs = new XStream(new DomDriver());
		XStream.setupDefaultSecurity(xs);
		xs.allowTypes(new Class[]{QueryOrderResult.class});
		xs.processAnnotations(QueryOrderResult.class);
		return xs;
	}
}
