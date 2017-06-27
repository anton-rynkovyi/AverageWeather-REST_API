package api.weather;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public enum WeathersEnum {
    WUNDERWEATHER("http://api.wunderground.com/api/389921eae39627cf/conditions/geolookup/lang:UA/pws:0/q/Ukraine/Odessa.json"),
    WORLDWEATHER("http://api.worldweatheronline.com/premium/v1/weather.ashx?key=380a3f42a489462dad5221442172606&q=Odessa,%20Ukraine&format=json&num_of_days=1.json");

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













