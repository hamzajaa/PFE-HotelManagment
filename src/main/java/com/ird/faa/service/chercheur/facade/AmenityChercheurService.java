package com.ird.faa.service.chercheur.facade;

import java.util.List;
import com.ird.faa.bean.Amenity;
import com.ird.faa.ws.rest.provided.vo.AmenityVo;
import com.ird.faa.service.core.facade.AbstractService;

public interface AmenityChercheurService extends AbstractService<Amenity,Long,AmenityVo>{



    /**
    * find Amenity from database by name (reference)
    * @param name - reference of Amenity
    * @return the founded Amenity , If no Amenity were
    *         found in database return  null.
    */
    Amenity findByName(String name);

    /**
    * find Amenity from database by id (PK) or name (reference)
    * @param id - id of Amenity
    * @param name - reference of Amenity
    * @return the founded Amenity , If no Amenity were
    *         found in database return  null.
    */
    Amenity findByIdOrName(Amenity amenity);


/**
    * delete Amenity from database
    * @param id - id of Amenity to be deleted
    *
    */
    int deleteById(Long id);




    /**
    * delete Amenity from database by name (reference)
    *
    * @param name - reference of Amenity to be deleted
    * @return 1 if Amenity deleted successfully
    */
    int deleteByName(String name);


    List<Amenity> findByRoomTypeId(Long id);

    void deleteByRoomTypeId(Long id);
}
