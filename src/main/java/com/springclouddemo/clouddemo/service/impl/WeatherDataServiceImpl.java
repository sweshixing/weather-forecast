package com.springclouddemo.clouddemo.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springclouddemo.clouddemo.entity.WeatherResponse;
import com.springclouddemo.clouddemo.service.WeatherDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherDataServiceImpl implements WeatherDataService {
    @Autowired(required = false)
    public RestTemplate restTemplate;
    public final String weatherAPI = "http://wthrcdn.etouch.cn/weather_mini";



    @Override
    public WeatherResponse getDataByCityId(String cityId) {
//        return null;
        String url = weatherAPI + "?cityId=" + cityId;
        return this.doGetWeatherData(url);
    }


    @Override
    public WeatherResponse getDataByCityName(String cityName) {
//        return null;
        String url = weatherAPI+"?city="+cityName;
        return this.doGetWeatherData(url);
    }

    private WeatherResponse doGetWeatherData(String url) {
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        String strBody = null;

        if (response.getStatusCode() == HttpStatus.valueOf(200)) {
            strBody = response.getBody();
        }

        ObjectMapper mapper = new ObjectMapper();
        WeatherResponse weather = new WeatherResponse();
        try {
            weather = mapper.readValue(strBody, WeatherResponse.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return weather;
    }

}
