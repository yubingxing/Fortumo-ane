package
{
	import com.ice.fortumo.Fortumo;
	import com.ice.fortumo.FortumoEvent;
	
	import flash.display.Sprite;
	import flash.display.StageAlign;
	import flash.display.StageScaleMode;
	import flash.events.Event;
	import flash.events.MouseEvent;
	import flash.text.TextField;
	import flash.text.TextFieldAutoSize;
	import flash.text.TextFormat;
	import flash.ui.Multitouch;
	import flash.ui.MultitouchInputMode;
	
	public class Main extends Sprite
	{
		private var txt : TextField;
		private var btn : Sprite;
		private var status : TextField;
		
		public function Main() : void {
			stage.scaleMode = StageScaleMode.NO_SCALE;
			stage.align = StageAlign.TOP_LEFT;
			stage.addEventListener(Event.DEACTIVATE, deactivate);
			Multitouch.inputMode = MultitouchInputMode.TOUCH_POINT;
			
			// Add status field
			status = new TextField();
			status.defaultTextFormat = new TextFormat('Verdana', 12, 0x000000);
			status.text = "Press Pay Now button";
			status.autoSize = TextFieldAutoSize.LEFT;
			status.y = 70;
			status.selectable = false;
			addChild(status);
			
			// Add pay button
			txt = new TextField();
			txt.defaultTextFormat = new TextFormat('Verdana', 50, 0x000000);
			txt.text = "Pay Now!";
			txt.autoSize = TextFieldAutoSize.LEFT;
			txt.border = true;
			txt.selectable = false;			
			btn = new Sprite();
			btn.mouseChildren = false;
			btn.addChild(txt);
			btn.buttonMode = true;			
			addChild(btn);		
			btn.addEventListener(MouseEvent.CLICK, function(event : MouseEvent) : void 
			{
				// Update status
				status.text = "Processing...";
				
				// Make payment
				var f : Fortumo = new Fortumo();
				f.setService("6aebbb4508823c9e56520e75cb9f91d9", "312dd44b1f346c07a575ae85676a0c61");
				f.setConsumable(false);
				f.setDisplayString("This is a test purchase");
				f.setProductName("TestProduct");
				f.addEventListener(FortumoEvent.PAYMENT_SUCCESS, onStatusUpdate);
				f.addEventListener(FortumoEvent.PAYMENT_FAILED, onStatusUpdate);
				f.addEventListener(FortumoEvent.PAYMENT_CANCELLED, onStatusUpdate);
				f.addEventListener(FortumoEvent.PAYMENT_PENDING, onStatusUpdate);
				f.makePayment();			
			});
		}
		
		private function deactivate(e:Event):void 
		{
			// NOTE: Do not quit the application, Fortumo.makePayment will also deactivate the app and thus quit it if you leave this line in
			// auto-close
			//NativeApplication.nativeApplication.exit();
		}
		
		private function onStatusUpdate(e:FortumoEvent):void
		{
			// e.level will contain the information in the PaymentResponse object: billingStatus=...&creditAmount=...&creditName=...&messageId=...&paymentCode=...&priceAmount=...&priceCurrency=...&productName=...&serviceId=...&sku=...&userId=...
			
			if (e.type == "PAYMENT_CANCELLED")
			{
				status.text = "Cancelled: " + e.data;
				trace("Payment cancelled: " + e.data);
			}
			else if (e.type == "PAYMENT_FAILED")
			{
				status.text = "Failed: " + e.data;
				trace("Payment failed: " + e.data);
			}
			else if (e.type == "PAYMENT_PENDING")
			{
				status.text = "Pending: " + e.data;
				trace("Payment pending: " + e.data);
			}
			else if (e.type == "PAYMENT_SUCCESS")
			{
				status.text = "Success: " + e.data;
				trace("Payment success: " + e.data);
			}
		}
	}	
}