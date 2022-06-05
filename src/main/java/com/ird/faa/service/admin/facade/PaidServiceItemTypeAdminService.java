package com.ird.faa.service.admin.facade;

import com.ird.faa.bean.PaidServiceItemType;
import com.ird.faa.service.core.facade.AbstractService;
import com.ird.faa.ws.rest.provided.vo.PaidServiceItemTypeVo;

import java.util.List;

public interface PaidServiceItemTypeAdminService extends AbstractService<PaidServiceItemType,Long, PaidServiceItemTypeVo> {

    int deleteById(Long id);


    List<PaidServiceItemType> findByRoomTypeId(Long id);

    int deleteByRoomTypeId(Long id);

    List<PaidServiceItemType> findByPaidServiceId(Long id);
    int deleteByPaidServiceId(Long id);

    PaidServiceItemType archiver(PaidServiceItemType paidServiceItemType) ;
    PaidServiceItemType desarchiver(PaidServiceItemType paidServiceItemType);
}
