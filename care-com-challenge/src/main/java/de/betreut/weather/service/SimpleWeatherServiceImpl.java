package de.betreut.weather.service;

import de.betreut.weather.dao.SimpleWeatherDao;
import de.betreut.weather.model.SimpleWeather;
import de.betreut.weather.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;


@Service("simpleWeatherService")
@Transactional
public class SimpleWeatherServiceImpl implements SimpleWeatherService {

    @Autowired
    private SimpleWeatherDao dao;


    @Override
    public SimpleWeather findById(int id) {
        return dao.findById(id);
    }

    @Override
    public void save(Response response) {
        SimpleWeather simpleWeather = new SimpleWeather();

        simpleWeather.setHumidity(Integer.parseInt(response.getMain().getHumidity()));
        simpleWeather.setWeather_condition(response.getWeather()[0].getDescription());

        simpleWeather.setTemperature(new BigDecimal(response.getMain().getTemp()));
        simpleWeather.setCity_name(response.getName());
        simpleWeather.setWind_speed(new BigDecimal(response.getWind().getSpeed()));

        simpleWeather.setCountry(response.getSys().getCountry());
        simpleWeather.setDate_time(new java.sql.Timestamp(Long.parseLong(response.getDt()) * 1000));


        dao.save(simpleWeather);
    }

    @Override
    public void deleteById(int id) {
        dao.deleteById(id);
    }

    @Override
    public List<SimpleWeather> findAllWeathers() {
        return dao.findAllWeathers();
    }
}
