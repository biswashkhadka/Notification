package com.biswash.notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btnstart, btnstop;
    Button btnchannel1, btnchannel2;
    private NotificationManagerCompat notificationManagerCompat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnchannel1 = findViewById(R.id.btnchannel1);
        btnchannel2 = findViewById(R.id.btnchannel2);
        btnstart = findViewById(R.id.btnstart);
        btnstop = findViewById(R.id.btnstop);

        btnstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startMyService();
            }
        });

        btnstop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopMyService();
            }
        });
        notificationManagerCompat = NotificationManagerCompat.from(this);
        CreateChannel channel = new CreateChannel(this);
        channel.createChannel();


        btnchannel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DisplayNotification();

            }
        });

        btnchannel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DisplayNotification2();
            }
        });

    }

    private void startMyService(){
        startService(new Intent(this, myservice.class));
    }

    private void stopMyService(){
        stopService(new Intent(this, myservice.class));
    }

    private void DisplayNotification2() {
        Notification notification = new NotificationCompat.Builder(this, CreateChannel.CHANNEL_2)
                .setSmallIcon(R.drawable.ic_sms_failed_black_24dp)
                .setContentTitle("Second Message")
                .setContentText("Second message body")
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

        notificationManagerCompat.notify(1,notification);
        count++;


    }
    int count = 1;
    private void DisplayNotification() {

        Notification notification = new NotificationCompat.Builder(this,CreateChannel.CHANNEL_1)
                .setSmallIcon(R.drawable.ic_sms_black_24dp)
                .setContentTitle("First Message")
                .setContentText("First message body")
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

        notificationManagerCompat.notify(count,notification);
        count++;
    }

    
}
