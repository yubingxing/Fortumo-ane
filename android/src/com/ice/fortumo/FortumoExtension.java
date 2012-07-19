package com.ice.fortumo;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREExtension;

public class FortumoExtension implements FREExtension {
	public static FREContext context;

	@Override
	public FREContext createContext(String PAYMENT_BROADCAST_PERMISSION) {
		if (context == null)
			context = new FortumoContext(PAYMENT_BROADCAST_PERMISSION);
		return context;
	}

	@Override
	public void dispose() {
		context = null;
	}

	@Override
	public void initialize() {
	}
}
