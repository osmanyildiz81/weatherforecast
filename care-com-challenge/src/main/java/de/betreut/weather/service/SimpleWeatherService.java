package de.betreut.weather.service;

import de.betreut.weather.model.SimpleWeather;
import de.betreut.weather.response.Response;

import java.util.List;


public interface SimpleWeatherService {

    SimpleWeather findById(int id);

    void save(Response response);

    void deleteById(int id);

    List<SimpleWeather> findAllWeathers();

}
