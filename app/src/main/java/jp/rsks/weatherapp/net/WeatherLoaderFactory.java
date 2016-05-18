package jp.rsks.weatherapp.net;

/**
 * Created by rsk on 2016/05/06.
 */
public class WeatherLoaderFactory {

    /* prohibit to use */
    private WeatherLoaderFactory () {}

    final static public WeatherLoader get() {
        return new YahooWeatherLoader();
    }

}
