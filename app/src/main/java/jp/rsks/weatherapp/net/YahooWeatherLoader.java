package jp.rsks.weatherapp.net;

import android.support.annotation.NonNull;

import com.google.gson.Gson;

import java.io.IOException;

import jp.rsks.weatherapp.BuildConfig;
import jp.rsks.weatherapp.util.Coordinate;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Get　Weather Infomation by YOLP weather API.
 * http://developer.yahoo.co.jp/webapi/map/openlocalplatform/v1/weather.html
 */
public class YahooWeatherLoader extends WeatherLoader {

    private final static String TAG = "YahooWeatherLoader";
    private final String BASEURI = "http://weather.olp.yahooapis.jp/v1/place";
    private final String APPID = BuildConfig.APPID;

    private final OkHttpClient client = new OkHttpClient();

    @Override
    public Weather getCurrentWeather(Coordinate coordeinate) {
        try {
            run(coordeinate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void run(Coordinate coordinate) throws Exception {
        Request request = new Request.Builder()
                .url(buildUrl(coordinate))
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()){
                    throw new IOException("Unexpected code " + response);
                }
                judgeCurrentWeather(parseResponse(response));
            }
        });
    }

    private String buildUrl(Coordinate coordinate) {
        final StringBuilder sb = new StringBuilder();
        sb.append(BASEURI);
        sb.append(String.format("?appid=%s", APPID));
        sb.append("&output=json");
        sb.append(String.format("&coordinates=%s,%s",
                coordinate.getAltitude(),
                coordinate.getLatitude()));
        return sb.toString();
    }

    private @NonNull YahooWeatherResponse parseResponse(@NonNull Response response) {
        final Gson gson = new Gson();
        YahooWeatherResponse result;
        try {
            result = gson.fromJson(response.body().string(),
                            YahooWeatherResponse.class);

        } catch (Exception e) {
            result = new YahooWeatherResponse();
            e.printStackTrace();
        }

        return result;
    }

    private @NonNull Weather judgeCurrentWeather(@NonNull YahooWeatherResponse response){
        for(YahooWeatherResponse.WeatherInfo weatherInfo :
                response.Feature.get(0).Property.WeatherList.Weather){
            if("observation".equals(weatherInfo.Type)){
                final float currentRainfall = weatherInfo.Rainfall;
                if(currentRainfall > 0.2){ return Weather.RAINY; }
            }
        }

        return Weather.SUNNY;
    }

}
