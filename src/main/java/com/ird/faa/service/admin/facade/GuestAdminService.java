package com.ird.faa.service.admin.facade;

import java.util.List;
import com.ird.faa.bean.Guest;
import com.ird.faa.ws.rest.provided.vo.GuestVo;
import com.ird.faa.service.core.facade.AbstractService;

public interface GuestAdminService extends AbstractService<Guest,Long,GuestVo>{

    Guest findByUsername(String username);




/**
    * delete Guest from database
    * @param id - id of Guest to be deleted
    *
    */
    int deleteById(Long id);


    List<Guest> findByCountryCode(String code);

    int deleteByCountryCode(String code);

    List<Guest> findByCountryId(Long id);

    int deleteByCountryId(Long id);






    Guest archiver(Guest guest) ;
    Guest desarchiver(Guest guest);

}
