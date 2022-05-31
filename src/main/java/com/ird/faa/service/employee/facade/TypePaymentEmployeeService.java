package com.ird.faa.service.employee.facade;

import java.util.List;
import com.ird.faa.bean.TypePayment;
import com.ird.faa.ws.rest.provided.vo.TypePaymentVo;
import com.ird.faa.service.core.facade.AbstractService;

public interface TypePaymentEmployeeService extends AbstractService<TypePayment,Long,TypePaymentVo>{



    /**
    * find TypePayment from database by code (reference)
    * @param code - reference of TypePayment
    * @return the founded TypePayment , If no TypePayment were
    *         found in database return  null.
    */
    TypePayment findByCode(String code);

    /**
    * find TypePayment from database by id (PK) or code (reference)
    * @param id - id of TypePayment
    * @param code - reference of TypePayment
    * @return the founded TypePayment , If no TypePayment were
    *         found in database return  null.
    */
    TypePayment findByIdOrCode(TypePayment typePayment);


/**
    * delete TypePayment from database
    * @param id - id of TypePayment to be deleted
    *
    */
    int deleteById(Long id);




    /**
    * delete TypePayment from database by code (reference)
    *
    * @param code - reference of TypePayment to be deleted
    * @return 1 if TypePayment deleted successfully
    */
    int deleteByCode(String code);




    TypePayment archiver(TypePayment typePayment) ;
    TypePayment desarchiver(TypePayment typePayment);

}
