package api.converter.impl;

import api.converter.WeatherConverter;
import api.weather.WeathersEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "prototype")
public class WeatherApiConverterChooser {

    @Autowired
    WunderWeatherConverter wunderWeatherConverter;

    @Autowired
    WorldWeatherConverter worldWeatherConverter;

    @Autowired
    ApixuWeatherConverter apixuWeatherConverter;

    public WeatherConverter getWeatherConverter(String apiKey) {
       if (apiKey.equals(WeathersEnum.WUNDER_WEATHER.toString())) {
           return wunderWeatherConverter;
       }
       if (apiKey.equals(WeathersEnum.WORLD_WEATHER.toString())){
           return worldWeatherConverter;
       }
       if (apiKey.equals(WeathersEnum.APIXU.toString())) {
           return apixuWeatherConverter;
       }
       return null;
    }
}
