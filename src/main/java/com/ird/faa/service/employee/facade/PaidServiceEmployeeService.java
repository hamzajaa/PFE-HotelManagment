package com.ird.faa.service.employee.facade;

import java.util.List;
import com.ird.faa.bean.PaidService;
import com.ird.faa.ws.rest.provided.vo.PaidServiceVo;
import com.ird.faa.service.core.facade.AbstractService;

public interface PaidServiceEmployeeService extends AbstractService<PaidService,Long,PaidServiceVo>{



    /**
    * find PaidService from database by code (reference)
    * @param code - reference of PaidService
    * @return the founded PaidService , If no PaidService were
    *         found in database return  null.
    */
    PaidService findByCode(String code);

    /**
    * find PaidService from database by id (PK) or code (reference)
    * @param id - id of PaidService
    * @param code - reference of PaidService
    * @return the founded PaidService , If no PaidService were
    *         found in database return  null.
    */
    PaidService findByIdOrCode(PaidService paidService);


/**
    * delete PaidService from database
    * @param id - id of PaidService to be deleted
    *
    */
    int deleteById(Long id);


    List<PaidService> findByPriceTypeCode(String code);

    int deleteByPriceTypeCode(String code);

    List<PaidService> findByPriceTypeId(Long id);

    int deleteByPriceTypeId(Long id);


    /**
    * delete PaidService from database by code (reference)
    *
    * @param code - reference of PaidService to be deleted
    * @return 1 if PaidService deleted successfully
    */
    int deleteByCode(String code);




    PaidService archiver(PaidService paidService) ;
    PaidService desarchiver(PaidService paidService);

    List<PaidService> findByCouponManagmentId(Long id);

    void deleteByCouponManagmentId(Long id);
}
