package com.example.mstappdemo;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Lienhe_Activity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Toolbar toolbarthongtin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lienhe_);
        toolbarthongtin = findViewById(R.id.toolbarthongtin);
        ActionBar();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }

    private void ActionBar() {
        setSupportActionBar(toolbarthongtin);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarthongtin.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return;
//        }
//        mMap.setMyLocationEnabled(true);
        // Add a marker in Sydney and move the camera
//        LatLng trungtam = new LatLng(21.961677455568466, 105.74744435703742);
//        mMap.addMarker(new MarkerOptions().position(trungtam).title("Đại học PHENIKAA"));
//        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
//        CameraPosition cameraPosition = new CameraPosition.Builder().target(trungtam).zoom(8).build();
//        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(trungtam));
//        mMap.animateCamera(CameraUpdateFactory.zoomTo(5.0f));
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
//        mMap.getUiSettings().setZoomControlsEnabled(true);
        LatLng sydn = new LatLng(21.02986329913306, 105.85026826887541);
        googleMap.addMarker(new MarkerOptions()
                .position(sydn)
                .title("The Oriental Jade Hotel"));

        LatLng syd = new LatLng(21.03145726851005, 105.85561226887538);
        googleMap.addMarker(new MarkerOptions()
                .position(syd)
                .title("Babylon Garden Hotel"));

        LatLng sy = new LatLng(21.03035772681795, 105.85040419771053);
        googleMap.addMarker(new MarkerOptions()
                .position(sy)
                .title("Solaria Hanoi Hotel"));

        LatLng s = new LatLng(21.027669031155387, 105.85933766887531);
        googleMap.addMarker(new MarkerOptions()
                .position(s)
                .title("TreHouse Homestay"));

        LatLng sqwe = new LatLng(21.030554312305693, 105.8482606265458);
        googleMap.addMarker(new MarkerOptions()
                .position(sqwe)
                .title("Nam Phuong Home – Daisy"));

        LatLng sqw = new LatLng(21.03393334898712, 105.83253234004027);
        googleMap.addMarker(new MarkerOptions()
                .position(sqw)
                .title("Michi Homestay"));

        LatLng sq = new LatLng(21.023440437975665, 105.84671909771042);
        googleMap.addMarker(new MarkerOptions()
                .position(sq)
                .title("Lacatio Homestay"));

        LatLng q = new LatLng(21.034587461769874, 105.83245116887544);
        googleMap.addMarker(new MarkerOptions()
                .position(q)
                .title("City Center & Good Security Area"));

        LatLng oiuyt = new LatLng(21.00096832774975, 105.81598843946969);
        googleMap.addMarker(new MarkerOptions()
                .position(oiuyt)
                .title("Vinhomes Royal City"));

        LatLng oiuy = new LatLng(21.00255648964607, 105.83467319051788);
        googleMap.addMarker(new MarkerOptions()
                .position(oiuy)
                .title("Ký túc xá Đại học Y"));

        LatLng oiu = new LatLng(21.03812929079007, 105.80025508723422);
        googleMap.addMarker(new MarkerOptions()
                .position(oiu)
                .title("Ký túc xá Đại học Thủ đô"));

        LatLng oi = new LatLng(20.996625944770212, 105.79301546277162);
        googleMap.addMarker(new MarkerOptions()
                .position(oi)
                .title("Khu trọ điều hòa máy lạnh"));

        LatLng o = new LatLng(20.991256552184822, 105.89113508874831);
        googleMap.addMarker(new MarkerOptions()
                .position(o)
                .title("Cho thuê phòng trọ Nguyễn Khoái"));


        LatLng tyui = new LatLng(21.05528133538909, 105.75373538947107);
        googleMap.addMarker(new MarkerOptions()
                .position(tyui)
                .title("Phòng quận 3 có máy lạnh tủ lạnh máy giặt"));

        LatLng tyu = new LatLng(21.057524122252385, 105.74068912588226);
        googleMap.addMarker(new MarkerOptions()
                .position(tyu)
                .title("Cho thuê phòng trọ SV"));

        LatLng ty = new LatLng(20.98554938718934, 105.78440529732568);
        googleMap.addMarker(new MarkerOptions()
                .position(ty)
                .title("Có phòng trọ cho thuê"));

        LatLng sydney = new LatLng(20.961472018947074, 105.74748352654501);
        mMap.addMarker(new MarkerOptions()
                .position(sydney)
                .title("Phenikaa-uni"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(oiuyt));
        CameraPosition cameraPosition = new CameraPosition.Builder().target(oiuyt).zoom(11).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }
}