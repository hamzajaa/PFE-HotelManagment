package com.ird.faa.dao;

import com.ird.faa.bean.PaidServiceItemType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaidServiceItemTypeDao extends JpaRepository<PaidServiceItemType,Long> {

    List<PaidServiceItemType> findByRoomTypeId(Long id);
    int deleteByRoomTypeId(Long id);


    List<PaidServiceItemType> findByPaidServiceId(Long id);

    int deleteByPaidServiceId(Long id);

}
