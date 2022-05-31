package com.ird.faa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

import com.ird.faa.bean.BookingStatus;


@Repository
public interface BookingStatusDao extends JpaRepository<BookingStatus,Long> {




    BookingStatus findByCode(String code);

    int deleteByCode(String code);



}
