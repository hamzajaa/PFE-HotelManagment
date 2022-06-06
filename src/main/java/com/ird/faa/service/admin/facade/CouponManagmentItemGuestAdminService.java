package com.ird.faa.service.admin.facade;

import com.ird.faa.bean.CouponManagmentItemGuest;
import com.ird.faa.service.core.facade.AbstractService;
import com.ird.faa.ws.rest.provided.vo.CouponManagmentItemGuestVo;

import java.util.List;

public interface CouponManagmentItemGuestAdminService extends AbstractService<CouponManagmentItemGuest, Long, CouponManagmentItemGuestVo> {
        int deleteById(Long id);


        List<CouponManagmentItemGuest> findByGuestId(Long id);

        int deleteByGuestId(Long id);

        List<CouponManagmentItemGuest> findByCouponManagmentId(Long id);
        int deleteByCouponManagmentId(Long id);

    CouponManagmentItemGuest archiver(CouponManagmentItemGuest couponManagmentItemGuest);
    CouponManagmentItemGuest desarchiver(CouponManagmentItemGuest couponManagmentItemGuest);

        }
