package com.ird.faa.ws.rest.provided.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.ird.faa.service.util.*;


import com.ird.faa.bean.RoomType;
import com.ird.faa.ws.rest.provided.vo.RoomTypeVo;

@Component
public class RoomTypeConverter extends AbstractConverter<RoomType, RoomTypeVo> {

//    @Autowired
//    private AmenityConverter amenityConverter;
//    private Boolean amenitys;

    @Autowired
    private RoomTypeItemAmenityConverter roomTypeItemAmenityConverter;
    private Boolean roomTypeItemAmenitys;

    private Boolean couponManagment;
    private Boolean paidService;


    public RoomTypeConverter() {
        init(true);
    }

    @Override
    public RoomType toItem(RoomTypeVo vo) {
        if (vo == null) {
            return null;
        } else {
            RoomType item = new RoomType();
            if (StringUtil.isNotEmpty(vo.getId())) item.setId(NumberUtil.toLong(vo.getId()));
            if (StringUtil.isNotEmpty(vo.getName())) item.setName(vo.getName());
            if (StringUtil.isNotEmpty(vo.getImage())) item.setImage(vo.getImage());
            if (StringUtil.isNotEmpty(vo.getShortCode())) item.setShortCode(vo.getShortCode());
            if (StringUtil.isNotEmpty(vo.getSlug())) item.setSlug(vo.getSlug());
            if (StringUtil.isNotEmpty(vo.getDescription())) item.setDescription(vo.getDescription());
            if (StringUtil.isNotEmpty(vo.getBaseOccupancy()))
                item.setBaseOccupancy(NumberUtil.toBigDecimal(vo.getBaseOccupancy()));
            if (StringUtil.isNotEmpty(vo.getHigherOcuupancy()))
                item.setHigherOcuupancy(NumberUtil.toBigDecimal(vo.getHigherOcuupancy()));
            if (StringUtil.isNotEmpty(vo.getNumberOfExtraBed()))
                item.setNumberOfExtraBed(NumberUtil.toLong(vo.getNumberOfExtraBed()));
            if (StringUtil.isNotEmpty(vo.getKidsOccupancy()))
                item.setKidsOccupancy(NumberUtil.toLong(vo.getKidsOccupancy()));
            if (StringUtil.isNotEmpty(vo.getBasePrice())) item.setBasePrice(NumberUtil.toBigDecimal(vo.getBasePrice()));
            if (StringUtil.isNotEmpty(vo.getAdditionalPersonPrice()))
                item.setAdditionalPersonPrice(NumberUtil.toBigDecimal(vo.getAdditionalPersonPrice()));
            if (StringUtil.isNotEmpty(vo.getExtraBedPrice()))
                item.setExtraBedPrice(NumberUtil.toBigDecimal(vo.getExtraBedPrice()));
            if (vo.getArchive() != null) item.setArchive(vo.getArchive());
            if (StringUtil.isNotEmpty(vo.getDateArchivage()))
                item.setDateArchivage(DateUtil.parse(vo.getDateArchivage()));
            if (StringUtil.isNotEmpty(vo.getDateCreation())) item.setDateCreation(DateUtil.parse(vo.getDateCreation()));

//            if (ListUtil.isNotEmpty(vo.getAmenitysVo()) && this.amenitys)
//                item.setAmenitys(amenityConverter.toItem(vo.getAmenitysVo()));

            if (ListUtil.isNotEmpty(vo.getRoomTypeItemAmenitysVo()) && this.roomTypeItemAmenitys)
                item.setRoomTypeItemAmenitys(roomTypeItemAmenityConverter.toItem(vo.getRoomTypeItemAmenitysVo()));


            return item;
        }
    }

