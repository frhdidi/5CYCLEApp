package com.example.test5cycle;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.example.test5cycle.databinding.ActivityMapsBinding;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    private FusedLocationProviderClient fusedLocationProviderClient;
    public static String GOOGLE_API_KEY = "AIzaSyCotL1Py2VIlRJWHAJsEvqNVQxfxenYsrU";
    //request code
    private static final int Request_code = 101;
    private double lat,lng;
    ImageButton atm, recycle, hosp, res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        atm = findViewById(R.id.bank);
        hosp = findViewById(R.id.hospital);
        recycle = findViewById(R.id.recycle2);
        res = findViewById(R.id.restaurant);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this.getApplicationContext());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //bank
        atm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuilder stringBuilder = new StringBuilder("http://maps.googleapis.com/maps/api/place/nearbysearch/json?");
                //containing adress of json file
                stringBuilder.append("location" + lat + "," + lng);
                stringBuilder.append("&radius = 1000");
                stringBuilder.append("&type = atm");
                stringBuilder.append("&sensor = true");
                stringBuilder.append("&key" + getResources().getString(R.string.google_api_key));

                String url = stringBuilder.toString();
                Object dataFetch[] = new Object[2];
                dataFetch[0] = mMap;
                dataFetch[1] = url;

                //create object of fetch data class
                fetchData fetchData = new fetchData();
                fetchData.execute(dataFetch);
            }
        });

        //hosp
        hosp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuilder stringBuilder = new StringBuilder("http://maps.googleapis.com/maps/api/place/nearbysearch/json?");
                //containing adress of json file
                stringBuilder.append("location" + lat + "," + lng);
                stringBuilder.append("&radius = 1000");
                stringBuilder.append("&type = hospital");
                stringBuilder.append("&sensor = true");
                stringBuilder.append("&key" + getResources().getString(R.string.google_api_key));

                String url = stringBuilder.toString();
                Object dataFetch[] = new Object[2];
                dataFetch[0] = mMap;
                dataFetch[1] = url;

                //create object of fetch data class
                fetchData fetchData = new fetchData();
                fetchData.execute(dataFetch);
            }
        });

        //recycle centre
        recycle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuilder stringBuilder = new StringBuilder("http://maps.googleapis.com/maps/api/place/nearbysearch/json?");
                //containing adress of json file
                stringBuilder.append("location" + lat + "," + lng);
                stringBuilder.append("&radius = 1000");
                stringBuilder.append("&type = recycle");
                stringBuilder.append("&sensor = true");
                stringBuilder.append("&key" + getResources().getString(R.string.google_api_key));

                String url = stringBuilder.toString();
                Object dataFetch[] = new Object[2];
                dataFetch[0] = mMap;
                dataFetch[1] = url;

                //create object of fetch data class
                fetchData fetchData = new fetchData();
                fetchData.execute(dataFetch);
            }
        });

        //restaurant
        res.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuilder stringBuilder = new StringBuilder("http://maps.googleapis.com/maps/api/place/nearbysearch/json?");
                //containing adress of json file
                stringBuilder.append("location" + lat + "," + lng);
                stringBuilder.append("&radius = 1000");
                stringBuilder.append("&type = restaurant");
                stringBuilder.append("&sensor = true");
                stringBuilder.append("&key" + getResources().getString(R.string.google_api_key));

                String url = stringBuilder.toString();
                Object dataFetch[] = new Object[2];
                dataFetch[0] = mMap;
                dataFetch[1] = url;

                //create object of fetch data class
                fetchData fetchData = new fetchData();
                fetchData.execute(dataFetch);
            }
        });
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker and move the camera
        getCurrentLocation();
    }

    //get location
    private void getCurrentLocation(){

        //location permission
        if(ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)!=PackageManager.PERMISSION_GRANTED)
        {
            //requesting permission
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},Request_code);
        return;

        }
        //request quality service
        LocationRequest locationRequest= LocationRequest.create();
        locationRequest.setInterval(60000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setFastestInterval(5000);
        //receive notif from location provided API
        LocationCallback locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(@NonNull LocationResult locationResult) {
               // super.onLocationResult(locationResult);
                Toast.makeText(getApplicationContext(),"Location result is" + locationResult,
                        Toast.LENGTH_LONG).show();

                if (locationResult==null){

                    Toast.makeText(getApplicationContext(),"Current location is null" + locationResult,
                            Toast.LENGTH_LONG).show();

                    return;
                }
                for(Location location:locationResult.getLocations()){
                    if (location != null) {
                        Toast.makeText(getApplicationContext(),"Current location is " + locationResult,
                                Toast.LENGTH_LONG).show();
                    }
                }
            }
        };


    fusedLocationProviderClient.requestLocationUpdates(locationRequest,locationCallback,null);
    Task <Location> task = fusedLocationProviderClient.getLastLocation();
    task.addOnSuccessListener(new OnSuccessListener<Location>() {
        @Override
        public void onSuccess(Location location) {
            if(location!=null){
                lat = location.getLatitude();
                lng = location.getLongitude();

                LatLng Latlng = new LatLng(lat,lng);
                mMap.addMarker(new MarkerOptions().position(Latlng).title("current location"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(Latlng));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Latlng,15));
            }
        }
    });

    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
       // super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (Request_code){

            case Request_code:
                if (grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    getCurrentLocation();
                }

        }
    }
}