package com.ird.faa.service.guest.facade;

import java.util.List;
import com.ird.faa.bean.Country;
import com.ird.faa.ws.rest.provided.vo.CountryVo;
import com.ird.faa.service.core.facade.AbstractService;

public interface CountryGuestService extends AbstractService<Country,Long,CountryVo>{



    /**
    * find Country from database by code (reference)
    * @param code - reference of Country
    * @return the founded Country , If no Country were
    *         found in database return  null.
    */
    Country findByCode(String code);

    /**
    * find Country from database by id (PK) or code (reference)
    * @param id - id of Country
    * @param code - reference of Country
    * @return the founded Country , If no Country were
    *         found in database return  null.
    */
    Country findByIdOrCode(Country country);


/**
    * delete Country from database
    * @param id - id of Country to be deleted
    *
    */
    int deleteById(Long id);




    /**
    * delete Country from database by code (reference)
    *
    * @param code - reference of Country to be deleted
    * @return 1 if Country deleted successfully
    */
    int deleteByCode(String code);





}
