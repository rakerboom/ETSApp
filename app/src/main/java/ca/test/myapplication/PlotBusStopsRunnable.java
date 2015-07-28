package ca.test.myapplication;

import android.app.Activity;
import android.content.Context;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.VisibleRegion;

import java.util.Vector;

/**
 * Created by rakerboom on 7/28/15.
 */
public class PlotBusStopsRunnable implements Runnable {

    private Activity activity;
    private GoogleMap mMap;
    private LatLngBounds visibleBounds;

    public PlotBusStopsRunnable(Activity activity, GoogleMap mMap, LatLngBounds visibleBounds)
    {
        this.activity = activity;
        this.mMap = mMap;
        this.visibleBounds = visibleBounds;
    }

    @Override
    public void run()
    {
        Vector<BusStop> busStops = BusStopData.BusStopData;
        for(int i=0;i<busStops.size();i++)
        {
            final BusStop busStop = busStops.elementAt(i);
            if(busStop.isVisible(visibleBounds))
            {
                activity.runOnUiThread(new Runnable() {
                    public void run() {
                        if(busStop.getMarker() == null) {
                            Marker marker = mMap.addMarker(new MarkerOptions()
                                    .position(busStop.getLatLng())
                                    .draggable(false)
                                    .title(busStop.getNumber() + ""));
                            busStop.setMarker(marker);
                        }
                    }
                });
            }
            else
            {
                activity.runOnUiThread(new Runnable() {
                    public void run() {
                        final Marker marker = busStop.getMarker();
                        if(marker != null) {
                            marker.remove();
                            busStop.setMarker(null);
                        }
                    }
                });
            }
        }
    }
}
