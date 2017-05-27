package com.example.abdelrahmansamir.location;

import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity {

    LocationManager locationManager;
    LocationListener locationListener;
    MapView mapView;
    TextView textView;
    GoogleMap googleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapView = (MapView) findViewById(R.id.map_view);
        mapView.onCreate(savedInstanceState);

        locationListener = new android.location.LocationListener() {

            @Override
            public void onLocationChanged(Location location) {
                if (googleMap != null) {
                    googleMap.clear();
                    LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 18));
                    MarkerOptions markerOptions = new MarkerOptions().position(latLng).title("My Location");
                    markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.green_flag));
                    googleMap.addMarker(markerOptions).showInfoWindow();
                    googleMap.addCircle(new CircleOptions()
                            .center(latLng)
                            .radius(50)
                            .strokeColor(Color.BLUE)
                            .fillColor(Color.alpha(Color.BLUE)));
                    googleMap.addCircle(new CircleOptions()
                            .center(latLng)
                            .radius(2)
                            .strokeColor(Color.GRAY)
                            .fillColor(Color.GRAY));
                }
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {
                Toast.makeText(MainActivity.this, "من فضلك فعل خدمة ال Location", Toast.LENGTH_LONG).show();
                Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        };

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER); //NET
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 100, 1, locationListener);
        locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER); //  GPS
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 100, 1, locationListener);

//        locationManager.isProviderEnabled(LocationManager.PASSIVE_PROVIDER); // GPS & NET
//        locationManager.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER, 100, 1, locationListener);

//        locationManager.requestSingleUpdate(LocationManager.NETWORK_PROVIDER, locationListener, null); // one Request


        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(final GoogleMap googleMap) {
                MainActivity.this.googleMap = googleMap;
                googleMap.clear();
                if (locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER) != null) {
                    Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                    LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 18));
                    MarkerOptions markerOptions = new MarkerOptions().position(latLng).title("My Location");
                    markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.green_flag));
                    googleMap.addMarker(markerOptions).showInfoWindow();
                    googleMap.addCircle(new CircleOptions()
                            .center(latLng)
                            .radius(50)
                            .strokeColor(Color.BLUE)
                            .fillColor(Color.alpha(Color.BLUE)));
                    googleMap.addCircle(new CircleOptions()
                            .center(latLng)
                            .radius(2)
                            .strokeColor(Color.GRAY)
                            .fillColor(Color.GRAY));
                }
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

}
