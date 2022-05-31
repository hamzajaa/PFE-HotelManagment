package com.ird.faa.service.admin.facade;

import com.ird.faa.bean.BookingItemRoom;
import com.ird.faa.service.core.facade.AbstractService;
import com.ird.faa.ws.rest.provided.vo.BookingItemRoomVo;

import java.util.List;

public interface BookingItemRoomAdminService extends AbstractService<BookingItemRoom,Long,BookingItemRoomVo>{





/**
    * delete BookingItemRoom from database
    * @param id - id of BookingItemRoom to be deleted
    *
    */
    int deleteById(Long id);


    List<BookingItemRoom> findByRoomRoomNumber(Long roomNumber);

    int deleteByRoomRoomNumber(Long roomNumber);

    List<BookingItemRoom> findByRoomId(Long id);

    int deleteByRoomId(Long id);

    List<BookingItemRoom> findByBookingId(Long id);

    int deleteByBookingId(Long id);
    List<BookingItemRoom> findByBookingRoomStateCode(String code);

    int deleteByBookingRoomStateCode(String code);

    List<BookingItemRoom> findByBookingRoomStateId(Long id);

    int deleteByBookingRoomStateId(Long id);






    BookingItemRoom archiver(BookingItemRoom bookingItemRoom) ;
    BookingItemRoom desarchiver(BookingItemRoom bookingItemRoom);

}
