package de.betreut.weather.dao;

import de.betreut.weather.model.SimpleWeather;

import java.util.List;


public interface SimpleWeatherDao {

    SimpleWeather findById(int id);

    void save(SimpleWeather simpleWeather);

    void deleteById(int id);

    List<SimpleWeather> findAllWeathers();

}

