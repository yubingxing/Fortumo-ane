package com.fortumo.android.testapp;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.fortumo.android.PaymentActivity;
import com.fortumo.android.PaymentRequestBuilder;
import com.fortumo.android.PaymentResponse;

public class TestActivity extends PaymentActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Button payButton = (Button) findViewById(R.id.payButton);
		payButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				PaymentRequestBuilder builder = new PaymentRequestBuilder();
				builder.setConsumable(true);
//				builder.setDisplayString("You are about to buy 100 crystals");
				builder.setDisplayString("You are about to buy gold coins.");
				builder.setProductName("Star War");
				makePayment(builder.build());
			}
		});
	}

	@Override
	protected void onPaymentCanceled(PaymentResponse response) {
		Toast.makeText(this, "Payment canceled by user", Toast.LENGTH_SHORT).show();
	}

	@Override
	protected void onPaymentFailed(PaymentResponse response) {
		Toast.makeText(this, "Payment failed", Toast.LENGTH_SHORT).show();
	}

	@Override
	protected void onPaymentPending(PaymentResponse response) {
		Toast.makeText(this, "Payment not confirmed", Toast.LENGTH_SHORT).show();
	}

	@Override
	protected void onPaymentSuccess(PaymentResponse response) {
		Toast.makeText(this, "Payment received", Toast.LENGTH_SHORT).show();
	}
}