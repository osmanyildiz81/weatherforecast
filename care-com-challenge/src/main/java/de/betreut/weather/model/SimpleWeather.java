package de.betreut.weather.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "simple_weather")
public class SimpleWeather implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty
    @Column(name = "city_name", nullable = false)
    private String city_name;
    @NotNull
    @Column(name = "temperature", nullable = false)
    private BigDecimal temperature;
    @NotEmpty
    @Column(name = "weather_condition", nullable = false)
    private String weather_condition;
    @NotNull
    @Column(name = "humidity", nullable = false)
    private int humidity;
    @NotNull
    @Column(name = "wind_speed", nullable = false)
    private BigDecimal wind_speed;
    @NotEmpty
    @Column(name = "country", nullable = false)
    private String country;
    @NotNull
    @Column(name = "date_time", nullable = false)
    private Timestamp date_time;

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public BigDecimal getTemperature() {
        return temperature;
    }

    public void setTemperature(BigDecimal temperature) {
        this.temperature = temperature;
    }

    public String getWeather_condition() {
        return weather_condition;
    }

    public void setWeather_condition(String weather_condition) {
        this.weather_condition = weather_condition;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public BigDecimal getWind_speed() {
        return wind_speed;
    }

    public void setWind_speed(BigDecimal wind_speed) {
        this.wind_speed = wind_speed;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Timestamp getDate_time() {
        return date_time;
    }

    public void setDate_time(Timestamp date_time) {
        this.date_time = date_time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return getId() + getCity_name() + getTemperature() + getHumidity() + getWind_speed() + getWeather_condition() + getCountry();
    }


}
