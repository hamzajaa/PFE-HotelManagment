package com.ird.faa.service.employee.facade;

import java.util.List;
import com.ird.faa.bean.Departement;
import com.ird.faa.ws.rest.provided.vo.DepartementVo;
import com.ird.faa.service.core.facade.AbstractService;

public interface DepartementEmployeeService extends AbstractService<Departement,Long,DepartementVo>{



    /**
    * find Departement from database by code (reference)
    * @param code - reference of Departement
    * @return the founded Departement , If no Departement were
    *         found in database return  null.
    */
    Departement findByCode(String code);

    /**
    * find Departement from database by id (PK) or code (reference)
    * @param id - id of Departement
    * @param code - reference of Departement
    * @return the founded Departement , If no Departement were
    *         found in database return  null.
    */
    Departement findByIdOrCode(Departement departement);


/**
    * delete Departement from database
    * @param id - id of Departement to be deleted
    *
    */
    int deleteById(Long id);




    /**
    * delete Departement from database by code (reference)
    *
    * @param code - reference of Departement to be deleted
    * @return 1 if Departement deleted successfully
    */
    int deleteByCode(String code);





}
