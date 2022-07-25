package com.example.test5cycle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ChooseLoginRegister extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_login_register);

        //declare button for login
        Button loginbtn = (Button) findViewById(R.id.login);

        //if user click to go to login
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //proceeds to login page(MainActivity)
                startActivity(new Intent(ChooseLoginRegister.this,MainActivity.class));
            }
        });

        //declare register button
        Button registerbtn = (Button) findViewById(R.id.register);

        //if user click on register button
        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //proceeds to register page (SignUpRegister)
                startActivity(new Intent(ChooseLoginRegister.this,SignUpRegister.class));
            }
        });
    }
}