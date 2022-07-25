package com.example.test5cycle;

//login page functions
//in java

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

public class MainActivity extends AppCompatActivity {

    //declare firebase connection, email, password
   private FirebaseAuth mAuth;
   private EditText email, password;
   private Button btnLogin;
   private TextView CreateNewAcc;

   //GOOGLE SIGN IN
//    GoogleSignInOptions gso;
//    GoogleSignInClient gsc;
//    ImageView googleBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //declare firebase, email and password
        mAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.login_email);
        password = findViewById(R.id.login_password);
        btnLogin = findViewById(R.id.login);
        CreateNewAcc = findViewById(R.id.newacc);

        CreateNewAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //proceeds to register page (SignUpRegister)
                startActivity(new Intent(MainActivity.this,SignUpRegister.class));
            }
        });

        //google sign in logo/button
//        googleBtn = findViewById(R.id.google_btn);

        //DECLARE GOOGLE SIGN IN
        //return gso
//        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
//        gsc = GoogleSignIn.getClient(this,gso);

        //ensure email still signed in unless logged out
//        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
//        if(acct!=null){
//            navigateToSecondActivity();
//        }

//        googleBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                signIn();
//            }
//        });

        // when click on login btn
        // run login function
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

    }

    //allows user to choose any gmail account during login
//    void signIn(){
//        Intent signInIntent = gsc.getSignInIntent();
//        startActivityForResult(signInIntent,1000);
//    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        //result on signed in or not
//        //if request code match
//        if (requestCode == 1000){
//            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
//
//            try {
//                //if exception is passed
//                //means not logged in
//                task.getResult(ApiException.class);
//                navigateToSecondActivity();
//            } catch (ApiException e) {
//                //pass toast error message
//               Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }
//
//    void navigateToSecondActivity(){
//        finish();
//        Intent intent = new Intent(MainActivity.this, HomePage.class);
//        startActivity(intent);
//    }

    //login function
    private void login()
    {
        //define user = email, pass = password
        String user = email.getText().toString().trim();
        String pass = password.getText().toString().trim();

        //if statement
        //if the username is empty,
        if(user.isEmpty())
        {
            //error passed on screen
            email.setError("Email can not be empty");
        }
        //if the password input is empty
        if (pass.isEmpty())
        {
            //error passes on screen
            password.setError("Password can not be empty!");
        }
        //if none of that
        else
        {
            // populate data to firebase
            mAuth.signInWithEmailAndPassword(user,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) 
                {
                    //populate = task
                    //if populating is successful,
                    if(task.isSuccessful())
                    {
                        //pass toast message = login successful
                        Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        //proceeds to Home page
                        startActivity(new Intent(MainActivity.this,HomePage.class));
                    }
                    //if not
                    else
                    {
                        // pass toast message = login failed
                        Toast.makeText(MainActivity.this, "Login Failed" +task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}