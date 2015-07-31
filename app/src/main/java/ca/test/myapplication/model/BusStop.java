package ca.test.myapplication.model;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Collections;
import java.util.List;
import java.util.Vector;

import ca.test.myapplication.R;

/**
 * Created by rakerboom on 7/28/15.
 */
public class BusStop {

    private int number;
    private String name;
    private LatLng latLng;
    private Marker marker;
    private String routeString;
    private MarkerOptions markerOptions;

    public BusStop(int number, String name, double lat, double lng, String[] routes) {
        this.number = number;
        this.name = name;
        this.latLng = new LatLng(lat, lng);
        this.marker = null;
        this.routeString = buildRouteString(routes);
        this.markerOptions = new MarkerOptions()
                .position(getLatLng())
                .draggable(false)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.bus))
                .title(getNumber() + "")
                .snippet(getRouteString());
    }

    public MarkerOptions getMarkerOptions() {
        return markerOptions;
    }

    public boolean isVisible(LatLngBounds bounds)
    {
        return bounds.contains(latLng);
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public int getNumber() {
        return number;
    }

    public String toString()
    {
        return number+","+name+","+latLng.latitude+","+latLng.longitude;
    }

    public void setMarker(Marker marker) {
        this.marker = marker;
    }

    public Marker getMarker() {
        return marker;
    }

    private String buildRouteString(String[] routes) {
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<routes.length;i++)
        {
            sb.append(routes[i]);
            if(i+1!=routes.length) sb.append(",");
        }
        return sb.toString();
    }

    public String getRouteString()
    {
        return this.routeString;
    }
}
