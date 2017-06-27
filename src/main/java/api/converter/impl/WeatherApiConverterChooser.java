package api.converter.impl;

import api.converter.WeatherConverter;
import api.weather.WeathersEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WeatherApiConverterChooser {

    @Autowired
    WunderWeatherConverter wunderWeatherConverter;

    @Autowired
    WorldWeatherConverter worldWeatherConverter;

    public WeatherConverter getWeatherConverter(String apiKey) {
       if (apiKey.equals(WeathersEnum.WUNDERWEATHER.toString())) {
           return wunderWeatherConverter;
       }
       if (apiKey.equals(WeathersEnum.WORLDWEATHER.toString())){
           return worldWeatherConverter;
       }
       return null;
    }
}
