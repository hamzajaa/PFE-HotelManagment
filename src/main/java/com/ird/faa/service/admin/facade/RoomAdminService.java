package com.ird.faa.service.admin.facade;

import java.util.List;
import com.ird.faa.bean.Room;
import com.ird.faa.ws.rest.provided.vo.RoomVo;
import com.ird.faa.service.core.facade.AbstractService;

public interface RoomAdminService extends AbstractService<Room,Long,RoomVo>{



    /**
    * find Room from database by roomNumber (reference)
    * @param roomNumber - reference of Room
    * @return the founded Room , If no Room were
    *         found in database return  null.
    */
    Room findByRoomNumber(Long roomNumber);

    /**
    * find Room from database by id (PK) or roomNumber (reference)
    * @param id - id of Room
    * @param roomNumber - reference of Room
    * @return the founded Room , If no Room were
    *         found in database return  null.
    */
    Room findByIdOrRoomNumber(Room room);


/**
    * delete Room from database
    * @param id - id of Room to be deleted
    *
    */
    int deleteById(Long id);


    List<Room> findByRoomTypeShortCode(String shortCode);

    int deleteByRoomTypeShortCode(String shortCode);

    List<Room> findByRoomTypeId(Long id);

    int deleteByRoomTypeId(Long id);

    List<Room> findByFloorId(Long id);

    int deleteByFloorId(Long id);

    List<Room> findByHouseKeepingId(Long id);

    int deleteByHouseKeepingId(Long id);



    /**
    * delete Room from database by roomNumber (reference)
    *
    * @param roomNumber - reference of Room to be deleted
    * @return 1 if Room deleted successfully
    */
    int deleteByRoomNumber(Long roomNumber);


    int count();

    Room archiver(Room room) ;
    Room desarchiver(Room room);

}
