package com.ice.fortumo {
	import flash.events.Event;
	
	public class FortumoEvent extends Event {
		public static const PAYMENT_CANCELLED:String = "PAYMENT_CANCELLED" 
		public static const PAYMENT_FAILED:String = "PAYMENT_FAILED";
		public static const PAYMENT_PENDING:String = "PAYMENT_PENDING";
		public static const PAYMENT_SUCCESS:String = "PAYMENT_SUCCESS";
		public function FortumoEvent(type:String, data:*, bubbles:Boolean=false, cancelable:Boolean=false) {
			this.data = data;
			super(type, bubbles, cancelable);
		}
		/** @private */
		public var data:* = null;
	}
}