package com.ird.faa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.ird.faa.bean.Payment;


@Repository
public interface PaymentDao extends JpaRepository<Payment, Long> {


    Payment findByInvoiceNumber(Long invoiceNumber);

    List<Payment> findByTypePaymentCode(String code);

    int deleteByTypePaymentCode(String code);

    List<Payment> findByTypePaymentId(Long id);

    int deleteByTypePaymentId(Long id);

    List<Payment> findByBookingId(Long id);

    int deleteByBookingId(Long id);


}
