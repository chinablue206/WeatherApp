package jp.rsks.weatherapp.net;

import jp.rsks.weatherapp.util.Coordinate;

/**
 * Created by rsk on 2016/05/03.
 */
public abstract class WeatherLoader {

    abstract public void getCurrentWeather(Coordinate coordinate,
                                           Listener listener);

    public enum Weather {
        SUNNY,
        RAINY
    }

    public interface Listener {
        public void notify(Weather weather);
    }
}
