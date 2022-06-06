package com.ird.faa.dao;

import com.ird.faa.bean.CouponManagmentItemRoomType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CouponManagmentItemRoomTypeDao extends JpaRepository<CouponManagmentItemRoomType, Long> {

    List<CouponManagmentItemRoomType> findByRoomTypeId(Long id);
    int deleteByRoomTypeId(Long id);


    List<CouponManagmentItemRoomType> findByCouponManagmentId(Long id);

    int deleteByCouponManagmentId(Long id);
    
    
}
