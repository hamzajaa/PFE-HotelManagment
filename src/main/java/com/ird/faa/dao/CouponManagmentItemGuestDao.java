package com.ird.faa.dao;

import com.ird.faa.bean.CouponManagmentItemGuest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CouponManagmentItemGuestDao extends JpaRepository<CouponManagmentItemGuest, Long> {

    List<CouponManagmentItemGuest> findByGuestId(Long id);
    int deleteByGuestId(Long id);


    List<CouponManagmentItemGuest> findByCouponManagmentId(Long id);

    int deleteByCouponManagmentId(Long id);
    
    
}
