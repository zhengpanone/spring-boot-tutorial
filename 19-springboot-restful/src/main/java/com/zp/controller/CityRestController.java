package com.zp.controller;

import com.zp.pojo.City;
import com.zp.response.ApiResult;
import com.zp.service.CityService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CityRestController {
    @Resource
    private CityService cityService;

    @GetMapping(value = "/api/city/{id}")
    public City findOneCity(@PathVariable("id") Long id) {
        return cityService.findCityById(id);
    }

    @GetMapping(value = "/api/city")
    public ApiResult<List<City>> findAllCity() {
        return ApiResult.success(cityService.findAllCity());
    }

    @PostMapping(value = "/api/city")
    public ApiResult<Void> createCity(@RequestBody City city) {
        cityService.saveCity(city);
        return ApiResult.success();
    }

    @PutMapping(value = "/api/city")
    public void modifyCity(@RequestBody City city) {
        cityService.updateCity(city);
    }

    @DeleteMapping(value = "/api/city/{id}")
    public void modifyCity(@PathVariable("id") Long id) {
        cityService.deleteCity(id);
    }
}
