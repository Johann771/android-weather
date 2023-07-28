package fr.johann.android.androidweatherapp.models;

import android.util.Log;
import fr.johann.android.androidweatherapp.utils.Util;
import org.json.JSONException;
import org.json.JSONObject;

public class City {
    private int mIdCity;
    public String mName;
    public String mDescription;
    public String mTemperature;
    public int mWeatherIcon;
    public int mldCity;
    public double mLatitude;
    public double mLongitude;
    public int mWeatherResIconWhite;
    public int mWeatherResIconGrey;


    public City(String mName, String mDescription, String mTemperature, int mWeatherIcon) {
        this.mName = mName;
        this.mDescription = mDescription;
        this.mTemperature = mTemperature;
        this.mWeatherIcon = mWeatherIcon;
    }

    public City(String stringJson) throws JSONException {

        JSONObject json = new JSONObject(stringJson);

        JSONObject details = json.getJSONArray("weather").getJSONObject(0);
        JSONObject main = json.getJSONObject("main");
        JSONObject coord = json.getJSONObject("coord");

        mIdCity = json.getInt("id");
        mName = json.getString("name");

        mTemperature = String.format("%.0f ℃", main.getDouble("temp")) ;
        mDescription = Util.capitalize(details.getString("description"));
        mWeatherResIconWhite = Util.setWeatherIcon(details.getInt("id"), json.getJSONObject("sys").getLong("sunrise") * 1000, json.getJSONObject("sys").getLong("sunset") * 1000);
        mWeatherResIconGrey = Util.setWeatherIcon(details.getInt("id"));
        mLatitude = coord.getDouble("lat");
        mLongitude = coord.getDouble("lon");

    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getmTemperature() {
        return mTemperature;
    }

    public void setmTemperature(String mTemperature) {
        this.mTemperature = mTemperature;
    }

    public int getmWeatherIcon() {
        return mWeatherIcon;
    }

    public void setmWeatherIcon(int mWeatherIcon) {
        this.mWeatherIcon = mWeatherIcon;
    }
}
