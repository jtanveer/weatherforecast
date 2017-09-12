package intern.summer.weatherforecast;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by jtanveer on 9/12/17.
 */

public interface WeatherInterface {

    @GET("weather")
    Call<Abhawa> getWeatherForecast(@Query("q") String location, @Query("units") String units, @Query("appid") String key);
}
