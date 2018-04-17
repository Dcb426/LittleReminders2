package cs316.project_1.littlereminders;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.util.List;
import java.util.Random;

import cs316.project_1.littlereminders.Database.DataSource;
import cs316.project_1.littlereminders.Database.DataStudent;

public class noteExample extends AppCompatActivity {

    private DataSource mDataSource;
    private View context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_example);

        mDataSource = new DataSource(this);
        mDataSource.open();
        if(mDataSource.getdataItemCount()>0)
        {
            List<DataStudent> notes = mDataSource.getAll();
            int b = 1;
            for(int a= 0;a<notes.size();a++)
            {
                    ScheduledAlarm(b,getNotification(notes.get(a).getstudentTitle(),notes.get(a).getstudentText()),notes.get(a).getstudentText());
                    b++;
            }
           toastMessage("Alarms Scheduled");
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i=new Intent(noteExample.this,navigation.class);
                startActivity(i);
            }
        }, 2000);
    }



    /*private void scheduleNotification(Notification notification, int delay) {

        Intent notificationIntent = new Intent(this, noteReciever.class);
        notificationIntent.putExtra(noteReciever.NOTIFICATION_ID, 1);
        notificationIntent.putExtra(noteReciever.NOTIFICATION, notification);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        long futureInMillis = SystemClock.elapsedRealtime() + delay;
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, pendingIntent);
    }*/

    public void ScheduledAlarm (int x, Notification note, String Text)
    {
            Intent noteIntent = new Intent(this, noteReciever.class);
            noteIntent.putExtra(noteReciever.NOTIFICATION_ID, 1);
            noteIntent.putExtra(noteReciever.NOTIFICATION, note);
            noteIntent.putExtra(noteReciever.NOTIFICATION_TEXT, Text);
            final int _id = (int) System.currentTimeMillis();
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this, _id, noteIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            long futureTime = SystemClock.elapsedRealtime() + 10000;
            AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
            alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureTime, pendingIntent);
    }

    private Notification getNotification(String title, String text) {

        //Bitmap tempBMP = BitmapFactory.decodeResource(getResources(),R.drawable.uglydog);
        Notification.Builder builder = new Notification.Builder(this);
        builder.setContentTitle(title);
        builder.setContentText(text);
        builder.setSmallIcon(R.drawable.uglydog);
        //builder.setStyle(new Notification.BigPictureStyle().bigPicture(tempBMP));
        return builder.build();
    }

    private long pickTime() {

        int max = 180000;
        int min = 60000;
        Random r = new Random();
        return r.nextInt(max - min +1 ) + min;
    }

    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}
