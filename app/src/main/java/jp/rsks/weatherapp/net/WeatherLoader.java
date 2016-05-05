package jp.rsks.weatherapp.net;

import jp.rsks.weatherapp.util.Coordinate;

/**
 * Created by rsk on 2016/05/03.
 */
public abstract class WeatherLoader {

    abstract public Weather getCurrentWeather(Coordinate coordinate);

    public enum Weather {
        SUNNY,
        RAINY
    }
}
