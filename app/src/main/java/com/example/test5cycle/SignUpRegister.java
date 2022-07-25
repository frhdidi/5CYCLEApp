package com.example.test5cycle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpRegister extends AppCompatActivity {

    //define class firebase authentication, email, reg button
    private FirebaseAuth mAuth;
    private EditText email, password, user_name;
    private Button btnRegister;
    TextView LoginInstead;

    //declare google sign in
//    GoogleSignInOptions gso;
//    GoogleSignInClient gsc;
//    ImageView googlebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_register);

        //declare firebase get instance, email, password, name, etc from input
        mAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.register_email);
        password = findViewById(R.id.register_password);
        btnRegister = findViewById(R.id.register);
        user_name = findViewById(R.id.user_name);
        LoginInstead = findViewById(R.id.text_login_instead);

//        //declare google logo as button (xml)
//        googlebtn = findViewById(R.id.google_btn);
//
//        //sign in options, request email, gsc= get client
//        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
//        gsc = GoogleSignIn.getClient(this,gso);
//
//        // when user click google button
//        googlebtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //run sign in function
//                signIn();
//            }
//        });

        // when register button clicked
        //run register function
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Register();
            }
        });

        LoginInstead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //proceeds to register page (SignUpRegister)
                startActivity(new Intent(SignUpRegister.this,MainActivity.class));
            }
        });
    }

//    //sign in function
//    void signIn() {
//        //client sign in
//        Intent signInIntent = gsc.getSignInIntent();
//        startActivityForResult(signInIntent,1000);
//    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode == 1000){
//            //populate = task
//            Task<GoogleSignInAccount> task=GoogleSignIn.getSignedInAccountFromIntent(data);
//
//            try {
//                task.getResult(ApiException.class);
//                navigateToSecondActivity();
//            } catch (ApiException e) {
//                //google pass toast message
//                Toast.makeText(getApplicationContext(),"Something went wrong",Toast.LENGTH_SHORT).show();
//            }
//
//        }
//    }

//    void navigateToSecondActivity(){
//        finish();
//        Intent intent = new Intent(SignUpRegister.this, HomePage.class);
//        startActivity(intent);
//    }

    //register function
    private void Register()
    {
        //define variables user =email, pass= password, registername = regname
        String user = email.getText().toString().trim();
        String pass = password.getText().toString().trim();
        String registername = user_name.getText().toString().trim();

        //if  statement
        // if username input is empty
        if(user.isEmpty())
        {
            //pass error message
            email.setError("Email can not be empty");
        }
        //if password input is empty
        if (pass.isEmpty())
        {
            //pass error message
            password.setError("Password can not be empty!");
        }
        //if name input is empty
        if (registername.isEmpty())
        {
            //pass error message
            user_name.setError("Name can not be empty!");
        }
        //if not
        else
        {
            //populate data to firebase
            mAuth.createUserWithEmailAndPassword(user,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    //populate = task
                    //if the task is successful
                    if (task.isSuccessful())
                    {
                        //pass toast message = user registered successfully
                        Toast.makeText(SignUpRegister.this, "User registered successfully", Toast.LENGTH_SHORT).show();
                        //proceeds to login page, for relogin
                        startActivity(new Intent(SignUpRegister.this,MainActivity.class));
                    }
                    //if not
                    else
                    {
                        //toast message registration failed
                        Toast.makeText(SignUpRegister.this, "Registration failed"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}