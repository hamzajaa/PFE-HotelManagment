package com.ird.faa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

import com.ird.faa.bean.CouponManagment;

import org.springframework.data.jpa.repository.Query;

@Repository
public interface CouponManagmentDao extends JpaRepository<CouponManagment,Long> {



    @Query("SELECT item FROM CouponManagment item ORDER BY item.couponFiPeriod ASC")
    List<CouponManagment> findAll();


    List<CouponManagment> findByCouponTypeCode(String code);
    int deleteByCouponTypeCode(String code);

    List<CouponManagment> findByCouponTypeId(Long id);

    int deleteByCouponTypeId(Long id);


}
