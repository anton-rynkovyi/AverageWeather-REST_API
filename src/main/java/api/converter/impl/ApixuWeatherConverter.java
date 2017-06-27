package api.converter.impl;

import api.connections.HttpWeatherURLConnection;
import api.converter.WeatherConverter;
import api.weather.Weather;
import api.weather.WeathersEnum;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
@Scope(value = "prototype")
public class ApixuWeatherConverter implements WeatherConverter{

    @Autowired
    HttpWeatherURLConnection httpWeatherURLConnection;

    public Weather convertJsonToWeather() {
        String json = httpWeatherURLConnection.sendGet(WeathersEnum.APIXU.toString());
        JsonParser parser = new JsonParser();
        JsonObject mainObject = parser.parse(json).getAsJsonObject();
        JsonObject current = mainObject.getAsJsonObject("current");
        JsonElement tempC = current.get("temp_c");
        JsonElement tempF = current.get("temp_f");
        JsonElement windMph = current.get("wind_mph");
        Weather weather = new Weather(tempC.toString(), tempF.toString(), windMph.getAsString(),
                WeathersEnum.APIXU.name(), new Date(System.currentTimeMillis()));
        return weather;
    }
}
