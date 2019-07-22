package de.betreut.weather.forecast;


import de.betreut.weather.response.Response;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Configuration
@PropertySource("classpath:application.properties")
public class WeatherService {

    Logger log = Logger.getLogger(WeatherService.class);
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Environment environment;

    @Autowired
    private List<Response> responses;

    /**
     *
     */
    public WeatherService() {
    }

    /**
     * @param cityName
     * @return
     * @throws Exception
     */
    public Response retrieveForecastByCity(String cityName) throws Exception {
        return getResponse(cityName);
    }


    /**
     * @return
     * @throws Exception
     */
    public List<Response> retrieveForecastDefaultCities() throws Exception {

        responses.clear();
        String[] cities = environment.getProperty("default.cities").trim().split(",");
        for (String city : cities) {
            responses.add(getResponse(city));
        }
        return responses;
    }

    /**
     * @param cityName
     * @return
     */
    private Response getResponse(String cityName) {
        StringBuilder stringBuilder = new StringBuilder().append(environment.getRequiredProperty("api.url").trim());
        stringBuilder.append(cityName);
        stringBuilder.append(environment.getRequiredProperty("api.key").trim());
        Response response = null;
        try {
            // Send request with GET method and default Headers.
            response = restTemplate.getForObject(stringBuilder.toString(), Response.class);
            response.getWeather()[0].setIconPath(new StringBuilder().append(environment.getRequiredProperty("icon.url").trim()).append(response.getWeather()[0].getIcon()).append(environment.getRequiredProperty("icon.url.suffix").trim()).toString());
        } catch (RestClientException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
        return response;
    }


}
