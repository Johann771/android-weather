package fr.johann.android.androidweatherapp.activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.MutableLiveData;
import fr.johann.android.androidweatherapp.R;
import fr.johann.android.androidweatherapp.databinding.ActivityMainBinding;
import fr.johann.android.androidweatherapp.models.City;
import fr.johann.android.androidweatherapp.utils.UtilAPI;
import okhttp3.*;
import org.json.JSONException;

import java.io.IOException;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private Context mContext;
    private OkHttpClient mOkHttpClient;
    private City mCity;
    private LocationManager mLocationManager;
    private final int REQUEST_CODE = 1;
    private Location mCurrentLocation;
    private Handler mHandler;
    private LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            mCurrentLocation = location;
            Log.d("lol", "onLocationChanged: " + location);
            // Récupération des données pour les coordonnées gps
            updateWeatherDataCoordinates();
            mLocationManager.removeUpdates(mLocationListener);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mContext = this;
        mOkHttpClient = new OkHttpClient();
        mHandler = new Handler();

        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            binding.linearLayoutBackground.setVisibility(View.VISIBLE);
            updateWeatherDataCoordinatesFromMyLocation();
        } else {
            binding.linearLayoutBackground.setVisibility(View.INVISIBLE);
            updateViewError(R.string.no_connexion);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission Granted
                    updateWeatherDataCoordinatesFromMyLocation();
                } else {
                    // Permission Denied
                    Toast.makeText(MainActivity.this, "Location Permission Denied", Toast.LENGTH_SHORT).show();
                    // Permission Denied
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    public void updateWeatherDataCoordinatesFromMyLocation() {

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
        } else {
            mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, mLocationListener);
        }
    }

    public void onButtonClick(View view) {
        Intent intent = new Intent(mContext, FavoriteActivity.class);
        intent.putExtra("key_message", binding.mEditTextInput.getText().toString());
        startActivity(intent);
    }

    public void updateWeatherDataCoordinates() {

        //String[] params = {String.valueOf(LAT), String.valueOf(LNG)};
        String[] params = {String.valueOf(mCurrentLocation.getLatitude()), String.valueOf(mCurrentLocation.getLongitude())};


        String s = String.format(UtilAPI.OPEN_WEATHER_MAP_API_COORDINATES, params);

        Log.d("TAG", "updateWeatherDataCoordinates: "+s);
        Request request = new Request.Builder().url(s).build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {

                final String stringJson = response.body().string();

                if (response.isSuccessful() && UtilAPI.isSuccessful(stringJson)) {
                    mHandler.post(() -> renderCurrentWeather(stringJson));
                } else {
                    mHandler.post(() -> updateViewError(R.string.place_not_found));
                }
            }
        });
    }

    private void renderCurrentWeather(String jsonString) {

        try {
            mCity = new City(jsonString);
            binding.setCity(mCity);
            binding.linearLayoutBackground.setVisibility(View.VISIBLE);

        } catch (JSONException e) {
            updateViewError(R.string.api_error);
        }
    }

    View.OnClickListener onClickListenerButton2 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(getApplicationContext(), "Clic sur le bouton 2", Toast.LENGTH_LONG).show();
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("TAG", "FavoriteActivity: onDestroy()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("TAG", "FavoriteActivity: onStart()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("TAG", "FavoriteActivity: onRestart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("TAG", "FavoriteActivity: onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("TAG", "FavoriteActivity: onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("TAG", "FavoriteActivity: onStop()");
    }

    private void callApiWithCoords(double lat, double lon) {
        String url = "https://api.openweathermap.org/data/2.5/weather?lat=" + lat + "&lon=" + lon + "&appid=01897e497239c8aff78d9b8538fb24ea&units=metric&lang=fr";
        Request request = new Request.Builder().url(url).build();
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(() -> showError("Une erreur est survenue"));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String stringJson = response.body().string();
                    runOnUiThread(() -> updateUi(stringJson));
                } else {
                    runOnUiThread(() -> showError("mon message"));
                }
            }
        });
    }

    private void showError(String message) {

    }

    private void updateUi(String stringJson) {
        try {
            mCity = new City(stringJson);
            binding.setCity(mCity);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void updateViewError(int resString) {
        binding.linearLayoutBackground.setVisibility(View.INVISIBLE);
//
//                        mLinearLayoutMain.setVisibility(View.INVISIBLE);
//                        mProgressBarMain.setVisibility(View.INVISIBLE);
//                        mFloatingButtonFavorite.setVisibility(View.INVISIBLE);
//                        mTextViewNoConnection.setVisibility(View.VISIBLE);
//                        mTextViewNoConnection.setText(resString);
    }
}