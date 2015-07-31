package ca.akerboom.ets;

import android.app.Activity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;

import java.util.Vector;

import ca.akerboom.ets.model.BusStop;
import ca.akerboom.ets.gtfs.Stops;

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
        synchronized (Stops.data) {
            Vector<BusStop> busStops = Stops.data;
            for (int i = 0; i < busStops.size(); i++) {
                final BusStop busStop = busStops.elementAt(i);
                if (busStop.isVisible(visibleBounds)) {
                    activity.runOnUiThread(new Runnable() {
                        public void run() {
                            if (busStop.getMarker() == null) {
                                Marker marker = mMap.addMarker(busStop.getMarkerOptions());
                                busStop.setMarker(marker);
                            }
                        }
                    });
                } else {
                    activity.runOnUiThread(new Runnable() {
                        public void run() {
                            final Marker marker = busStop.getMarker();
                            if (marker != null) {
                                marker.remove();
                                busStop.setMarker(null);
                            }
                        }
                    });
                }
            }
        }
    }
}
