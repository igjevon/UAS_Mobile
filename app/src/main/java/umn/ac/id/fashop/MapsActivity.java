package umn.ac.id.fashop;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    LocationManager locationManager;
    LocationListener locationListener;
    private int permission_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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

        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                // Add a marker in Sydney and move the camera
                LatLng userLocation = new LatLng(location.getLatitude(),location.getLongitude());
                mMap.addMarker(new MarkerOptions().position(userLocation).title("User Location"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation,16));
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras){

            }


            public void onProviderEnable(String provider){

            }


            public void onProviderDisable(String provider){

            }
        };

        if(checkPermission()){
            Log.i("Info Permission", "True");
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,
                    0,locationListener);
            // Add a marker in Sydney and move the camera
            //get the lastUser location
            Location userLastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            LatLng userLocation = new LatLng(userLastKnownLocation.getLatitude(), userLastKnownLocation.getLongitude());
            mMap.addMarker(new MarkerOptions().position(userLocation).title("Your Location"));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation,16));
        }else{
            requestPermission();
        }

    }
    public boolean checkPermission(){
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
        == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            return true;
        }
        return false;
    }
    public void requestPermission(){
        ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION} ,permission_id);
    }

    @SuppressLint("MissingPermission")
    public void onRequestPermissionResult(int requestCode, String[] permissions, int[] grantResult){
        super.onRequestPermissionsResult(requestCode, permissions, grantResult);
        if(requestCode == permission_id ){
            if(grantResult.length > 0 && grantResult[0] == PackageManager.PERMISSION_GRANTED){
                if(checkPermission()){
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,
                            0,locationListener);
                }

                }
            }
    }
}