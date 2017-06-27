package api.controllers;

import api.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;

@Controller
@Scope(value = "prototype")
public class WeatherController {

    @Autowired
    WeatherService weatherService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public String getWeather() {
        try {
            return weatherService.generateJsonString(); //generateJsonFile();
        } catch (EmptyResultDataAccessException e) {
            weatherService.insert();
            return weatherService.generateJsonString();
        }
    }
}
