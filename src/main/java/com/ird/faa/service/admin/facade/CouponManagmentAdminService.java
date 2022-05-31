package com.ird.faa.service.admin.facade;

import java.util.List;
import com.ird.faa.bean.CouponManagment;
import com.ird.faa.ws.rest.provided.vo.CouponManagmentVo;
import com.ird.faa.service.core.facade.AbstractService;

public interface CouponManagmentAdminService extends AbstractService<CouponManagment,Long,CouponManagmentVo>{





/**
    * delete CouponManagment from database
    * @param id - id of CouponManagment to be deleted
    *
    */
    int deleteById(Long id);


    List<CouponManagment> findByCouponTypeCode(String code);

    int deleteByCouponTypeCode(String code);

    List<CouponManagment> findByCouponTypeId(Long id);

    int deleteByCouponTypeId(Long id);







}
