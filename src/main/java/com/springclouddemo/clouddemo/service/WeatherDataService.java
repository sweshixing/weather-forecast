package com.springclouddemo.clouddemo.service;

import com.springclouddemo.clouddemo.entity.WeatherResponse;

public interface WeatherDataService {

    WeatherResponse getDataByCityId(String cityId);

    WeatherResponse getDataByCityName(String cityName);
}
