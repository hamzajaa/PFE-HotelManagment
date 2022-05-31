package com.ird.faa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

import com.ird.faa.bean.CouponType;


@Repository
public interface CouponTypeDao extends JpaRepository<CouponType,Long> {




    CouponType findByCode(String code);

    int deleteByCode(String code);



}
