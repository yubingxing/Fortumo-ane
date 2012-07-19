package com.ice.fortumo.function;

import android.content.Intent;
import android.util.Log;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;
import com.ice.fortumo.CustomPaymentActivity;

public class MakePaymentFunction implements FREFunction {
	private static String TAG = "MakePaymentFunction";

	@Override
	public FREObject call(FREContext context, FREObject[] args) {
		Log.d(TAG, "call");

		if (args.length != 8) {
			Log.e(TAG, "Invalid number of args");
			return null;
		}

		try {
			// Get parameters
			boolean consumable = args[0].getAsBool();
			String displayString = args[1] != null ? args[1].getAsString()
					: null;
			String productName = args[2] != null ? args[2].getAsString() : null;
			String serviceId = args[3] != null ? args[3].getAsString() : null;
			String appSecret = args[4] != null ? args[4].getAsString() : null;
			double creditsMultiplier = args[5].getAsDouble();
			int icon = args[6].getAsInt();
			String sku = args[7] != null ? args[7].getAsString() : null;

			// Start CustomPaymentActivity
			Intent intent = new Intent(context.getActivity(),
					CustomPaymentActivity.class);
			intent.putExtra("consumable", consumable);
			intent.putExtra("displayString", displayString);
			intent.putExtra("productName", productName);
			intent.putExtra("serviceId", serviceId);
			intent.putExtra("appSecret", appSecret);
			intent.putExtra("creditsMultiplier", creditsMultiplier);
			intent.putExtra("icon", icon);
			intent.putExtra("sku", sku);
			context.getActivity().startActivity(intent);
		} catch (Exception e) {
			Log.e(TAG, "Exception caught " + e.toString());
		}

		return null;
	}

}
