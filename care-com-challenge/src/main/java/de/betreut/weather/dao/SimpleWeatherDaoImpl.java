package de.betreut.weather.dao;

import de.betreut.weather.model.SimpleWeather;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("simpleWeatherDao")
public class SimpleWeatherDaoImpl extends AbstractDao<Integer, SimpleWeather> implements SimpleWeatherDao {

    public SimpleWeather findById(int id) {
        SimpleWeather simpleWeather = getByKey(id);

        return simpleWeather;
    }

    @Override
    public void save(SimpleWeather simpleWeather) {
        persist(simpleWeather);
    }

    @Override
    public void deleteById(int id) {
        SimpleWeather user = (SimpleWeather) getEntityManager()
                .createQuery("SELECT w FROM SimpleWeather w WHERE w.id= ?")
                .setParameter("id", id)
                .getSingleResult();
        delete(user);
    }

    @Override
    public List<SimpleWeather> findAllWeathers() {
        List<SimpleWeather> weathers = getEntityManager()
                .createQuery("SELECT w FROM SimpleWeather w ORDER BY w.date_time DESC")
                .getResultList();
        return weathers;

    }

}
