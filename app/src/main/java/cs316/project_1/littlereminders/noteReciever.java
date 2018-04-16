package cs316.project_1.littlereminders;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.telephony.SmsManager;
import android.widget.Toast;

public class noteReciever extends BroadcastReceiver {

    public static String NOTIFICATION_ID = "notification-id";
    public static String NOTIFICATION = "notification";
    public static String NOTIFICATION_TEXT = "notification_text";
    public String text;

    @Override
    public void onReceive(final Context context, Intent intent) {
        NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        Notification notification = intent.getParcelableExtra(NOTIFICATION);


        int id = intent.getIntExtra(NOTIFICATION_ID, 0);
        text = intent.getStringExtra(NOTIFICATION_TEXT);

        nm.notify(id, notification);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                String phoneNumberReciver="9724397977";// phone number to which SMS to be send
                String message=text;// message to send
                SmsManager sms = SmsManager.getDefault();
                sms.sendTextMessage(phoneNumberReciver, null, message, null, null);
                // Show the toast  like in above screen shot
                Toast.makeText(context, "Alarm Triggered and SMS Sent", Toast.LENGTH_LONG).show();
            }
        }, 60000);
    }

}
