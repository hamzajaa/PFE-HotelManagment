package com.ird.faa.dao;

import com.ird.faa.bean.BookingItemRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BookingItemRoomDao extends JpaRepository<BookingItemRoom,Long> {


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


}
