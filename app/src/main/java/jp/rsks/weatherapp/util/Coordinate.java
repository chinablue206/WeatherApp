package jp.rsks.weatherapp.util;

/**
 * Represents latitude, altitude
 */
public class Coordinate {
    private String altitude;
    private String latitude;

    public Coordinate(String altitude, String latitude){
        this.altitude = altitude;
        this.latitude = latitude;
    }

    public String getAltitude() {return this.altitude;}
    public String getLatitude() {return this.latitude;}
}
