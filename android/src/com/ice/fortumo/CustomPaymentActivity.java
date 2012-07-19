package com.ice.fortumo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.fortumo.android.PaymentActivity;
import com.fortumo.android.PaymentRequestBuilder;
import com.fortumo.android.PaymentResponse;

/**
 * 发送数据Activity
 * @author IceStar
 */
public class CustomPaymentActivity extends PaymentActivity {
	private static String TAG = "CustomPaymentActivity";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		Log.d(TAG, "onCreate");
		
		super.onCreate(savedInstanceState);

		try {
			// Get parameters from intent
			Intent intent = getIntent();
			boolean consumable = intent.getBooleanExtra("consumable", true);
			String displayString = intent.getStringExtra("displayString");
			String productName = intent.getStringExtra("productName");
			String serviceId = intent.getStringExtra("serviceId");
			String appSecret = intent.getStringExtra("appSecret");
			double creditsMultiplier = intent.getDoubleExtra("creditsMultiplier", 1.0);
			int icon = intent.getIntExtra("icon", -1);
			String sku = intent.getStringExtra("sku");

			// Forward request to Fortumo
			PaymentRequestBuilder builder = new PaymentRequestBuilder();
			builder.setConsumable(consumable);
			builder.setDisplayString(displayString);
			if (productName != null)
				builder.setProductName(productName);
			if (serviceId != null && appSecret != null)
				builder.setService(serviceId, appSecret);
			builder.setCreditsMultiplier(creditsMultiplier);
			if (icon != -1)
				builder.setIcon(icon);
			if (sku != null)
				builder.setSku(sku);
			makePayment(builder.build());
		}
		catch (Exception e) {
			Log.e(TAG, "Exception caught " + e.toString());
		}
	}
	
	/**
	 * Dump payment response as string
	 */
	private void sendResponse(String code, PaymentResponse response) {
		StringBuilder b = new StringBuilder();
		b.append("billingStatus=");
		b.append(response.getBillingStatus());
		b.append("&creditAmount=");
		b.append(response.getCreditAmount());
		b.append("&creditName=");
		b.append(response.getCreditName());
		b.append("&messageId=");
		b.append(response.getMessageId());
		b.append("&paymentCode=");
		b.append(response.getPaymentCode());
		b.append("&priceAmount=");
		b.append(response.getPriceAmount());
		b.append("&priceCurrency=");
		b.append(response.getPriceCurrency());
		b.append("&productName=");
		b.append(response.getProductName());
		b.append("&serviceId=");
		b.append(response.getServiceId());
		b.append("&sku=");
		b.append(response.getSku());
		b.append("&userId=");
		b.append(response.getUserId());

		Log.d(TAG, code + " " + b.toString());

		FortumoExtension.context.dispatchStatusEventAsync(code, b.toString());
		finish();
	}
	
	@Override
	protected void onPaymentCanceled(PaymentResponse response) {
		sendResponse("PAYMENT_CANCELLED", response);
	}

	@Override
	protected void onPaymentFailed(PaymentResponse response) {
		sendResponse("PAYMENT_FAILED", response);
	}

	@Override
	protected void onPaymentPending(PaymentResponse response) {
		sendResponse("PAYMENT_PENDING", response);
	}

	@Override
	protected void onPaymentSuccess(PaymentResponse response) {
		sendResponse("PAYMENT_SUCCESS", response);
	}
}