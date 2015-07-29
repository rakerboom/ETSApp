package ca.test.myapplication.model;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Collections;
import java.util.List;
import java.util.Vector;

/**
 * Created by rakerboom on 7/28/15.
 */
public class BusStop {

    private int number;
    private String name;
    private LatLng latLng;
    private Marker marker;
    private List<Integer> routes;
    private String routeString;

    public BusStop(int number, String name, double lat, double lng) {
        this.number = number;
        this.name = name;
        this.latLng = new LatLng(lat, lng);
        this.marker = null;
        this.routes = new Vector<Integer>();
        this.routeString = "";
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

    private void buildRouteString() {
        Collections.sort(this.routes);
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<routes.size();i++)
        {
            sb.append(routes.get(i));
            if(i+1!=routes.size()) sb.append(",");
        }
        this.routeString = sb.toString();
    }

    public String getRouteString()
    {
        return this.routeString;
    }

    public void addRoute(Integer route)
    {
        //build a route string when a route is added so that it's not not later on the UI thread.
        this.routes.add(route);
        buildRouteString();
    }
}
