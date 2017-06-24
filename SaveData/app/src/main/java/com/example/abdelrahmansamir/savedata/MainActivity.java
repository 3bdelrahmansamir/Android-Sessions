package com.example.abdelrahmansamir.savedata;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    TextView tvName;
    TextView tvPassword;
    Button btClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = getSharedPreferences("Data_Name", MODE_PRIVATE);
        tvName = (TextView) findViewById(R.id.tv_name);
        tvPassword = (TextView) findViewById(R.id.tv_password);
        tvName.setText(sharedPreferences.getString("Name", "None"));
        tvPassword.setText(sharedPreferences.getString("Password", "None"));
        btClear = (Button) findViewById(R.id.bt_clear);
        btClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.this.finish();
                SharedPreferences.Editor editor = getSharedPreferences("Data_Name", MODE_PRIVATE).edit();
                editor.clear();
                editor.commit();
                MainActivity.this.startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });
    }
}
