package com.ird.faa.service.admin.facade;

import java.util.List;
import com.ird.faa.bean.BookingStatus;
import com.ird.faa.ws.rest.provided.vo.BookingStatusVo;
import com.ird.faa.service.core.facade.AbstractService;

public interface BookingStatusAdminService extends AbstractService<BookingStatus,Long,BookingStatusVo>{



    /**
    * find BookingStatus from database by code (reference)
    * @param code - reference of BookingStatus
    * @return the founded BookingStatus , If no BookingStatus were
    *         found in database return  null.
    */
    BookingStatus findByCode(String code);

    /**
    * find BookingStatus from database by id (PK) or code (reference)
    * @param id - id of BookingStatus
    * @param code - reference of BookingStatus
    * @return the founded BookingStatus , If no BookingStatus were
    *         found in database return  null.
    */
    BookingStatus findByIdOrCode(BookingStatus bookingStatus);


/**
    * delete BookingStatus from database
    * @param id - id of BookingStatus to be deleted
    *
    */
    int deleteById(Long id);




    /**
    * delete BookingStatus from database by code (reference)
    *
    * @param code - reference of BookingStatus to be deleted
    * @return 1 if BookingStatus deleted successfully
    */
    int deleteByCode(String code);




    BookingStatus archiver(BookingStatus bookingStatus) ;
    BookingStatus desarchiver(BookingStatus bookingStatus);

}
