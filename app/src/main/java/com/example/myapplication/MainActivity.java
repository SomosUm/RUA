package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.SearchView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.IOException;
import java.util.List;

public class MainActivity extends FragmentActivity implements OnMapReadyCallback {

    GoogleMap map;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //GoogleMaps + Search Bar
        /*searchView = findViewById(R.id.sv_location);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().
                findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                String location = searchView.getQuery().toString();
                List<Address> addressList=  null;
                if (location!=null || location.equals("")){
                    Geocoder geocoder = new Geocoder(MainActivity.this);
                    try {
                        addressList = geocoder.getFromLocationName(location, 1);
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                    Address address = addressList.get(0);
                    LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                    //map.addMarker(new MarkerOptions().position(latLng).title(location));
                    map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));

                }
                return  false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        mapFragment.getMapAsync(this);*/

        //Navigation Bar
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    switch (menuItem.getItemId()){
                        case R.id.dashboard:
                            startActivity(new Intent(getApplicationContext(),
                                    DashBoard.class));
                            overridePendingTransition(0,0);
                            return  true;
                        case R.id.home:
                            return true;
                        case R.id.map:
                            startActivity(new Intent(getApplicationContext(),
                                    Map.class));
                            overridePendingTransition(0,0);
                            return true;
                        case R.id.about:
                            startActivity(new Intent(getApplicationContext(),
                                    About.class));
                            overridePendingTransition(0,0);
                            return  true;
                    }
                    return false;
                    }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                //criando o ponto
                MarkerOptions markerOptions = new MarkerOptions();
                //posicionando o ponto
                markerOptions.position(latLng);
                //configurando Lat e Lon
                markerOptions.title(latLng.latitude+" : "+ latLng.longitude);
                //limpa posição anterior
                map.clear();
                //zoom no ponto
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
                //Adicionar ponto no mapa
                map.addMarker(markerOptions);
            }
        });
        //LatLng rioDoce= new LatLng(-7.966468, -34.832229);
        //map.addMarker(new MarkerOptions().position(rioDoce).title("RioDoce"));
        //map.moveCamera(CameraUpdateFactory.newLatLng(rioDoce));
    }
}
