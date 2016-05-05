package jp.rsks.weatherapp;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Class to hold Area rest.
 */
public class CityList {

    private final Context mCtx;

    private final HashMap<String, List<String[]>> mCityList;
    public CityList (Context ctx) {
        mCtx = ctx;
        final Resources res = mCtx.getResources();
        mCityList = new HashMap<String, List<String[]>>() {
            {
                put(res.getString(R.string.hokkaido),
                        getCityList(R.array.hokkaido_cities));
                put(res.getString(R.string.tohoku),
                        getCityList(R.array.tohoku_cities));
            }
        };
    }

    public HashMap<String, List<String[]>> getCityList() {
        return mCityList;
    }

    private List<String[]> getCityList (int resId) {
        final Resources res = mCtx.getResources();
        final TypedArray ta = res.obtainTypedArray(resId);
        final List<String[]> ret = new ArrayList<String[]>();
        for(int i = 0; i < ta.length(); i++){
            ret.add(res.getStringArray(ta.getResourceId(i, 0)));
        }
        return ret;
    }

    private class Coordinates {
        final private float latitude;
        final private float altitude;

        public Coordinates(float latitude, float altitude){
            this.latitude = latitude;
            this.altitude = altitude;
        }

        public float getLatitude () { return latitude; }
        public float getAltitude () { return altitude; }

    }
}
