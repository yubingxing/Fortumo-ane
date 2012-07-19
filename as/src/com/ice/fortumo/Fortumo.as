package com.ice.fortumo
{
	import flash.events.EventDispatcher;
	import flash.events.IEventDispatcher;
	import flash.events.StatusEvent;
	import flash.external.ExtensionContext;
	[Event(name="PAYMENT_CANCELLED", type="com.ice.fortumo.FortumoEvent")]
	[Event(name="PAYMENT_FAILED", type="com.ice.fortumo.FortumoEvent")]
	[Event(name="PAYMENT_PENDING", type="com.ice.fortumo.FortumoEvent")]
	[Event(name="PAYMENT_SUCCESS", type="com.ice.fortumo.FortumoEvent")]
	public class Fortumo extends EventDispatcher {
		private static const MAKE_PAYMENT:String = "makepayment";
		private static const APP_ID:String = "com.ice.fortumo";
		private var exContext : ExtensionContext;
		private var consumable : Boolean = true;
		private var displayString : String = null;
		private var productName : String = null;
		private var serviceId : String = null;
		private var appSecret : String = null;
		private var creditsMultiplier : Number = 1.0;
		private var icon : int = -1;
		private var sku : String = null;		
		
		public function Fortumo(target:IEventDispatcher=null) {
			super(target);
			this.exContext = ExtensionContext.createExtensionContext(APP_ID, "fortumo");
			this.exContext.addEventListener(StatusEvent.STATUS, onStatusChange);
		}
		
		public function setConsumable(c : Boolean) : void {
			consumable = c;
		}
		
		public function setDisplayString(s : String) : void {
			displayString = s;
		}
		
		public function setProductName(n : String) : void {
			productName = n;
		}
		
		public function setService(id : String, secret : String) : void {
			serviceId = id;
			appSecret = secret;
		}
		
		public function setCreditsMultiplier(m : Number) : void {
			creditsMultiplier = m;
		}
		
		public function setIcon(i : int) : void {
			icon = i;
		}		
		
		public function setSku(s : String) : void {
			sku = s;
		}
		
		public function makePayment() : void {
			this.exContext.call(MAKE_PAYMENT, consumable, displayString, productName, serviceId, appSecret, creditsMultiplier, icon, sku);
		}
		
		private function onStatusChange(e:StatusEvent):void {
			if (e.code == "PAYMENT_CANCELLED" 
				|| e.code == "PAYMENT_FAILED"
				|| e.code == "PAYMENT_PENDING"
				|| e.code == "PAYMENT_SUCCESS")
			{
				this.dispatchEvent(new FortumoEvent(e.code, e.level));
			}
		}
	}
}