package edu.miu.pm.onlineshopping.email;

import java.util.Map;

public interface MessageContentBuilder {

	String buildMessage(String templateName, Map<String, Object> datas);
	
}
