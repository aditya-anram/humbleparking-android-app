
package com.aditya.humbleparking;

        import androidx.fragment.app.FragmentActivity;

        import android.os.Bundle;

        import com.google.android.gms.maps.CameraUpdateFactory;
        import com.google.android.gms.maps.GoogleMap;
        import com.google.android.gms.maps.OnMapReadyCallback;
        import com.google.android.gms.maps.SupportMapFragment;
        import com.google.android.gms.maps.model.LatLng;
        import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

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

//        // UAD Kampus 1
//        LatLng uad1 = new LatLng(-7.7987557,110.3809038);
//        mMap.addMarker(new MarkerOptions().position(uad1).title("Parkir UAD Kampus 1"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(uad1));
//
//        // UAD Kampus 2
//        LatLng uad2 = new LatLng(-7.8203287,110.385841);
//        mMap.addMarker(new MarkerOptions().position(uad2).title("Parkir UAD Kampus 2"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(uad2));
//
//        // UAD Kampus 3
//        LatLng uad3 = new LatLng(-7.808284,110.387267);
//        mMap.addMarker(new MarkerOptions().position(uad3).title("Parkir UAD Kampus 3"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(uad3));

        // UAD Kampus 4
        LatLng uad4 = new LatLng(-7.8332349, 110.3809325);
        mMap.addMarker(new MarkerOptions().position(uad4).title("Parkir UAD Kampus 4"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(uad4));

//        // UAD Kampus 5
//        LatLng uad5 = new LatLng(-7.8264039,110.3770621);
//        mMap.addMarker(new MarkerOptions().position(uad5).title("Parkir UAD Kampus 5"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(uad5));

        float zoomLevel = 13.0f;
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(uad4, zoomLevel));
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
    }

}

