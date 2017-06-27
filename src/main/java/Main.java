import api.service.WeatherService;
import api.weather.Weather;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        ApplicationContext ctx =
                new FileSystemXmlApplicationContext("src/main/webapp/WEB-INF/spring/spring-config.xml");

        WeatherService weatherService = ctx.getBean(WeatherService.class);
       weatherService.generateJsonFile();
    }
}
