package api.weather;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public enum WeathersEnum {
    WUNDER_WEATHER("http://api.wunderground.com/api/389921eae39627cf/conditions/geolookup/lang:UA/pws:0/q/Ukraine/Odessa.json"),
    WORLD_WEATHER("http://api.worldweatheronline.com/premium/v1/weather.ashx?key=380a3f42a489462dad5221442172606&q=Odessa,%20Ukraine&format=json&num_of_days=1.json"),
    APIXU("http://api.apixu.com/v1/current.json?key=4ce12cb62add42e0b6f50408172706&q=Odessa,%20ua");
    //OPEN_WEATHER_MAP("http://api.openweathermap.org/data/2.5/forecast?id=698740&APPID=3d7a7f53ee89151fb8c55ee34e0bae25.json");

    private String apiUrl;

    WeathersEnum(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    static {
        List<WeathersEnum> statuses = new ArrayList();
        for (WeathersEnum status : values()) {
            statuses.add(status);
        }
    }

    @Override
    public String toString() {
        return apiUrl;
    }
}













