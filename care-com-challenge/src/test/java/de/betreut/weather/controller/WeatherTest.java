package de.betreut.weather.controller;

import de.betreut.weather.configuration.Config;
import de.betreut.weather.response.City;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = Config.class)
public class WeatherTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
        this.mockMvc = builder.build();
    }

    @Test
    public void testMyMvcController() throws Exception {
        City city = new City();
        ResultMatcher ok = MockMvcResultMatchers.status().isOk();
        ResultMatcher msg = MockMvcResultMatchers.model().attributeExists("city");
        ResultMatcher msg1 = MockMvcResultMatchers.model().attributeExists("weathers");
        ResultMatcher msg2 = MockMvcResultMatchers.model().attributeExists("cityWeather");


        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/");
        this.mockMvc.perform(builder)
                .andExpect(ok)
                .andExpect(msg)
                .andExpect(msg1)
                .andExpect(msg2);
    }
}
