package com.ird.faa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

import com.ird.faa.bean.Room;


@Repository
public interface RoomDao extends JpaRepository<Room,Long> {




    Room findByRoomNumber(Long roomNumber);

    int deleteByRoomNumber(Long roomNumber);

    List<Room> findByRoomTypeShortCode(String shortCode);
    int deleteByRoomTypeShortCode(String shortCode);

    List<Room> findByRoomTypeId(Long id);

    int deleteByRoomTypeId(Long id);

    List<Room> findByFloorId(Long id);

    int deleteByFloorId(Long id);

    List<Room> findByHouseKeepingId(Long id);

    int deleteByHouseKeepingId(Long id);


}
