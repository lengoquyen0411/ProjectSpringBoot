package com.lengoquyen0411.library.Repo;

import com.lengoquyen0411.library.Model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepo extends JpaRepository<City, Long> {
}
