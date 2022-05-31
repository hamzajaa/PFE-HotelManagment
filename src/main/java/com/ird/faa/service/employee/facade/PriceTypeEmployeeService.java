package com.ird.faa.service.employee.facade;

import java.util.List;
import com.ird.faa.bean.PriceType;
import com.ird.faa.ws.rest.provided.vo.PriceTypeVo;
import com.ird.faa.service.core.facade.AbstractService;

public interface PriceTypeEmployeeService extends AbstractService<PriceType,Long,PriceTypeVo>{



    /**
    * find PriceType from database by code (reference)
    * @param code - reference of PriceType
    * @return the founded PriceType , If no PriceType were
    *         found in database return  null.
    */
    PriceType findByCode(String code);

    /**
    * find PriceType from database by id (PK) or code (reference)
    * @param id - id of PriceType
    * @param code - reference of PriceType
    * @return the founded PriceType , If no PriceType were
    *         found in database return  null.
    */
    PriceType findByIdOrCode(PriceType priceType);


/**
    * delete PriceType from database
    * @param id - id of PriceType to be deleted
    *
    */
    int deleteById(Long id);




    /**
    * delete PriceType from database by code (reference)
    *
    * @param code - reference of PriceType to be deleted
    * @return 1 if PriceType deleted successfully
    */
    int deleteByCode(String code);




    PriceType archiver(PriceType priceType) ;
    PriceType desarchiver(PriceType priceType);

}
