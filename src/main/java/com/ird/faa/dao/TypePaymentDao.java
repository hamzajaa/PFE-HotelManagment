package com.ird.faa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

import com.ird.faa.bean.TypePayment;


@Repository
public interface TypePaymentDao extends JpaRepository<TypePayment,Long> {




    TypePayment findByCode(String code);

    int deleteByCode(String code);



}
