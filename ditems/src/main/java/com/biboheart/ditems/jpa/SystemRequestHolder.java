package com.biboheart.ditems.jpa;

public class SystemRequestHolder {
	private final static ThreadLocal<SystemRequest> systemRequesthreadLocal = new ThreadLocal<>();

	static void initRequestHolder(SystemRequest systemRequest) {
		systemRequesthreadLocal.set(systemRequest);
	}

	static SystemRequest getSystemRequest() {
		return systemRequesthreadLocal.get();
	}

	static void remove() {
		systemRequesthreadLocal.remove();
	}
}
