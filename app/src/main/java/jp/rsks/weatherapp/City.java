package jp.rsks.weatherapp;

import android.net.Uri;

import jp.rsks.weatherapp.util.Coordinate;

/**
 * Represents city information.
 */
public class City {
    final private Coordinate coordinate;
    final private String name;
    final private Uri logoUri;

    public City(String name, Uri logoUri, Coordinate coordinate){
        this.coordinate = coordinate;
        this.name = name;
        this.logoUri = logoUri;
    }

    public Coordinate coordinate() { return this.coordinate; }
    public String name() {return this.name;}
    public Uri logoUri() {return this.logoUri;}

}
