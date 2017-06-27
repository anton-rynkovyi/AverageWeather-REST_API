package api.controllers;

import api.service.WeatherService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class WeatherDataJob extends QuartzJobBean {

    @Autowired
    WeatherService weatherService;

    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            weatherService.insert();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
