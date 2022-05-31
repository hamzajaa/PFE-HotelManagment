package com.ird.faa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

import com.ird.faa.bean.City;


@Repository
public interface CityDao extends JpaRepository<City,Long> {




    City findByCode(String code);

    int deleteByCode(String code);

    List<City> findByCountryCode(String code);
    int deleteByCountryCode(String code);

    List<City> findByCountryId(Long id);

    int deleteByCountryId(Long id);


}
