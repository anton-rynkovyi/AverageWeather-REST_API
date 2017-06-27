package api.dao;

import api.weather.Weather;

import java.util.List;

public interface WeatherDao {

    void insert(Weather weather);

    Weather getWeather(String apiId);

}
