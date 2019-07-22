package de.betreut.weather.controller;

import de.betreut.weather.forecast.WeatherService;
import de.betreut.weather.model.SimpleWeather;
import de.betreut.weather.response.City;
import de.betreut.weather.response.Response;
import de.betreut.weather.service.SimpleWeatherService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
@SessionAttributes("weather")
public class WeatherController {

    Logger log = Logger.getLogger(WeatherController.class);
    @Autowired
    SimpleWeatherService simpleWeatherService;
    @Autowired
    private WeatherService weatherService;
    @Autowired
    private City city;

    @RequestMapping(value = {"/", "/list"}, method = RequestMethod.GET)
    public String weatherForecast(ModelMap model) throws Exception {
        log.debug("getting retrieveForecastDefaultCities");
        // Send request with GET method and default Headers.


        getDafults(model, new ArrayList<Response>());
        return "weather";

    }

    private void getDafults(ModelMap model, List<Response> cityWeather) throws Exception {

        List<Response> weathers = weatherService.retrieveForecastDefaultCities();
        City city = new City();
        city.setCityName("");
        List<SimpleWeather> simpleWeathers = simpleWeatherService.findAllWeathers();
        model.addAttribute("weathers", weathers);
        model.addAttribute("cityWeather", cityWeather);
        model.addAttribute("simpleWeathers", simpleWeathers);
        model.addAttribute("city", city);
    }

    @RequestMapping(value = {"/", "/list"}, method = RequestMethod.POST)
    public String getWeatherForecast(@ModelAttribute City city, ModelMap model, BindingResult result) throws Exception {
        log.debug("getting getWeatherForecast");
        // Send request with GET method .
        List<Response> weathers = new ArrayList<Response>();
        Response response = weatherService.retrieveForecastByCity(city.getCityName());
        if (response != null) {
            weathers.add(response);
            simpleWeatherService.save(response);
        }
        result.getModel().clear();
        city.setCityName(null);
        getDafults(model, weathers);
        //empty city

        return "weather";

    }
}
