package jp.rsks.weatherapp;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.net.Uri;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jp.rsks.weatherapp.util.Coordinate;

/**
 * Class to hold Area rest.
 */
public class CityList {

    private final Context mCtx;

    private final HashMap<String, List<City>> mCityList;
    public CityList (Context ctx) {
        mCtx = ctx;
        final Resources res = mCtx.getResources();
        mCityList = new HashMap<String, List<City>>() {
            {
                put(res.getString(R.string.hokkaido),
                        getCityList(R.array.hokkaido_cities));
                put(res.getString(R.string.tohoku),
                        getCityList(R.array.tohoku_cities));
            }
        };
    }

    public HashMap<String, List<City>> getCityList() {
        return mCityList;
    }

    /*
    private List<String[]> getCityList (int resId) {
        final Resources res = mCtx.getResources();
        final TypedArray ta = res.obtainTypedArray(resId);
        final List<String[]> ret = new ArrayList<String[]>();
        for(int i = 0; i < ta.length(); i++){
            ret.add(res.getStringArray(ta.getResourceId(i, 0)));
        }
        return ret;
    }
*/
    private List<City> getCityList (int resId) {
        final Resources res = mCtx.getResources();
        final TypedArray ta = res.obtainTypedArray(resId);
        final List<City> ret = new ArrayList<City>();
        for(int i = 0; i < ta.length(); i++){
            String[] info = res.getStringArray(ta.getResourceId(i, 0));
            ret.add(new City(info[0],
                    Uri.parse(info[1]),
                    new Coordinate(info[3], info[2])));
        }
        return ret;
    }
}
