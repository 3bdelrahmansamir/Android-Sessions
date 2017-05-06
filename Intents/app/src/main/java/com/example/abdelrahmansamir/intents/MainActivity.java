package com.example.abdelrahmansamir.intents;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public static final int RESULT_OK = 0;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            String result = bundle.getString("result");
            if (result.equals("OK")) {
                Toast.makeText(MainActivity.this, "Welcome Bahaa", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void onView(View v) {


        Intent intent = new Intent(MainActivity.this, GetDataFromIntent.class);
        intent.putExtra("Name", "Bahaa");
        intent.putExtra("Password", "12345");
        startActivityForResult(intent, RESULT_OK);

//        Intent intent = new Intent();
//        intent.setClassName("com.ex.ex", "com.ex.ex.MainActivity");
//        startActivity(intent);


//        Intent i = new Intent("android.intent.action.VIEW");
//        i.setData(Uri.parse("http://www.amazon.com"));
//        startActivity(i);

//        Intent intent = new Intent(android.content.Intent.ACTION_DIAL, Uri.parse("tel:+201143328433"));
//        startActivity(intent);
//
//        Intent intent1 = new Intent(Intent.ACTION_VIEW);
//        intent1.setData(Uri.parse("geo:37.827500,-122.481670"));
//        startActivity(intent1);

//        Intent shareIntent = new Intent(Intent.ACTION_SEND);
//        shareIntent.setType("text/plain");
//        shareIntent.putExtra(Intent.EXTRA_TEXT, "TEXT");
//        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
//        shareIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"email,email"});
//        startActivity(shareIntent);

    }
}
