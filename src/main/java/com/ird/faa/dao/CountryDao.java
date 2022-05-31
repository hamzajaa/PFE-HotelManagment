package com.ird.faa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

import com.ird.faa.bean.Country;


@Repository
public interface CountryDao extends JpaRepository<Country,Long> {




    Country findByCode(String code);

    int deleteByCode(String code);



}
