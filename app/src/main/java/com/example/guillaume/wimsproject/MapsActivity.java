package com.example.guillaume.wimsproject;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    String lat, lon; // latitude et longitude en string
    private GoogleMap mMap;
    MainActivity appel2 = MainActivity.getInstance2(); // grâce à cet instance on pourra recuperer des donnees de la classe MainActivity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // nous recuperons la latitude et la longitude depuis les zones de texte
        lat = getLat();
        lon = getLon();

        // nous convertissons la latitude et la longitude en double
        double latitude = Double.parseDouble(lat);
        double longitude = Double.parseDouble(lon);

        // parametrage de la carte pour qu'elle affiche la latitude et la longitude qui ont été récupérées
        LatLng localisation = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(localisation).title("WIMS"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(localisation));

    }

    public String getLat() {

        lat =  appel2.txtlatitude.getText().toString();

        return lat;
    }
    public String getLon() {

        lon =  appel2.txtlongitude.getText().toString();

        return lon;
    }

}
