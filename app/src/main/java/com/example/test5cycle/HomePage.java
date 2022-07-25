package com.example.test5cycle;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomePage extends AppCompatActivity {

    //define firebase authentication and logout button
    private FirebaseAuth mAuth;
    private Button btnLogout;
    BottomNavigationView bottomNavigationView;
    private ImageView recycle;

    //initializing homepage,account page, account page
    HomePageFragment homePageFragment = new HomePageFragment();
     SettingsFragment settingsFragment = new SettingsFragment();
    feedPage feedPage = new feedPage();
    private Object ImageView;

    //declare google sign in
//    GoogleSignInOptions gso;
//    GoogleSignInClient gsc;
//    TextView email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

       // ImageView = findViewById(R.id.recycle);

        //bottom navigation for home,feed,settings (connected to homepage)
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        //ensure home is the default homepage fragment when user enters home
        bottomNavigationView.setSelectedItemId(R.id.homepage);
        getSupportFragmentManager().beginTransaction().replace(R.id.container,homePageFragment).commit();


        //bottom nav element
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                //check which item : home,acc or feed
                switch (item.getItemId()){
                    //if click home
                    case R.id.homepage:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,homePageFragment).commit();
                        return true;
                        //if settings
                    case R.id.account:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,settingsFragment).commit();
                        //if feed
                    case R.id.feed:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,feedPage).commit();
                        return true;
                }
                return false;
            }
        });

        //declare firebase getInstance and button logout
        mAuth = FirebaseAuth.getInstance();
        btnLogout = findViewById(R.id.logout);


        //when button logout is clicked
        //run logout function
            btnLogout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    logout();
                }
            });

    /*        recycle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(HomePage.this,Recycle.class));
                }
            });*/
//            //declare email textview
//            email= findViewById(R.id.email);

//        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
//        gsc = GoogleSignIn.getClient(this,gso);

//        //get email
//        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
//        if (acct!=null){
//            String personEmail = acct.getEmail();
//            email.setText(personEmail);
//        }
    }

@Override
    public void onStart(){
        //connect firebase
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser==null)
        {
            startActivity(new Intent(HomePage.this, MainActivity.class));
        }
    }
    //logout function
    public void logout() {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(HomePage.this,ChooseLoginRegister.class ));
    }
}