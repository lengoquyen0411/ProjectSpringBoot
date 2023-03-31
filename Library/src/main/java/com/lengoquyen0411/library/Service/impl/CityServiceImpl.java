package com.lengoquyen0411.library.Service.impl;

import com.lengoquyen0411.library.Model.City;
import com.lengoquyen0411.library.Repo.CityRepo;
import com.lengoquyen0411.library.Service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CityServiceImpl implements CityService {
    @Autowired
    private CityRepo cityRepo;
    @Override
    public List<City> getAll() {
        return cityRepo.findAll();
    }
}
