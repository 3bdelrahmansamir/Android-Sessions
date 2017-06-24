package com.example.abdelrahmansamir.savedata;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {


    LinearLayout linearLayout;
    EditText etName;
    EditText etPassword;
    Button btLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        etName = (EditText) findViewById(R.id.et_name);
        etPassword = (EditText) findViewById(R.id.et_password);
        btLogin = (Button) findViewById(R.id.bt_login);

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (etName.getText().toString().equals("") || etPassword.getText().toString().equals("")) {
                    Toast.makeText(LoginActivity.this, "من فضلك ادخل البيانات كامه فى الحقول", Toast.LENGTH_SHORT).show();
                } else {

                    SharedPreferences.Editor editor = getSharedPreferences("Data_Name", MODE_PRIVATE).edit();
                    editor.putString("Name", etName.getText().toString());
                    editor.putString("Password", etPassword.getText().toString());
                    editor.commit();
                    finish();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));

                }

            }
        });


        SharedPreferences sharedPreferences = getSharedPreferences("Data_Name", MODE_PRIVATE);
        String name = sharedPreferences.getString("Name", null);
        if (name == null) {
            linearLayout = (LinearLayout) findViewById(R.id.ll_all);
            linearLayout.setVisibility(View.VISIBLE);
        } else {
            finish();
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        }


    }
}
