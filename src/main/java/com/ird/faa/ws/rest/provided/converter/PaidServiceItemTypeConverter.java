package com.ird.faa.ws.rest.provided.converter;

import com.ird.faa.bean.PaidServiceItemType;
import com.ird.faa.service.util.DateUtil;
import com.ird.faa.service.util.NumberUtil;
import com.ird.faa.service.util.StringUtil;
import com.ird.faa.ws.rest.provided.vo.PaidServiceItemTypeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaidServiceItemTypeConverter extends AbstractConverter<PaidServiceItemType, PaidServiceItemTypeVo> {

 
    @Autowired
    private PaidServiceConverter paidServiceConverter;
    @Autowired
    private RoomTypeConverter roomTypeConverter;
    private Boolean roomType;
    private Boolean paidService;

    public PaidServiceItemTypeConverter() {
        init(true);
    }

    @Override
        public PaidServiceItemType toItem(PaidServiceItemTypeVo vo) {
        if (vo == null) {
            return null;
        } else {
            PaidServiceItemType item = new PaidServiceItemType();
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
            if (vo.getPaidServiceVo() != null && this.paidService)
                item.setPaidService(paidServiceConverter.toItem(vo.getPaidServiceVo()));

            return item;
        }
    }

    @Override
    public PaidServiceItemTypeVo toVo(PaidServiceItemType item) {
        if (item == null) {
            return null;
        } else {
            PaidServiceItemTypeVo vo = new PaidServiceItemTypeVo();
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
            if (item.getPaidService() != null && this.paidService) {
                vo.setPaidServiceVo(paidServiceConverter.toVo(item.getPaidService()));
            }

            return vo;
        }
    }

    public void init(Boolean value) {
        roomType = value;
        paidService = value;

    }



    public PaidServiceConverter getPaidServiceConverter() {
        return this.paidServiceConverter;
    }

    public void setPaidServiceConverter(PaidServiceConverter paidServiceConverter) {
        this.paidServiceConverter = paidServiceConverter;
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

    public boolean isPaidService() {
        return this.paidService;
    }

    public void setPaidService(boolean paidService) {
        this.paidService = paidService;
    }




}
