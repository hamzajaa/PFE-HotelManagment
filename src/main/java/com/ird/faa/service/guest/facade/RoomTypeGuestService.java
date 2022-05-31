package com.ird.faa.service.guest.facade;

import java.util.List;
import com.ird.faa.bean.RoomType;
import com.ird.faa.ws.rest.provided.vo.RoomTypeVo;
import com.ird.faa.service.core.facade.AbstractService;

public interface RoomTypeGuestService extends AbstractService<RoomType,Long,RoomTypeVo>{



    /**
    * find RoomType from database by shortCode (reference)
    * @param shortCode - reference of RoomType
    * @return the founded RoomType , If no RoomType were
    *         found in database return  null.
    */
    RoomType findByShortCode(String shortCode);

    /**
    * find RoomType from database by id (PK) or shortCode (reference)
    * @param id - id of RoomType
    * @param shortCode - reference of RoomType
    * @return the founded RoomType , If no RoomType were
    *         found in database return  null.
    */
    RoomType findByIdOrShortCode(RoomType roomType);


/**
    * delete RoomType from database
    * @param id - id of RoomType to be deleted
    *
    */
    int deleteById(Long id);




    /**
    * delete RoomType from database by shortCode (reference)
    *
    * @param shortCode - reference of RoomType to be deleted
    * @return 1 if RoomType deleted successfully
    */
    int deleteByShortCode(String shortCode);




    RoomType archiver(RoomType roomType) ;
    RoomType desarchiver(RoomType roomType);

    List<RoomType> findByCouponManagmentId(Long id);

    void deleteByCouponManagmentId(Long id);

    List<RoomType> findByPaidServiceId(Long id);

    void deleteByPaidServiceId(Long id);
}
