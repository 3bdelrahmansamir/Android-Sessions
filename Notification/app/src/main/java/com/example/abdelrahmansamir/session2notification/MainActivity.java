package com.example.abdelrahmansamir.session2notification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }


    public void notification(View v) {

        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("Key", "Value");

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setColor(Color.WHITE)
                .setContentTitle("Welocme")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentText("Hello World!")
//                .setAutoCancel(false)
                .setNumber(3)
//                .setOngoing(true)
                .setSound(defaultSoundUri)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .addAction(
                        R.drawable.ic_action_stat_share,
                        getResources().getString(R.string.action_share),
                        PendingIntent.getActivity(
                                MainActivity.this,
                                0,
                                new Intent(MainActivity.this, SecondActivity.class),
                                PendingIntent.FLAG_UPDATE_CURRENT));

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
//        notificationManager.cancel(0);
    }


    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.main, menu);

        MenuItem itemcheck1 = menu.findItem(R.id.check1);
        MenuItem itemcheck2 = menu.findItem(R.id.check2);


        itemcheck1.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                menuItem.setChecked(true);
                return false;
            }
        });
        itemcheck2.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                menuItem.setChecked(true);
                return false;
            }
        });

        MenuItem item = menu.findItem(R.id.search);

        final SearchView sv = (SearchView) item.getActionView();


        int id = sv.getContext().getResources().getIdentifier("android:id/search_src_text", null, null);


        final TextView textView = (TextView) sv.findViewById(id);


        textView.setHint("عمليه البحث هنا");


        textView.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.white));


        textView.setHintTextColor(ContextCompat.getColor(MainActivity.this, R.color.white));


        textView.setBackground(ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_action_stat_share));


        textView.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.colorPrimaryDark));

        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String searchWord) {

                Toast.makeText(MainActivity.this, searchWord, Toast.LENGTH_LONG).show();
                sv.onActionViewCollapsed();
                textView.setText("");

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        });

        MenuItem itemAbout = menu.findItem(R.id.about);
        itemAbout.setTitle("About");

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.help) {
            Toast.makeText(MainActivity.this, "Help", Toast.LENGTH_LONG).show();
        }

        return super.onOptionsItemSelected(item);
    }
}
