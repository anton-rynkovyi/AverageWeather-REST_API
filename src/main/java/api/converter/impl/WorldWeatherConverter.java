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
public class WorldWeatherConverter implements WeatherConverter {

    @Autowired
    HttpWeatherURLConnection httpWeatherURLConnection;

    public Weather convertJsonToWeather() {
        String json = httpWeatherURLConnection.sendGet(WeathersEnum.WORLDWEATHER.toString());
        JsonParser parser = new JsonParser();
        JsonObject mainObject = parser.parse(json).getAsJsonObject();
        JsonObject data = mainObject.getAsJsonObject("data");
        JsonArray currentCondition = data.getAsJsonArray("current_condition");
        JsonElement tempC = currentCondition.get(0).getAsJsonObject().get("temp_C");
        JsonElement tempF = currentCondition.get(0).getAsJsonObject().get("temp_F");
        JsonElement windspeedMiles = currentCondition.get(0).getAsJsonObject().get("windspeedMiles");
        Weather weather = new Weather(tempC.toString().replace("\"",""), tempF.toString().replace("\"",""),
                windspeedMiles.toString().replace("\"",""),
                WeathersEnum.WORLDWEATHER.name(), new Date(System.currentTimeMillis()));
        return weather;
    }
}
