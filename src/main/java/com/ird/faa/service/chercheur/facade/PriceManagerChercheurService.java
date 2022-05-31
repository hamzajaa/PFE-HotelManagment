package com.ird.faa.service.chercheur.facade;

import java.util.List;
import com.ird.faa.bean.PriceManager;
import com.ird.faa.ws.rest.provided.vo.PriceManagerVo;
import com.ird.faa.service.core.facade.AbstractService;

public interface PriceManagerChercheurService extends AbstractService<PriceManager,Long,PriceManagerVo>{





/**
    * delete PriceManager from database
    * @param id - id of PriceManager to be deleted
    *
    */
    int deleteById(Long id);


    List<PriceManager> findByRoomTypeShortCode(String shortCode);

    int deleteByRoomTypeShortCode(String shortCode);

    List<PriceManager> findByRoomTypeId(Long id);

    int deleteByRoomTypeId(Long id);







}
