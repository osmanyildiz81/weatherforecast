package de.betreut.weather.configuration;

import de.betreut.weather.forecast.WeatherService;
import de.betreut.weather.response.City;
import de.betreut.weather.response.Response;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.util.ArrayList;
import java.util.List;

@Configuration
//@Import(JpaConfiguration.class)
@EnableWebMvc
@ComponentScan(basePackages = "de.betreut.weather")
public class Config extends WebMvcConfigurerAdapter {

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    WeatherService weatherService() {
        return new WeatherService();
    }

    @Bean
    City city() {
        return new City();
    }

    @Bean
    public List<Response> responses() {
        return new ArrayList<Response>();
    }

    /**
     * Configure ViewResolvers to deliver preferred views.
     */
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {

        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        registry.viewResolver(viewResolver);
    }

    /**
     * Configure ResourceHandlers to serve static resources like CSS/ Javascript etc...
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/webjars/**").addResourceLocations("/webjars/");
    }

    @Override
    public void configurePathMatch(PathMatchConfigurer matcher) {
        matcher.setUseRegisteredSuffixPatternMatch(true);
    }
}
