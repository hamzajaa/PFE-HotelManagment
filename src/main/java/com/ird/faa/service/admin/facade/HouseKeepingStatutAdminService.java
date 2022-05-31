package com.ird.faa.service.admin.facade;

import java.util.List;
import com.ird.faa.bean.HouseKeepingStatut;
import com.ird.faa.ws.rest.provided.vo.HouseKeepingStatutVo;
import com.ird.faa.service.core.facade.AbstractService;

public interface HouseKeepingStatutAdminService extends AbstractService<HouseKeepingStatut,Long,HouseKeepingStatutVo>{



    /**
    * find HouseKeepingStatut from database by name (reference)
    * @param name - reference of HouseKeepingStatut
    * @return the founded HouseKeepingStatut , If no HouseKeepingStatut were
    *         found in database return  null.
    */
    HouseKeepingStatut findByName(String name);

    /**
    * find HouseKeepingStatut from database by id (PK) or name (reference)
    * @param id - id of HouseKeepingStatut
    * @param name - reference of HouseKeepingStatut
    * @return the founded HouseKeepingStatut , If no HouseKeepingStatut were
    *         found in database return  null.
    */
    HouseKeepingStatut findByIdOrName(HouseKeepingStatut houseKeepingStatut);


/**
    * delete HouseKeepingStatut from database
    * @param id - id of HouseKeepingStatut to be deleted
    *
    */
    int deleteById(Long id);




    /**
    * delete HouseKeepingStatut from database by name (reference)
    *
    * @param name - reference of HouseKeepingStatut to be deleted
    * @return 1 if HouseKeepingStatut deleted successfully
    */
    int deleteByName(String name);




    HouseKeepingStatut archiver(HouseKeepingStatut houseKeepingStatut) ;
    HouseKeepingStatut desarchiver(HouseKeepingStatut houseKeepingStatut);

}
