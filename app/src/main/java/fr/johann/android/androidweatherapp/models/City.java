package fr.johann.android.androidweatherapp.models;

public class City {
    public String mName;
    public String mDescription;
    public String  mTemperature;
    public int mWeatherIcon;

    public City(String mName, String mDescription, String mTemperature, int mWeatherIcon) {
        this.mName = mName;
        this.mDescription = mDescription;
        this.mTemperature = mTemperature;
        this.mWeatherIcon = mWeatherIcon;
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
