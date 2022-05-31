package com.ird.faa.service.admin.facade;

import com.ird.faa.bean.Booking;
import com.ird.faa.service.core.facade.AbstractService;
import com.ird.faa.ws.rest.provided.vo.BookingVo;

import java.util.List;

public interface BookingAdminService extends AbstractService<Booking,Long,BookingVo>{





/**
    * delete Booking from database
    * @param id - id of Booking to be deleted
    *
    */
    int deleteById(Long id);

    Booking findByBookingNumber(Long bookingNumber);
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

    void updateb(Booking booking);
}