    @Override
    public RoomTypeVo toVo(RoomType item) {
        if (item == null) {
            return null;
        } else {
            RoomTypeVo vo = new RoomTypeVo();
            if (item.getId() != null) vo.setId(NumberUtil.toString(item.getId()));

            if (StringUtil.isNotEmpty(item.getName())) vo.setName(item.getName());

            if (StringUtil.isNotEmpty(item.getImage())) vo.setImage(item.getImage());

            if (StringUtil.isNotEmpty(item.getShortCode())) vo.setShortCode(item.getShortCode());

            if (StringUtil.isNotEmpty(item.getSlug())) vo.setSlug(item.getSlug());

            if (StringUtil.isNotEmpty(item.getDescription())) vo.setDescription(item.getDescription());

            if (item.getBaseOccupancy() != null) vo.setBaseOccupancy(NumberUtil.toString(item.getBaseOccupancy()));

            if (item.getHigherOcuupancy() != null)
                vo.setHigherOcuupancy(NumberUtil.toString(item.getHigherOcuupancy()));

            if (item.getNumberOfExtraBed() != null)
                vo.setNumberOfExtraBed(NumberUtil.toString(item.getNumberOfExtraBed()));

            if (item.getKidsOccupancy() != null) vo.setKidsOccupancy(NumberUtil.toString(item.getKidsOccupancy()));

            if (item.getBasePrice() != null) vo.setBasePrice(NumberUtil.toString(item.getBasePrice()));

            if (item.getAdditionalPersonPrice() != null)
                vo.setAdditionalPersonPrice(NumberUtil.toString(item.getAdditionalPersonPrice()));

            if (item.getExtraBedPrice() != null) vo.setExtraBedPrice(NumberUtil.toString(item.getExtraBedPrice()));

            if (item.getArchive() != null) vo.setArchive(item.getArchive());
            if (item.getDateArchivage() != null) vo.setDateArchivage(DateUtil.formateDate(item.getDateArchivage()));
            if (item.getDateCreation() != null) vo.setDateCreation(DateUtil.formateDate(item.getDateCreation()));
//            if (ListUtil.isNotEmpty(item.getAmenitys()) && this.amenitys) {
//                amenityConverter.init(true);
//                amenityConverter.setRoomType(false);
//                vo.setAmenitysVo(amenityConverter.toVo(item.getAmenitys()));
//                amenityConverter.setRoomType(true);
//            }
            if (ListUtil.isNotEmpty(item.getRoomTypeItemAmenitys()) && this.roomTypeItemAmenitys) {
                roomTypeItemAmenityConverter.init(true);
                roomTypeItemAmenityConverter.setRoomType(false);
                vo.setRoomTypeItemAmenitysVo(roomTypeItemAmenityConverter.toVo(item.getRoomTypeItemAmenitys()));
                roomTypeItemAmenityConverter.setRoomType(true);
            }

            return vo;
        }
    }

    public void init(Boolean value) {
//        amenitys = value;
        paidService = value;
        couponManagment = value;
        roomTypeItemAmenitys = value;

    }


//    public AmenityConverter getAmenityConverter() {
//        return this.amenityConverter;
//    }
//
//    public void setAmenityConverter(AmenityConverter amenityConverter) {
//        this.amenityConverter = amenityConverter;
//    }

    public RoomTypeItemAmenityConverter getRoomTypeItemAmenityConverter() {
        return roomTypeItemAmenityConverter;
    }

    public void setRoomTypeItemAmenityConverter(RoomTypeItemAmenityConverter roomTypeItemAmenityConverter) {
        this.roomTypeItemAmenityConverter = roomTypeItemAmenityConverter;
    }

//    public Boolean isAmenitys() {
//        return this.amenitys;
//    }
//
//    public void setAmenitys(Boolean amenitys) {
//        this.amenitys = amenitys;
//    }

    public Boolean isRoomTypeItemAmenitys() {
        return this.roomTypeItemAmenitys;
    }

    public void setRoomTypeItemAmenitys(Boolean roomTypeItemAmenitys) {
        this.roomTypeItemAmenitys = roomTypeItemAmenitys;
    }


    public void setPaidService(Boolean paidService) {
        this.paidService = paidService;
    }

    public void setCouponManagment(Boolean couponManagment) {
        this.couponManagment = couponManagment;
    }
}
