package com.newrelic.fit.org.apache.log4j;

import java.util.HashMap;
import java.util.Map;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.NewField;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;

@Weave(type = MatchType.BaseClass, originalName = "org.apache.log4j.Category")
public class Log4JCategory_Instrumentation {
	@NewField private static final String PARAM_KEY = "custom";
	@NewField private static final String PARAM_VALUE = "notice-handled-exception";
	
	@NewField private static final Map<String, String> PARAMS = new HashMap<String, String>();
	static {
		PARAMS.put(PARAM_KEY, PARAM_VALUE);
	}
	
	public void debug(Object message, Throwable t) {
		Weaver.callOriginal();
		NewRelic.noticeError(t, PARAMS);
	}

	public void error(Object message) {
		Weaver.callOriginal();
		
		NewRelic.noticeError(message.toString(), PARAMS); 
	}
	
	public void error(Object message, Throwable t) {
		Weaver.callOriginal();
		NewRelic.noticeError(t, PARAMS);
	}

	public void fatal(Object message, Throwable t) {
		Weaver.callOriginal();
		NewRelic.noticeError(t, PARAMS);
	}
	
	public void info(Object message, Throwable t) {
		Weaver.callOriginal();
		NewRelic.noticeError(t, PARAMS);
	}
	
	public void warn(Object message, Throwable t) {
		Weaver.callOriginal();
		NewRelic.noticeError(t, PARAMS);
	}
}
