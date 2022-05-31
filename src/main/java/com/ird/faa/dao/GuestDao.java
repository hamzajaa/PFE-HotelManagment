package com.ird.faa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

import com.ird.faa.bean.Guest;


@Repository
public interface GuestDao extends JpaRepository<Guest,Long> {

    Guest findByUsername(String username);




    List<Guest> findByCountryCode(String code);
    int deleteByCountryCode(String code);

    List<Guest> findByCountryId(Long id);

    int deleteByCountryId(Long id);


}
