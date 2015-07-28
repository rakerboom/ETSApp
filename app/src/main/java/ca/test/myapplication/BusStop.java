package ca.test.myapplication;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by rakerboom on 7/28/15.
 */
public class BusStop {

    private int number;
    private String name;
    private LatLng latLng;
    private Marker marker;

    public BusStop(int number, String name, double lat, double lng) {
        this.number = number;
        this.name = name;
        this.latLng = new LatLng(lat, lng);
        this.marker = null;
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
}
