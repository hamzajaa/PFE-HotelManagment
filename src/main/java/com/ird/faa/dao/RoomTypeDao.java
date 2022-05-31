package com.ird.faa.dao;

import com.ird.faa.bean.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoomTypeDao extends JpaRepository<RoomType,Long> {




    RoomType findByShortCode(String shortCode);

    int deleteByShortCode(String shortCode);



}
