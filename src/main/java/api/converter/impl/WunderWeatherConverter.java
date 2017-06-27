package api.converter.impl;

import api.connections.HttpWeatherURLConnection;
import api.converter.WeatherConverter;
import api.weather.Weather;
import api.weather.WeathersEnum;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.sql.Date;


@Component
@Scope(value = "prototype")
public class WunderWeatherConverter implements WeatherConverter {

    @Autowired
    HttpWeatherURLConnection httpWeatherURLConnection;

    public Weather convertJsonToWeather(){
        String json = httpWeatherURLConnection.sendGet(WeathersEnum.WUNDERWEATHER.toString());
        JsonParser parser = new JsonParser();
        JsonObject mainObject = parser.parse(json).getAsJsonObject();
        JsonObject currentObservation = mainObject.getAsJsonObject("current_observation");
        JsonElement tempC = currentObservation.get("temp_c");
        JsonElement tempF = currentObservation.get("temp_f");
        JsonElement windMph = currentObservation.get("wind_mph");
        Weather weather = new Weather(tempC.toString(), tempF.toString(), windMph.toString(),
                WeathersEnum.WUNDERWEATHER.name(), new Date(System.currentTimeMillis()));
        return weather;
    }
}
