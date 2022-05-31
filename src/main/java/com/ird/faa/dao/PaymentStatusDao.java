package com.ird.faa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

import com.ird.faa.bean.PaymentStatus;


@Repository
public interface PaymentStatusDao extends JpaRepository<PaymentStatus,Long> {




    PaymentStatus findByCode(String code);

    int deleteByCode(String code);



}
