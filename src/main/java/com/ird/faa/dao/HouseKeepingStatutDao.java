package com.ird.faa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

import com.ird.faa.bean.HouseKeepingStatut;


@Repository
public interface HouseKeepingStatutDao extends JpaRepository<HouseKeepingStatut,Long> {




    HouseKeepingStatut findByName(String name);

    int deleteByName(String name);



}
