package com.ird.faa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

import com.ird.faa.bean.PaidService;


@Repository
public interface PaidServiceDao extends JpaRepository<PaidService,Long> {




    PaidService findByCode(String code);

    int deleteByCode(String code);

    List<PaidService> findByPriceTypeCode(String code);
    int deleteByPriceTypeCode(String code);

    List<PaidService> findByPriceTypeId(Long id);

    int deleteByPriceTypeId(Long id);

    List<PaidService> findByCouponManagmentId(Long id);


}
