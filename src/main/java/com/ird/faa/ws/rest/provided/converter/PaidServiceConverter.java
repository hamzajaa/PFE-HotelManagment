package com.ird.faa.ws.rest.provided.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.ird.faa.service.util.*;


import com.ird.faa.bean.PaidService;
import com.ird.faa.ws.rest.provided.vo.PaidServiceVo;

@Component
public class PaidServiceConverter extends AbstractConverter<PaidService, PaidServiceVo> {

    @Autowired
    private RoomTypeConverter roomTypeConverter;
    @Autowired
    private PriceTypeConverter priceTypeConverter;
    private Boolean priceType;
    private Boolean roomTypes;
    private Boolean couponManagment;


    public PaidServiceConverter() {
        init(true);
    }

    @Override
    public PaidService toItem(PaidServiceVo vo) {
        if (vo == null) {
            return null;
        } else {
            PaidService item = new PaidService();
            if (StringUtil.isNotEmpty(vo.getId()))
                item.setId(NumberUtil.toLong(vo.getId()));
            if (StringUtil.isNotEmpty(vo.getTitle()))
                item.setTitle(vo.getTitle());
            if (StringUtil.isNotEmpty(vo.getCode()))
                item.setCode(vo.getCode());
            if (StringUtil.isNotEmpty(vo.getPrice()))
                item.setPrice(NumberUtil.toBigDecimal(vo.getPrice()));
            if (StringUtil.isNotEmpty(vo.getDescription()))
                item.setDescription(vo.getDescription());
            if (vo.getActive() != null)
                item.setActive(vo.getActive());
            if (vo.getArchive() != null)
                item.setArchive(vo.getArchive());
            if (StringUtil.isNotEmpty(vo.getDateArchivage()))
                item.setDateArchivage(DateUtil.parse(vo.getDateArchivage()));
            if (StringUtil.isNotEmpty(vo.getDateCreation()))
                item.setDateCreation(DateUtil.parse(vo.getDateCreation()));
            if (vo.getPriceTypeVo() != null && this.priceType)
                item.setPriceType(priceTypeConverter.toItem(vo.getPriceTypeVo()));

            if (ListUtil.isNotEmpty(vo.getRoomTypesVo()) && this.roomTypes)
                item.setRoomTypes(roomTypeConverter.toItem(vo.getRoomTypesVo()));

            return item;
        }
    }

    @Override
    public PaidServiceVo toVo(PaidService item) {
        if (item == null) {
            return null;
        } else {
            PaidServiceVo vo = new PaidServiceVo();
            if (item.getId() != null)
                vo.setId(NumberUtil.toString(item.getId()));

            if (StringUtil.isNotEmpty(item.getTitle()))
                vo.setTitle(item.getTitle());

            if (StringUtil.isNotEmpty(item.getCode()))
                vo.setCode(item.getCode());

            if (item.getPrice() != null)
                vo.setPrice(NumberUtil.toString(item.getPrice()));

            if (StringUtil.isNotEmpty(item.getDescription()))
                vo.setDescription(item.getDescription());

            if (item.getActive() != null)
                vo.setActive(item.getActive());
            if (item.getArchive() != null)
                vo.setArchive(item.getArchive());
            if (item.getDateArchivage() != null)
                vo.setDateArchivage(DateUtil.formateDate(item.getDateArchivage()));
            if (item.getDateCreation() != null)
                vo.setDateCreation(DateUtil.formateDate(item.getDateCreation()));
            if (item.getPriceType() != null && this.priceType) {
                vo.setPriceTypeVo(priceTypeConverter.toVo(item.getPriceType()));
            }
            if (ListUtil.isNotEmpty(item.getRoomTypes()) && this.roomTypes) {
                roomTypeConverter.init(true);
                roomTypeConverter.setPaidService(false);
                vo.setRoomTypesVo(roomTypeConverter.toVo(item.getRoomTypes()));
                roomTypeConverter.setPaidService(true);
            }

            return vo;
        }
    }

    public void init(Boolean value) {
        priceType = value;
        roomTypes = value;
        couponManagment = value;
    }


    public RoomTypeConverter getRoomTypeConverter() {
        return this.roomTypeConverter;
    }

    public void setRoomTypeConverter(RoomTypeConverter roomTypeConverter) {
        this.roomTypeConverter = roomTypeConverter;
    }

    public PriceTypeConverter getPriceTypeConverter() {
        return this.priceTypeConverter;
    }

    public void setPriceTypeConverter(PriceTypeConverter priceTypeConverter) {
        this.priceTypeConverter = priceTypeConverter;
    }

    public boolean isPriceType() {
        return this.priceType;
    }

    public void setPriceType(boolean priceType) {
        this.priceType = priceType;
    }


    public Boolean isRoomTypes() {
        return this.roomTypes;
    }

    public void setRoomTypes(Boolean roomTypes) {
        this.roomTypes = roomTypes;
    }


    public void setCouponManagment(Boolean couponManagment) {
        this.couponManagment = couponManagment;
    }
}
