package com.ird.faa.service.guest.facade;

import java.util.List;
import com.ird.faa.bean.Payment;
import com.ird.faa.ws.rest.provided.vo.PaymentVo;
import com.ird.faa.service.core.facade.AbstractService;

public interface PaymentGuestService extends AbstractService<Payment,Long,PaymentVo>{





/**
    * delete Payment from database
    * @param id - id of Payment to be deleted
    *
    */
    int deleteById(Long id);


    List<Payment> findByTypePaymentCode(String code);

    int deleteByTypePaymentCode(String code);

    List<Payment> findByTypePaymentId(Long id);

    int deleteByTypePaymentId(Long id);

    List<Payment> findByBookingId(Long id);

    int deleteByBookingId(Long id);







}
