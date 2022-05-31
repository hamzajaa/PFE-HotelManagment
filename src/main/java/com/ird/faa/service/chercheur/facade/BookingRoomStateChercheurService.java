package com.ird.faa.service.chercheur.facade;

import java.util.List;
import com.ird.faa.bean.BookingRoomState;
import com.ird.faa.ws.rest.provided.vo.BookingRoomStateVo;
import com.ird.faa.service.core.facade.AbstractService;

public interface BookingRoomStateChercheurService extends AbstractService<BookingRoomState,Long,BookingRoomStateVo>{



    /**
    * find BookingRoomState from database by code (reference)
    * @param code - reference of BookingRoomState
    * @return the founded BookingRoomState , If no BookingRoomState were
    *         found in database return  null.
    */
    BookingRoomState findByCode(String code);

    /**
    * find BookingRoomState from database by id (PK) or code (reference)
    * @param id - id of BookingRoomState
    * @param code - reference of BookingRoomState
    * @return the founded BookingRoomState , If no BookingRoomState were
    *         found in database return  null.
    */
    BookingRoomState findByIdOrCode(BookingRoomState bookingRoomState);


/**
    * delete BookingRoomState from database
    * @param id - id of BookingRoomState to be deleted
    *
    */
    int deleteById(Long id);




    /**
    * delete BookingRoomState from database by code (reference)
    *
    * @param code - reference of BookingRoomState to be deleted
    * @return 1 if BookingRoomState deleted successfully
    */
    int deleteByCode(String code);





}
