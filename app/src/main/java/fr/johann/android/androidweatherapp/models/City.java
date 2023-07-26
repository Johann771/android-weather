package fr.johann.android.androidweatherapp.models;

import org.json.JSONException;
import org.json.JSONObject;

public class City {
    public String mName;
    public String mDescription;
    public String mTemperature;
    public int mWeatherIcon;
    public int mldCity;
    public double mLatitude;
    public double mLongitude;
    public int mWeatherResIconWhite;

    public City(String mName, String mDescription, String mTemperature, int mWeatherIcon) {
        this.mName = mName;
        this.mDescription = mDescription;
        this.mTemperature = mTemperature;
        this.mWeatherIcon = mWeatherIcon;
    }

    public City(String stringJson) throws JSONException {
        JSONObject json = new JSONObject(stringJson);
        mName = json.getString("name");
        mLatitude = json.getDouble("coord.lat");
        mLongitude = json.getDouble("coord.lon");
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
