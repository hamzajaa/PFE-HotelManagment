package com.ird.faa.service.employee.facade;

import java.util.List;
import com.ird.faa.bean.City;
import com.ird.faa.ws.rest.provided.vo.CityVo;
import com.ird.faa.service.core.facade.AbstractService;

public interface CityEmployeeService extends AbstractService<City,Long,CityVo>{



    /**
    * find City from database by code (reference)
    * @param code - reference of City
    * @return the founded City , If no City were
    *         found in database return  null.
    */
    City findByCode(String code);

    /**
    * find City from database by id (PK) or code (reference)
    * @param id - id of City
    * @param code - reference of City
    * @return the founded City , If no City were
    *         found in database return  null.
    */
    City findByIdOrCode(City city);


/**
    * delete City from database
    * @param id - id of City to be deleted
    *
    */
    int deleteById(Long id);


    List<City> findByCountryCode(String code);

    int deleteByCountryCode(String code);

    List<City> findByCountryId(Long id);

    int deleteByCountryId(Long id);


    /**
    * delete City from database by code (reference)
    *
    * @param code - reference of City to be deleted
    * @return 1 if City deleted successfully
    */
    int deleteByCode(String code);





}
