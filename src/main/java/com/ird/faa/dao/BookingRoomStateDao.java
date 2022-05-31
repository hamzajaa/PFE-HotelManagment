package com.ird.faa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

import com.ird.faa.bean.BookingRoomState;


@Repository
public interface BookingRoomStateDao extends JpaRepository<BookingRoomState,Long> {




    BookingRoomState findByCode(String code);

    int deleteByCode(String code);



}
