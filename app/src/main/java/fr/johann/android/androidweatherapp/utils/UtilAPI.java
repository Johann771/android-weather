package fr.johann.android.androidweatherapp.utils;

import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class UtilAPI {

    public static final String OPEN_WEATHER_MAP_API_COORDINATES = "http://api.openweathermap.org/data/2.5/weather?lat=%s&lon=%s&units=metric&lang=fr&appid=01897e497239c8aff78d9b8538fb24ea";
    public static final String OPEN_WEATHER_MAP_API_CITY_NAME = "http://api.openweathermap.org/data/2.5/weather?q=%s&units=metric&lang=fr&appid=01897e497239c8aff78d9b8538fb24ea";
    public static final String OPEN_WEATHER_MAP_API_FAVOURITE = "http://api.openweathermap.org/data/2.5/group?id=%s&units=metric&lang=fr&appid=01897e497239c8aff78d9b8538fb24ea";
    public static final String OPEN_WEATHER_MAP_API_FORECAST = "http://api.openweathermap.org/data/2.5/forecast/daily?id=%s&units=metric&cnt=5&lang=fr&appid=01897e497239c8aff78d9b8538fb24ea";

    public static boolean isSuccessful(String stringJson) {

        try {
            JSONObject json = new JSONObject(stringJson);

            int cod = json.getInt("cod");
            if (cod != 200)
                return false;

        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public static boolean isSuccessForecast(String stringJson) {

        try {
            JSONObject json = new JSONObject(stringJson);

            int cod = json.getInt("cnt");
            if (cod == 0)
                return false;

        } catch (Exception e) {
            return false;
        }

        return true;
    }
}
