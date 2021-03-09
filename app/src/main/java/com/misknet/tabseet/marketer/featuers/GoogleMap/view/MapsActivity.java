package com.misknet.tabseet.marketer.featuers.GoogleMap.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.misknet.tabseet.marketer.Network.utiles.NetworkingParent;
import com.misknet.tabseet.marketer.R;
import com.misknet.tabseet.marketer.featuers.HomeFragment.model.City;
import com.misknet.tabseet.marketer.featuers.HomeFragment.model.DataToMvisits;
import com.misknet.tabseet.marketer.featuers.HomeFragment.model.Neighborhood;

import java.util.ArrayList;
import java.util.Objects;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, LocationListener {

    private GoogleMap mMap;
    TextView tv_location;
    ImageView im_my_location, im_client_location, im_go;
    ConstraintLayout textcontener;
    LinearLayout conternerfragment;
    RelativeLayout relativeLayout;
    FrameLayout frameLayout;
    SupportMapFragment mapFragment;
    Button btn_refesh;
    private double latitude;
    private double longitude;
    LocationManager locationManager;
    private static final int REQUEST_LOCATION = 100;
    private FusedLocationProviderClient location;
    private final String TAG = MapsActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        init();
        check();
        btn_refesh.setOnClickListener(v -> check());
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        }

        LatLng client = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions()
                .position(client)
                .title("client Location"));

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(client, 12));


        im_my_location.setOnClickListener(v -> {
            openGPS();
            getLocation();

        });
        im_client_location.setOnClickListener(v -> mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(client, 12)));
    }

    public void getDateIntent() {
        Intent intent = getIntent();
        if (intent.hasExtra("latitude") && intent.hasExtra("longitude")) {
            latitude = Double.valueOf(Objects.requireNonNull(intent.getStringExtra("latitude")));
            longitude = Double.valueOf(Objects.requireNonNull(intent.getStringExtra("longitude")));

            String cityname = intent.getStringExtra("cityNamehi");
            String neighborhoodName = intent.getStringExtra("neighborhoodNamehi");

            tv_location.append(cityname + "-" + neighborhoodName);


        } else if (intent.hasExtra("latit") && intent.hasExtra("longit")) {
            latitude = Double.valueOf(Objects.requireNonNull(intent.getStringExtra("latit")));
            longitude = Double.valueOf(Objects.requireNonNull(intent.getStringExtra("longit")));

            String cityname = intent.getStringExtra("cityNamehi");
            String neighborhoodName = intent.getStringExtra("neighborhoodNamehi");

            tv_location.append(cityname + "-" + neighborhoodName);

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_LOCATION) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && permissions[0] == Manifest.permission.ACCESS_FINE_LOCATION) {

            } else {
                Toast.makeText(this, "No premission", Toast.LENGTH_SHORT).show();
            }
        }
    }


    @Override
    public void onLocationChanged(Location location) {
        LatLng myLocation = new LatLng(location.getLatitude(), location.getLongitude());
        mMap.addMarker(new MarkerOptions().position(myLocation));

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myLocation, 12));

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    public void getLocation() {
        try {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 500, 5, this);

        } catch (SecurityException e) {
            Log.e(TAG, e.getMessage());
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LocationRequest.PRIORITY_HIGH_ACCURACY) {
            switch (resultCode) {
                case Activity.RESULT_OK:
                    // All required changes were successfully made
                    Log.i(TAG, "onActivityResult: GPS Enabled by user");
                    break;
                case Activity.RESULT_CANCELED:
                    // The user was asked to change settings, but chose not to
                    Log.i(TAG, "onActivityResult: User rejected GPS request");
                    break;
                default:
                    break;
            }
        }
    }

    public void openGPS() {
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        final LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);

        Task<LocationSettingsResponse> result = LocationServices.getSettingsClient(MapsActivity.this).checkLocationSettings(builder.build());
        result.addOnCompleteListener(new OnCompleteListener<LocationSettingsResponse>() {
            @Override
            public void onComplete(@NonNull Task<LocationSettingsResponse> task) {

                try {
                    LocationSettingsResponse response = task.getResult(ApiException.class);
                    // All location settings are satisfied. The client can initialize location
                    // requests here.
                } catch (ApiException exception) {
                    switch (exception.getStatusCode()) {
                        case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                            // Location settings are not satisfied. But could be fixed by showing the
                            // user a dialog.
                            try {
                                // Cast to a resolvable exception.
                                ResolvableApiException resolvable = (ResolvableApiException) exception;
                                // Show the dialog by calling startResolutionForResult(),
                                // and check the result in onActivityResult().
                                resolvable.startResolutionForResult(
                                        MapsActivity.this,
                                        LocationRequest.PRIORITY_HIGH_ACCURACY);
                            } catch (IntentSender.SendIntentException e) {
                                // Ignore the error.
                            } catch (ClassCastException e) {
                                // Ignore, should be an impossible error.
                            }
                            break;
                        case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                            // Location settings are not satisfied. However, we have no way to fix the
                            // settings so we won't show the dialog.
                            break;
                    }
                }
            }

        });

    }

    public void init() {

        location = LocationServices.getFusedLocationProviderClient(this);
        im_my_location = findViewById(R.id.im_my_location);
        im_client_location = findViewById(R.id.im_client_location);
        im_go = findViewById(R.id.im_go);
        relativeLayout = findViewById(R.id.layout_no_internet);
        btn_refesh = findViewById(R.id.btn_refresh);
        textcontener = findViewById(R.id.textcontener);
        conternerfragment=findViewById(R.id.conternerfragment);
        tv_location = findViewById(R.id.tv_location);
        frameLayout=findViewById(R.id.mapcontener);
         mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void check() {
        if (NetworkingParent.myConnection) {

            mapFragment.getMapAsync(this);
            location = LocationServices.getFusedLocationProviderClient(this);
            im_go.setOnClickListener(v -> onBackPressed());
            textcontener.setVisibility(View.VISIBLE);
            im_my_location.setVisibility(View.VISIBLE);
            im_client_location.setVisibility(View.VISIBLE);
            conternerfragment.setVisibility(View.VISIBLE);
            relativeLayout.setVisibility(View.GONE);
            mapFragment.setMenuVisibility(false);
            getDateIntent();

        } else {
            frameLayout.setBackground(getResources().getDrawable(R.drawable.splash_logo, null));
            textcontener.setVisibility(View.GONE);
            im_my_location.setVisibility(View.GONE);
            im_client_location.setVisibility(View.GONE);
            conternerfragment.setVisibility(View.GONE);
            relativeLayout.setVisibility(View.VISIBLE);
            mapFragment.setMenuVisibility(true);

        }
    }
}
