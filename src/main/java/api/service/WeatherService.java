package api.service;

import api.weather.Weather;

import java.io.File;
import java.util.List;

public interface WeatherService {

    void insert();

    List<Weather> getLastWeathers();

    Weather getAvgWeather();

    String generateJsonString();

    File generateJsonFile();
}
