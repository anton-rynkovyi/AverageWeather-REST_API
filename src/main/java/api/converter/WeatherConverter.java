package api.converter;

import api.weather.Weather;

public interface WeatherConverter {

    Weather convertJsonToWeather();
}
