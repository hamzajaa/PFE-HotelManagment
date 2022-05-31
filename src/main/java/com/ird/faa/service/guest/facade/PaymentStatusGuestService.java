package com.ird.faa.service.guest.facade;

import java.util.List;
import com.ird.faa.bean.PaymentStatus;
import com.ird.faa.ws.rest.provided.vo.PaymentStatusVo;
import com.ird.faa.service.core.facade.AbstractService;

public interface PaymentStatusGuestService extends AbstractService<PaymentStatus,Long,PaymentStatusVo>{



    /**
    * find PaymentStatus from database by code (reference)
    * @param code - reference of PaymentStatus
    * @return the founded PaymentStatus , If no PaymentStatus were
    *         found in database return  null.
    */
    PaymentStatus findByCode(String code);

    /**
    * find PaymentStatus from database by id (PK) or code (reference)
    * @param id - id of PaymentStatus
    * @param code - reference of PaymentStatus
    * @return the founded PaymentStatus , If no PaymentStatus were
    *         found in database return  null.
    */
    PaymentStatus findByIdOrCode(PaymentStatus paymentStatus);


/**
    * delete PaymentStatus from database
    * @param id - id of PaymentStatus to be deleted
    *
    */
    int deleteById(Long id);




    /**
    * delete PaymentStatus from database by code (reference)
    *
    * @param code - reference of PaymentStatus to be deleted
    * @return 1 if PaymentStatus deleted successfully
    */
    int deleteByCode(String code);




    PaymentStatus archiver(PaymentStatus paymentStatus) ;
    PaymentStatus desarchiver(PaymentStatus paymentStatus);

}
