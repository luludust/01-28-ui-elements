package edu.uw.uidemo;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "**DEMO**";

    private static final int REQUEST_PICTURE_CODE = 1;

    private int mClickCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ActionBar actionBar = getSupportActionBar(); //for reference

        //View launchButton = findViewById(R.id.btnLaunch);
        //View callButton = findViewById(R.id.btnDial);
        //View cameraButton = findViewById(R.id.btnPicture);
        //View messageButton = findViewById(R.id.btnMessage);
        //View clickerButton = findViewById(R.id.btnClicker);

    }

    public void startSecondActivity(View v) {
        Log.v(TAG, "Launch button pressed");

        //Explicit intent
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        intent.putExtra("edu.uw.uidemo.message", "Hello number 2!");
        startActivity(intent);
    }

    public void callNumber(View v) {
        Log.v(TAG, "Call button pressed");

        //implicit intent
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:206-685-1622"));

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void takePicture(View v) {
        Log.v(TAG, "Camera button pressed");

        //implicit intent
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, REQUEST_PICTURE_CODE);
        }
    }

    public void sendMessage(View v) {
        Log.v(TAG, "Message button pressed");
        //...
    }

    //click notification id
    private int CLICK_NOTIFICATION_ID = 0;

    public void clickerPressed(View v) {
        Log.v(TAG, "Clicker button pressed");
//        ActionBar actionBar = getSupportActionBar(); //for reference
//        actionBar.hide();

        //SHOW NOTIFICATIONS
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_stat_name2)
                .setContentTitle("Click Counting")
                .setContentText("I work??")
                .setPriority(NotificationCompat.PRIORITY_HIGH);

        // Creates an explicit intent for an Activity in your app
        Intent resultIntent = new Intent(this, MainActivity.class);

//        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
//        stackBuilder.addNextIntent(resultIntent);
//        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(
//                        0,
//                        PendingIntent.FLAG_UPDATE_CURRENT
//                );

        PendingIntent resultPendingIntent = PendingIntent.getActivity(this, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        mBuilder.setContentIntent(resultPendingIntent);

        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
// mId allows you to update the notification later on.
        mNotificationManager.notify(CLICK_NOTIFICATION_ID, mBuilder.build());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.menu_item1 :
                callNumber(null);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
