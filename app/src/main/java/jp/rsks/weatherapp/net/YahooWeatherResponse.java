package jp.rsks.weatherapp.net;

import java.util.List;

/**
 * Gson objecct definition for yahoo data api.
 */
public class YahooWeatherResponse {
    public List<_Feature> Feature;
    public _ResultInfo ResultInfo;

    public class _ResultInfo {
        public int Count;
    }

    public class _Feature {
        public _Property Property;
    }

    public class _Property {
        public _WeatherList WeatherList;
    }

    public class _WeatherList {
        List<WeatherInfo> Weather;
    }

    public class WeatherInfo {
        public String Type;
        public String Date;
        public float Rainfall;
    }
}
