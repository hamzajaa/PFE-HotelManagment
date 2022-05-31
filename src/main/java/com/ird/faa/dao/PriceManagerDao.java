package com.ird.faa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

import com.ird.faa.bean.PriceManager;


@Repository
public interface PriceManagerDao extends JpaRepository<PriceManager,Long> {





    List<PriceManager> findByRoomTypeShortCode(String shortCode);
    int deleteByRoomTypeShortCode(String shortCode);

    List<PriceManager> findByRoomTypeId(Long id);

    int deleteByRoomTypeId(Long id);


}
