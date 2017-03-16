package claudia.com.googlemapproject;

import android.Manifest;
import android.content.pm.PackageManager;

import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashMap;

import claudia.com.googlemapproject.InfoWindow.MyInfoWindow;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback  {

    private GoogleMap mMap;

    private double lat, lon;

    private Location loc ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mMap = mapFragment.getMap();
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


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        }



        //PositMionner la caméra à l'affichage de la carte
        LatLng centrerCamera = new LatLng(48.872156, 2.347464);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(centrerCamera));

        HashMap<String, String> markers = new HashMap<>();


        //Ajout des marqueurs
        Marker sireneM = mMap.addMarker(new MarkerOptions()
                .position(new LatLng(48.86125629633995, 2.3263978958129883))
                .title("Sirène").snippet("Certains prétendent l'avoir vu nager dans la Seine, pas très loin d'ici"));


        Marker licorneM = mMap.addMarker(new MarkerOptions()
                .position(new LatLng(48.845259549865254, 2.3134374618530273))
                .title("Licorne").snippet("Si vous la voyez sous un arc-en-ciel faites un voeux!"));

        Marker dragonM = mMap.addMarker(new MarkerOptions()
                .position(new LatLng(48.83387658166071, 2.3323631286621094))
                .title("Dragon").snippet("Attention il fait feux de tout bois pour défendre sa princesse !"));


        //ajout des marqueurs dans la hashtable : id du marqueur / nom du fichier correspondant
        markers.put(sireneM.getId(), "sirene");
        markers.put(licorneM.getId(), "licorne");
        markers.put(dragonM.getId(), "dragon");

        // Création d'une fenêtre d'info personnalisée
        mMap.setInfoWindowAdapter(new MyInfoWindow(this, markers));


    }



}


