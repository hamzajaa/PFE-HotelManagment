package com.ird.faa.dao;

import com.ird.faa.bean.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingDao extends JpaRepository<Booking, Long> {


    @Query("SELECT item FROM Booking item ORDER BY item.bookingDate ASC")
    List<Booking> findAll();


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


}
