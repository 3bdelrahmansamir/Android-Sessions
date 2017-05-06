package com.example.abdelrahmansamir.intents;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class GetDataFromIntent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_data_from_intent);

        Bundle bundle = getIntent().getExtras();
        if (bundle.getString("Name").equals("Bahaa") && bundle.getString("Password").equals("12345")) {

            Intent intent = new Intent();
            intent.putExtra("result", "OK");
            setResult(0, intent);
            finish();
        }
    }
}
