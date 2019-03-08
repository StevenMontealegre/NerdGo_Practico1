package application.nerdgo;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;

public class MapsActivity2 extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private LocationManager manager;
    Polyline lineBiblioteca, lineEdificioB, lineEdificioL;
    ArrayList<Polyline> poligonos;

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps2);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        manager = (LocationManager) getSystemService(LOCATION_SERVICE);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 0, new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Toast.makeText(getApplicationContext(), "Posición actual ----- LAT:" + location.getLatitude() + " " + "Posición actual ----- LAT:" + location.getLongitude(), Toast.LENGTH_LONG).show();
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
        });

        //HABILITAR ZOOM
        UiSettings uiSettings = mMap.getUiSettings();
        uiSettings.setZoomControlsEnabled(true);

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(3.34, -76.53);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in ICESI")).setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
        float zoomlevel = 16;
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, zoomlevel));

//Creación de polígonos

        lineBiblioteca = mMap.addPolyline(new PolylineOptions()
                .add(new LatLng(3.342024, -76.530116), new LatLng(3.341617, -76.529767))
                .width(5)
                .color(Color.RED));
        lineEdificioB = mMap.addPolyline(new PolylineOptions()
                .add(new LatLng(3.341290, -76.530465), new LatLng(3.341590, -76.529843))
                .width(5)
                .color(Color.RED));
        lineEdificioL = mMap.addPolyline(new PolylineOptions()
                .add(new LatLng(3.341644, -76.529666), new LatLng(3.340867, -76.529263))
                .width(5)
                .color(Color.RED));
        poligonos.add(lineBiblioteca);
        poligonos.add(lineEdificioB);
        poligonos.add(lineEdificioL);
    }
}
