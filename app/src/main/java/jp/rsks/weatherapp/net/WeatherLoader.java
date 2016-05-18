package jp.rsks.weatherapp.net;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import java.lang.ref.WeakReference;

import jp.rsks.weatherapp.R;
import jp.rsks.weatherapp.util.Coordinate;

/**
 * Created by rsk on 2016/05/03.
 */
public abstract class WeatherLoader {

    final private String TAG = "WeatherLoader";

    private Activity activity;
    protected Coordinate coordinate;
    private ImageView imageView;
    private Context context;

    public WeatherLoader init(Context context,
                         Coordinate coordinate,
                         ImageView imageView) {
        if(! (context instanceof Activity)){
            throw new IllegalArgumentException("Should be instance of Activity");
        }
        this.coordinate = coordinate;
        this.imageView = imageView;
        this.context = new WeakReference<>(context).get();
        this.activity = (Activity) this.context;

        return this;
    }

    abstract public void getCurrentWeather(Listener listener);

    public void loadCurrentWeather(){
        getCurrentWeather(new Listener() {
            @Override
            public void notify(final Weather weather) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        imageView.setImageDrawable(
                                context.getDrawable(weather.resId()));

                    }
                });
            }
        });
    }

    public enum Weather {
        SUNNY(R.drawable.sunny_icon),
        RAINY(R.drawable.rainy_icon);

        final private int icon_resource;

        Weather(int resId) {
            this.icon_resource = resId;
        }

        public int resId() {return this.icon_resource;}
    }

    public interface Listener {
        void notify(Weather weather);
    }
}
