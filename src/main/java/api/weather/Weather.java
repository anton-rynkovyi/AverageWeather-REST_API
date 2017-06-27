package api.weather;

import java.sql.Date;

public class Weather {

    private int weatherId;
    private String tempC;
    private String tempF;
    private String windMph;
    private String apiId;
    private Date insertingDate;


    public Weather() {
    }

    public Weather(String tempC, String tempF, String windMph, String apiId, Date insertingDate) {
        this.tempC = tempC;
        this.tempF = tempF;
        this.windMph = windMph;
        this.apiId = apiId;
        this.insertingDate = insertingDate;
    }

    public int getWeatherId() {
        return weatherId;
    }

    public void setWeatherId(int weatherId) {
        this.weatherId = weatherId;
    }

    public String getTempC() {
        return tempC;
    }

    public void setTempC(String tempC) {
        this.tempC = tempC;
    }

    public String getTempF() {
        return tempF;
    }

    public void setTempF(String tempF) {
        this.tempF = tempF;
    }

    public String getWindMph() {
        return windMph;
    }

    public void setWindMph(String windMph) {
        this.windMph = windMph;
    }

    public String getApiId() {
        return apiId;
    }

    public void setApiId(String apiId) {
        this.apiId = apiId;
    }

    public Date getInsertingDate() {
        return insertingDate;
    }

    public void setInsertingDate(Date insertingDate) {
        this.insertingDate = insertingDate;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "weather_id=" + weatherId +
                ", tempC='" + tempC + '\'' +
                ", tempF='" + tempF + '\'' +
                ", windMph='" + windMph + '\'' +
                ", apiId='" + apiId + '\'' +
                ", insertingDate=" + insertingDate +
                '}';
    }
}
