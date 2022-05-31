package com.ird.faa.service.employee.facade;

import java.util.List;
import com.ird.faa.bean.HouseKeeping;
import com.ird.faa.ws.rest.provided.vo.HouseKeepingVo;
import com.ird.faa.service.core.facade.AbstractService;

public interface HouseKeepingEmployeeService extends AbstractService<HouseKeeping,Long,HouseKeepingVo>{





/**
    * delete HouseKeeping from database
    * @param id - id of HouseKeeping to be deleted
    *
    */
    int deleteById(Long id);


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






    HouseKeeping archiver(HouseKeeping houseKeeping) ;
    HouseKeeping desarchiver(HouseKeeping houseKeeping);

}
