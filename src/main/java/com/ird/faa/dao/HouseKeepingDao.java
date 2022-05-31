package com.ird.faa.dao;

import com.ird.faa.bean.HouseKeeping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface HouseKeepingDao extends JpaRepository<HouseKeeping,Long> {


    List<HouseKeeping> findByRoomRoomNumber(Long roomNumber);
    int deleteByRoomRoomNumber(Long roomNumber);

    List<HouseKeeping> findByRoomId(Long id);

    int deleteByRoomId(Long id);
    List<HouseKeeping> findByRoomTypeShortCode(String shortCode);
    int deleteByRoomTypeShortCode(String shortCode);

    List<HouseKeeping> findByRoomTypeId(Long id);

    int deleteByRoomTypeId(Long id);

    List<HouseKeeping> findByFloorId(Long id);

    int deleteByFloorId(Long id);
    List<HouseKeeping> findByHouseKeepingStatusName(String name);
    int deleteByHouseKeepingStatusName(String name);

    List<HouseKeeping> findByHouseKeepingStatusId(Long id);

    int deleteByHouseKeepingStatusId(Long id);
    List<HouseKeeping> findByEmployeeCin(String cin);
    int deleteByEmployeeCin(String cin);

    List<HouseKeeping> findByEmployeeId(Long id);

    int deleteByEmployeeId(Long id);


}
