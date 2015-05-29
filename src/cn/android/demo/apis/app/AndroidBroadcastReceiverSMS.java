package cn.android.demo.apis.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

/**
 * 
 * TODO 未收到短信
 * 
 * 
 * 广播监听短信
 * 
 * @author Elroy
 * 
 */
public class AndroidBroadcastReceiverSMS extends BroadcastReceiver {
	public static final String ACTION_SMS = "android.provider.Telephony.SMS_RECEIVED";

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.v("DDD", "SMS onReceive");
		if (ACTION_SMS.equals(intent.getAction())) {
			Intent intentSms = new Intent(context, BroadCastActivity2SMS.class);
			intentSms.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

			SmsMessage[] msgs = getMessageFromIntent(intent);
			StringBuilder sBuilder = new StringBuilder();
			if (msgs != null && msgs.length > 0) {
				for (SmsMessage msg : msgs) {
					Log.v("DDD", "执行");
					Log.v("DDD", msg.getDisplayMessageBody());
					sBuilder.append("接收到了短信：\n发件人是：");
					sBuilder.append(msg.getDisplayOriginatingAddress());
					sBuilder.append("\n------短信内容-------\n");
					sBuilder.append(msg.getDisplayMessageBody());
					intentSms.putExtra("sms_address",
							msg.getDisplayOriginatingAddress());
					intentSms.putExtra("sms_body", msg.getDisplayMessageBody());
				}
			}
			Toast.makeText(context, sBuilder.toString(), 1000).show();
			context.startActivity(intentSms);
		}
	}

	public static SmsMessage[] getMessageFromIntent(Intent intent) {
		SmsMessage retmeMessage[] = null;
		Bundle bundle = intent.getExtras();
		Object pdus[] = (Object[]) bundle.get("pdus");
		retmeMessage = new SmsMessage[pdus.length];
		for (int i = 0; i < pdus.length; i++) {
			byte[] bytedata = (byte[]) pdus[i];
			retmeMessage[i] = SmsMessage.createFromPdu(bytedata);
		}
		return retmeMessage;
	}

}
