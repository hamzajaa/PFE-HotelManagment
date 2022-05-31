package com.ird.faa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

import com.ird.faa.bean.PriceType;


@Repository
public interface PriceTypeDao extends JpaRepository<PriceType,Long> {




    PriceType findByCode(String code);

    int deleteByCode(String code);



}
