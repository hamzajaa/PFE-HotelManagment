package com.ird.faa.ws.rest.provided.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.ird.faa.service.util.*;


import com.ird.faa.bean.Room;
import com.ird.faa.ws.rest.provided.vo.RoomVo;

@Component
public class RoomConverter extends AbstractConverter<Room, RoomVo> {

    @Autowired
    private RoomTypeConverter roomTypeConverter;
    @Autowired
    private FloorConverter floorConverter;
    @Autowired
    private HouseKeepingConverter houseKeepingConverter;
    private Boolean roomType;
    private Boolean floor;
    private Boolean houseKeeping;


    public RoomConverter() {
        init(true);
    }

    @Override
    public Room toItem(RoomVo vo) {
        if (vo == null) {
            return null;
        } else {
            Room item = new Room();
            if (StringUtil.isNotEmpty(vo.getId()))
                item.setId(NumberUtil.toLong(vo.getId()));
            if (StringUtil.isNotEmpty(vo.getRoomNumber()))
                item.setRoomNumber(NumberUtil.toLong(vo.getRoomNumber()));
            if (StringUtil.isNotEmpty(vo.getLibelle()))
                item.setLibelle(vo.getLibelle());
            if (vo.getArchive() != null)
                item.setArchive(vo.getArchive());
            if (StringUtil.isNotEmpty(vo.getDateArchivage()))
                item.setDateArchivage(DateUtil.parse(vo.getDateArchivage()));
            if (StringUtil.isNotEmpty(vo.getDateCreation()))
                item.setDateCreation(DateUtil.parse(vo.getDateCreation()));
            if (vo.getRoomTypeVo() != null && this.roomType)
                item.setRoomType(roomTypeConverter.toItem(vo.getRoomTypeVo()));
            if (vo.getFloorVo() != null && this.floor)
                item.setFloor(floorConverter.toItem(vo.getFloorVo()));
            if (vo.getHouseKeepingVo() != null && this.houseKeeping)
                item.setHouseKeeping(houseKeepingConverter.toItem(vo.getHouseKeepingVo()));


            return item;
        }
    }

    @Override
    public RoomVo toVo(Room item) {
        if (item == null) {
            return null;
        } else {
            RoomVo vo = new RoomVo();
            if (item.getId() != null)
                vo.setId(NumberUtil.toString(item.getId()));

            if (item.getRoomNumber() != null)
                vo.setRoomNumber(NumberUtil.toString(item.getRoomNumber()));

            if (StringUtil.isNotEmpty(item.getLibelle()))
                vo.setLibelle(item.getLibelle());

            if (item.getArchive() != null)
                vo.setArchive(item.getArchive());
            if (item.getDateArchivage() != null)
                vo.setDateArchivage(DateUtil.formateDate(item.getDateArchivage()));
            if (item.getDateCreation() != null)
                vo.setDateCreation(DateUtil.formateDate(item.getDateCreation()));
            if (item.getRoomType() != null && this.roomType) {
                vo.setRoomTypeVo(roomTypeConverter.toVo(item.getRoomType()));
            }
            if (item.getFloor() != null && this.floor) {
                vo.setFloorVo(floorConverter.toVo(item.getFloor()));
            }
            if (item.getHouseKeeping() != null && this.houseKeeping) {
                houseKeepingConverter.setRoom(false);
                vo.setHouseKeepingVo(houseKeepingConverter.toVo(item.getHouseKeeping()));
                houseKeepingConverter.setRoom(true);
            }

            return vo;
        }
    }

    public void init(Boolean value) {
        roomType = value;
        floor = value;
        houseKeeping = value;
    }


    public RoomTypeConverter getRoomTypeConverter() {
        return this.roomTypeConverter;
    }

    public void setRoomTypeConverter(RoomTypeConverter roomTypeConverter) {
        this.roomTypeConverter = roomTypeConverter;
    }

    public FloorConverter getFloorConverter() {
        return this.floorConverter;
    }

    public void setFloorConverter(FloorConverter floorConverter) {
        this.floorConverter = floorConverter;
    }

    public HouseKeepingConverter getHouseKeepingConverter() {
        return this.houseKeepingConverter;
    }

    public void setHouseKeepingConverter(HouseKeepingConverter houseKeepingConverter) {
        this.houseKeepingConverter = houseKeepingConverter;
    }

    public boolean isRoomType() {
        return this.roomType;
    }

    public void setRoomType(boolean roomType) {
        this.roomType = roomType;
    }

    public boolean isFloor() {
        return this.floor;
    }

    public void setFloor(boolean floor) {
        this.floor = floor;
    }

    public boolean isHouseKeeping() {
        return this.houseKeeping;
    }

    public void setHouseKeeping(boolean houseKeeping) {
        this.houseKeeping = houseKeeping;
    }


}
