package com.ice.fortumo;

import java.util.HashMap;
import java.util.Map;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.ice.fortumo.function.MakePaymentFunction;

public class FortumoContext extends FREContext {
	public static final String MAKE_PAYMENT = "makepayment";
	
	public FortumoContext(String PAYMENT_BROADCAST_PERMISSION){
	}
	
	@Override
	public void dispose() {
	}

	@Override
	public Map<String, FREFunction> getFunctions() {
		Map<String, FREFunction> functionMap = new HashMap<String, FREFunction>();
		functionMap.put(MAKE_PAYMENT, new MakePaymentFunction());
		return functionMap;
	}
}
