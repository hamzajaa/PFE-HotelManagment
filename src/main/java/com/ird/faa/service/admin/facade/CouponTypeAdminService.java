package com.ird.faa.service.admin.facade;

import java.util.List;
import com.ird.faa.bean.CouponType;
import com.ird.faa.ws.rest.provided.vo.CouponTypeVo;
import com.ird.faa.service.core.facade.AbstractService;

public interface CouponTypeAdminService extends AbstractService<CouponType,Long,CouponTypeVo>{



    /**
    * find CouponType from database by code (reference)
    * @param code - reference of CouponType
    * @return the founded CouponType , If no CouponType were
    *         found in database return  null.
    */
    CouponType findByCode(String code);

    /**
    * find CouponType from database by id (PK) or code (reference)
    * @param id - id of CouponType
    * @param code - reference of CouponType
    * @return the founded CouponType , If no CouponType were
    *         found in database return  null.
    */
    CouponType findByIdOrCode(CouponType couponType);


/**
    * delete CouponType from database
    * @param id - id of CouponType to be deleted
    *
    */
    int deleteById(Long id);




    /**
    * delete CouponType from database by code (reference)
    *
    * @param code - reference of CouponType to be deleted
    * @return 1 if CouponType deleted successfully
    */
    int deleteByCode(String code);




    CouponType archiver(CouponType couponType) ;
    CouponType desarchiver(CouponType couponType);

}
