package com.ird.faa.dao;

import com.ird.faa.bean.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GenderDao extends JpaRepository<Gender,Long> {




    Gender findByCode(String code);

    int deleteByCode(String code);



}
