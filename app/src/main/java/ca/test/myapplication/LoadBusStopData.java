package ca.test.myapplication;

import android.content.Context;
import android.content.res.Resources;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by rakerboom on 7/28/15.
 */
public class LoadBusStopData implements Runnable {

    private Context context;

    public LoadBusStopData(Context context)
    {
        this.context = context;
    }

    @Override
    public void run()
    {
        InputStream inputStream = context.getResources().openRawResource(R.raw.stops);

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        try {
            reader.readLine();//throw first line away
            String line;
            while ((line = reader.readLine()) != null) {
                String[] array = line.split(",");
                BusStopData.BusStopData.add(new BusStop(Integer.parseInt(array[0].trim()), array[2].trim(), Double.parseDouble(array[4].trim()), Double.parseDouble(array[5].trim())));
            }
        } catch (IOException e) {
        }
    }

}
