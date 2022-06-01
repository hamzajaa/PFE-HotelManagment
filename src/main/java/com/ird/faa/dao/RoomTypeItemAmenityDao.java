package com.ird.faa.dao;

import com.ird.faa.bean.RoomTypeItemAmenity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomTypeItemAmenityDao extends JpaRepository<RoomTypeItemAmenity,Long> {

    List<RoomTypeItemAmenity> findByAmenityId(Long id);
    int deleteByAmenityId(Long id);


    List<RoomTypeItemAmenity> findByRoomTypeId(Long id);

    int deleteByRoomTypeId(Long id);

}
