package com.ird.faa.service.guest.facade;

import java.util.List;
import com.ird.faa.bean.Booking;
import com.ird.faa.ws.rest.provided.vo.BookingVo;
import com.ird.faa.service.core.facade.AbstractService;

public interface BookingGuestService extends AbstractService<Booking,Long,BookingVo>{





/**
    * delete Booking from database
    * @param id - id of Booking to be deleted
    *
    */
    int deleteById(Long id);



    List<Booking> findByGuestId(Long id);

    int deleteByGuestId(Long id);
    List<Booking> findByPaymentStatusCode(String code);

    int deleteByPaymentStatusCode(String code);

    List<Booking> findByPaymentStatusId(Long id);

    int deleteByPaymentStatusId(Long id);
    List<Booking> findByBookingStatusCode(String code);

    int deleteByBookingStatusCode(String code);

    List<Booking> findByBookingStatusId(Long id);

    int deleteByBookingStatusId(Long id);

    List<Booking> findByPriceManagerId(Long id);

    int deleteByPriceManagerId(Long id);






    Booking archiver(Booking booking) ;
    Booking desarchiver(Booking booking);

}
