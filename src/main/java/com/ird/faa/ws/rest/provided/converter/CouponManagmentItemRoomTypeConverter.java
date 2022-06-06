package com.ird.faa.ws.rest.provided.converter;

import com.ird.faa.bean.CouponManagmentItemRoomType;
import com.ird.faa.service.util.DateUtil;
import com.ird.faa.service.util.NumberUtil;
import com.ird.faa.service.util.StringUtil;
import com.ird.faa.ws.rest.provided.vo.CouponManagmentItemRoomTypeVo;
import org.springframework.beans.factory.annotation.Autowired;

public class CouponManagmentItemRoomTypeConverter extends AbstractConverter<CouponManagmentItemRoomType, CouponManagmentItemRoomTypeVo> {

    @Autowired
    private CouponManagmentConverter couponManagmentConverter;
    @Autowired
    private RoomTypeConverter roomTypeConverter;
    private Boolean roomType;
    private Boolean couponManagment;

    public CouponManagmentItemRoomTypeConverter() {
        init(true);
    }

    @Override
    public CouponManagmentItemRoomType toItem(CouponManagmentItemRoomTypeVo vo) {
        if (vo == null) {
            return null;
        } else {
            CouponManagmentItemRoomType item = new CouponManagmentItemRoomType();
            if (StringUtil.isNotEmpty(vo.getId()))
                item.setId(NumberUtil.toLong(vo.getId()));
            if (vo.getArchive() != null)
                item.setArchive(vo.getArchive());
            if (StringUtil.isNotEmpty(vo.getDateArchivage()))
                item.setDateArchivage(DateUtil.parse(vo.getDateArchivage()));
            if (StringUtil.isNotEmpty(vo.getDateCreation()))
                item.setDateCreation(DateUtil.parse(vo.getDateCreation()));
            if (vo.getRoomTypeVo()!= null && this.roomType)
                item.setRoomType(roomTypeConverter.toItem(vo.getRoomTypeVo()));
            if (vo.getCouponManagmentVo() != null && this.couponManagment)
                item.setCouponManagment(couponManagmentConverter.toItem(vo.getCouponManagmentVo()));

            return item;
        }
    }

    @Override
    public CouponManagmentItemRoomTypeVo toVo(CouponManagmentItemRoomType item) {
        if (item == null) {
            return null;
        } else {
            CouponManagmentItemRoomTypeVo vo = new CouponManagmentItemRoomTypeVo();
            if (item.getId() != null)
                vo.setId(NumberUtil.toString(item.getId()));

            if (item.getArchive() != null)
                vo.setArchive(item.getArchive());
            if (item.getDateArchivage() != null)
                vo.setDateArchivage(DateUtil.formateDate(item.getDateArchivage()));
            if (item.getDateCreation() != null)
                vo.setDateCreation(DateUtil.formateDate(item.getDateCreation()));
            if (item.getRoomType() != null && this.roomType) {
                vo.setRoomTypeVo(roomTypeConverter.toVo(item.getRoomType()));
            }
            if (item.getCouponManagment() != null && this.couponManagment) {
                vo.setCouponManagmentVo(couponManagmentConverter.toVo(item.getCouponManagment()));
            }

            return vo;
        }
    }

    public void init(Boolean value) {
        roomType = value;
        couponManagment = value;

    }



    public CouponManagmentConverter getCouponManagmentConverter() {
        return this.couponManagmentConverter;
    }

    public void setCouponManagmentConverter(CouponManagmentConverter couponManagmentConverter) {
        this.couponManagmentConverter = couponManagmentConverter;
    }

    public RoomTypeConverter getRoomTypeConverter() {
        return this.roomTypeConverter;
    }

    public void setRoomTypeConverter(RoomTypeConverter roomConverter) {
        this.roomTypeConverter = roomConverter;
    }

    public boolean isRoomType() {
        return this.roomType;
    }

    public void setRoomType(boolean roomType) {
        this.roomType = roomType;
    }

    public boolean isCouponManagment() {
        return this.couponManagment;
    }

    public void setCouponManagment(boolean couponManagment) {
        this.couponManagment = couponManagment;
    }


}
