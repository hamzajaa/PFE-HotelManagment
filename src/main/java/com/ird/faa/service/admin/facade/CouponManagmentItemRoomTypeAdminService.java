package com.ird.faa.service.admin.facade;

import com.ird.faa.bean.CouponManagmentItemRoomType;
import com.ird.faa.service.core.facade.AbstractService;
import com.ird.faa.ws.rest.provided.vo.CouponManagmentItemRoomTypeVo;

import java.util.List;

public interface CouponManagmentItemRoomTypeAdminService extends AbstractService<CouponManagmentItemRoomType, Long, CouponManagmentItemRoomTypeVo> {
        int deleteById(Long id);


        List<CouponManagmentItemRoomType> findByRoomTypeId(Long id);

        int deleteByRoomTypeId(Long id);

        List<CouponManagmentItemRoomType> findByCouponManagmentId(Long id);
        int deleteByCouponManagmentId(Long id);

    CouponManagmentItemRoomType archiver(CouponManagmentItemRoomType couponManagmentItemRoomType);
    CouponManagmentItemRoomType desarchiver(CouponManagmentItemRoomType couponManagmentItemRoomType);

        }
