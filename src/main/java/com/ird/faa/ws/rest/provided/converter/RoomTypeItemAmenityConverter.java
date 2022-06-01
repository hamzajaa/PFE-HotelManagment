package com.ird.faa.ws.rest.provided.converter;

import com.ird.faa.bean.RoomTypeItemAmenity;
import com.ird.faa.service.util.DateUtil;
import com.ird.faa.service.util.NumberUtil;
import com.ird.faa.service.util.StringUtil;
import com.ird.faa.ws.rest.provided.vo.RoomTypeItemAmenityVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoomTypeItemAmenityConverter extends AbstractConverter<RoomTypeItemAmenity, RoomTypeItemAmenityVo> {

 
    @Autowired
    private RoomTypeConverter roomTypeConverter;
    @Autowired
    private AmenityConverter amenityConverter;
    private Boolean amenity;
    private Boolean roomType;

    public RoomTypeItemAmenityConverter() {
        init(true);
    }

    @Override
        public RoomTypeItemAmenity toItem(RoomTypeItemAmenityVo vo) {
        if (vo == null) {
            return null;
        } else {
            RoomTypeItemAmenity item = new RoomTypeItemAmenity();
            if (StringUtil.isNotEmpty(vo.getId()))
                item.setId(NumberUtil.toLong(vo.getId()));
            if (vo.getArchive() != null)
                item.setArchive(vo.getArchive());
            if (StringUtil.isNotEmpty(vo.getDateArchivage()))
                item.setDateArchivage(DateUtil.parse(vo.getDateArchivage()));
            if (StringUtil.isNotEmpty(vo.getDateCreation()))
                item.setDateCreation(DateUtil.parse(vo.getDateCreation()));
            if (vo.getAmenityVo()!= null && this.amenity)
                item.setAmenity(amenityConverter.toItem(vo.getAmenityVo()));
            if (vo.getRoomTypeVo() != null && this.roomType)
                item.setRoomType(roomTypeConverter.toItem(vo.getRoomTypeVo()));

            return item;
        }
    }

    @Override
    public RoomTypeItemAmenityVo toVo(RoomTypeItemAmenity item) {
        if (item == null) {
            return null;
        } else {
            RoomTypeItemAmenityVo vo = new RoomTypeItemAmenityVo();
            if (item.getId() != null)
                vo.setId(NumberUtil.toString(item.getId()));

            if (item.getArchive() != null)
                vo.setArchive(item.getArchive());
            if (item.getDateArchivage() != null)
                vo.setDateArchivage(DateUtil.formateDate(item.getDateArchivage()));
            if (item.getDateCreation() != null)
                vo.setDateCreation(DateUtil.formateDate(item.getDateCreation()));
            if (item.getAmenity() != null && this.amenity) {
                vo.setAmenityVo(amenityConverter.toVo(item.getAmenity()));
            }
            if (item.getRoomType() != null && this.roomType) {
                vo.setRoomTypeVo(roomTypeConverter.toVo(item.getRoomType()));
            }

            return vo;
        }
    }

    public void init(Boolean value) {
        amenity = value;
        roomType = value;

    }



    public RoomTypeConverter getRoomTypeConverter() {
        return this.roomTypeConverter;
    }

    public void setRoomTypeConverter(RoomTypeConverter roomTypeConverter) {
        this.roomTypeConverter = roomTypeConverter;
    }

    public AmenityConverter getAmenityConverter() {
        return this.amenityConverter;
    }

    public void setAmenityConverter(AmenityConverter roomConverter) {
        this.amenityConverter = roomConverter;
    }

    public boolean isAmenity() {
        return this.amenity;
    }

    public void setAmenity(boolean amenity) {
        this.amenity = amenity;
    }

    public boolean isRoomType() {
        return this.roomType;
    }

    public void setRoomType(boolean roomType) {
        this.roomType = roomType;
    }




}
