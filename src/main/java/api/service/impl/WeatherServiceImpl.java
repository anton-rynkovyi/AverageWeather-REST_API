package api.service.impl;

import api.converter.WeatherConverter;
import api.converter.impl.WeatherApiConverterChooser;
import api.dao.WeatherDao;
import api.service.WeatherService;
import api.weather.Weather;
import api.weather.WeathersEnum;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.sql.Date;
import java.util.*;


@Service
@Scope(value = "prototype")
public class WeatherServiceImpl implements WeatherService {

    @Autowired
    WeatherApiConverterChooser weatherApiConverterChooser;

    @Autowired
    WeatherDao weatherDao;

    public void insert() {
        for (WeathersEnum apiUrl : WeathersEnum.values()) {
            WeatherConverter weatherConverter = weatherApiConverterChooser.getWeatherConverter(apiUrl.toString());
            Weather weather = weatherConverter.convertJsonToWeather();
            weatherDao.insert(weather);
        }
    }

    public List<Weather> getLastWeathers() {
        List<Weather> weatherList = new ArrayList<Weather>();
        for (WeathersEnum weathersEnum : WeathersEnum.values()) {
            weatherList.add(weatherDao.getWeather(weathersEnum.name()));
        }
        return weatherList;
    }

    public Weather getAvgWeather() {
        List<Weather> weatherList = getLastWeathers();
        double tempC = 0;
        double tempF = 0;
        double windMph = 0;
        StringBuilder api_id = new StringBuilder();
        for (int i = 0; i < weatherList.size(); i++) {
            tempC += Double.parseDouble(weatherList.get(i).getTempC());
            tempF += Double.parseDouble(weatherList.get(i).getTempF());
            windMph += Double.parseDouble(weatherList.get(i).getWindMph());
            if (i != weatherList.size()-1) {
                api_id.append(weatherList.get(i).getApiId() + "/");
            } else {
                api_id.append(weatherList.get(i).getApiId());
            }
        }
        tempC /= weatherList.size();
        tempF /= weatherList.size();
        windMph /= weatherList.size();
        Weather weather = new Weather();
        weather.setTempC(String.valueOf(tempC));
        weather.setTempF(String.valueOf(tempF));
        weather.setWindMph(String.valueOf(windMph));
        weather.setApiId(api_id.toString());
        weather.setInsertingDate(new Date(System.currentTimeMillis()));
        return weather;
    }

    public String generateJsonString() {
        Gson gson = new Gson();
        String avgWeather = gson.toJson(getAvgWeather());
        return avgWeather;
    }

    public File generateJsonFile() {
        JsonWriter writer;
        Weather weather = getAvgWeather();
        try {
            writer = new JsonWriter(new FileWriter("average-weather.json"));
            writer.beginObject();
            writer.name("temp_c").value(weather.getTempC());
            writer.name("temp_f").value(weather.getTempF());
            writer.name("wind_mph").value(weather.getWindMph());
            //writer.name("api_id").name(weather.getApiId());
            writer.endObject();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        File file = new File("average-weather.json");
        return file;
    }

}
