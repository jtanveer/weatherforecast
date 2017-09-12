package intern.summer.weatherforecast;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements Callback<Abhawa> {

    private TextView txtCity;
    private TextView txtTemp;
    private TextView txtCond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtCity = (TextView) findViewById(R.id.txtCity);
        txtTemp = (TextView) findViewById(R.id.txtTemp);
        txtCond = (TextView) findViewById(R.id.txtCond);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WeatherInterface weatherInterface = retrofit.create(WeatherInterface.class);

        Call<Abhawa> call = weatherInterface.getWeatherForecast("Dhaka,bd", "metric", "c743683c5ca7b41efb53d07fbd974510");
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<Abhawa> call, Response<Abhawa> response) {
        Abhawa abhawa = response.body();
//        Log.d("Abhawa", "onResponse: City Name: " + abhawa.getName());
        txtCity.setText(abhawa.getName() + ", " + abhawa.getSys().getCountry());
        txtTemp.setText(abhawa.getMain().getTemp() + "˚C");
        txtCond.setText(abhawa.getWeather().get(0).getMain());
    }

    @Override
    public void onFailure(Call<Abhawa> call, Throwable t) {

    }
}
