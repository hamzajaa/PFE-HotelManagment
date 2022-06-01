package com.ird.faa.service.admin.facade;

import com.ird.faa.bean.RoomTypeItemAmenity;
import com.ird.faa.service.core.facade.AbstractService;
import com.ird.faa.ws.rest.provided.vo.RoomTypeItemAmenityVo;

import java.util.List;

public interface RoomTypeItemAmenityAdminService extends AbstractService<RoomTypeItemAmenity,Long, RoomTypeItemAmenityVo> {

    int deleteById(Long id);


    List<RoomTypeItemAmenity> findByAmenityId(Long id);
    int deleteByAmenityId(Long id);


    List<RoomTypeItemAmenity> findByRoomTypeId(Long id);

    int deleteByRoomTypeId(Long id);

    RoomTypeItemAmenity archiver(RoomTypeItemAmenity roomTypeItemAmenity) ;
    RoomTypeItemAmenity desarchiver(RoomTypeItemAmenity roomTypeItemAmenity);
}
