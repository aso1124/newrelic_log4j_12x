package com.newrelic.fit.org.apache.log4j;

import java.util.HashMap;
import java.util.Map;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.NewField;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;

@Weave(type = MatchType.ExactClass, originalName = "org.apache.log4j.Logger")
public class Log4jLogger_Instrumentation {
	@NewField private static final String PARAM_KEY = "custom";
	@NewField private static final String PARAM_VALUE = "notice-handled-exception";
	
	@NewField private static final Map<String, String> PARAMS = new HashMap<String, String>();
	static {
		PARAMS.put(PARAM_KEY, PARAM_VALUE);
	}

	public void trace(Object message, Throwable t) {
		Weaver.callOriginal();
		NewRelic.noticeError(t, PARAMS);
	}
}
