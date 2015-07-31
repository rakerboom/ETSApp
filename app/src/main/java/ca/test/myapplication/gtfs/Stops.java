package ca.test.myapplication.gtfs;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Vector;

import ca.test.myapplication.R;
import ca.test.myapplication.model.BusStop;

/**
 * Created by rakerboom on 7/28/15.
 */
public class Stops implements Runnable {

    public static Vector<BusStop> data = new Vector<BusStop>(6288);

    private Context context;

    public Stops(Context context)
    {
        this.context = context;
    }

    @Override
    public void run()
    {
        synchronized (data) {
            InputStream inputStream = context.getResources().openRawResource(R.raw.stops);

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            try {
                reader.readLine();//throw first line away
                //stop_id,stop_code,stop_name,stop_desc,stop_lat,stop_lon,zone_id,stop_url,location_type,parent_station, routes,
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] array = line.split(",");
                    String[] routes = array[9].split(":");
                    BusStop busStop = new BusStop(Integer.parseInt(array[0].trim()), array[2].trim(), Double.parseDouble(array[4].trim()), Double.parseDouble(array[5].trim()), routes);
                    data.add(busStop);
                }
            } catch (Exception e) {
                System.out.println("Failed to load data.");
            }
        }
    }

}
