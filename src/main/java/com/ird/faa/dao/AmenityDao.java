package com.ird.faa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

import com.ird.faa.bean.Amenity;


@Repository
public interface AmenityDao extends JpaRepository<Amenity,Long> {




    Amenity findByName(String name);

    int deleteByName(String name);



}
