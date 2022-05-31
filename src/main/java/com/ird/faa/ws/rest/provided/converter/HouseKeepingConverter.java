package com.ird.faa.ws.rest.provided.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.ird.faa.service.util.*;


import com.ird.faa.bean.HouseKeeping;
import com.ird.faa.ws.rest.provided.vo.HouseKeepingVo;

@Component
public class HouseKeepingConverter extends AbstractConverter<HouseKeeping, HouseKeepingVo> {

    @Autowired
    private EmployeeConverter employeeConverter;
    @Autowired
    private RoomTypeConverter roomTypeConverter;
    @Autowired
    private FloorConverter floorConverter;
    @Autowired
    private HouseKeepingStatutConverter houseKeepingStatutConverter;
    @Autowired
    private RoomConverter roomConverter;
    private Boolean room;
    private Boolean roomType;
    private Boolean floor;
    private Boolean houseKeepingStatus;
    private Boolean employee;

    public HouseKeepingConverter() {
        init(true);
    }

    @Override
    public HouseKeeping toItem(HouseKeepingVo vo) {
        if (vo == null) {
            return null;
        } else {
            HouseKeeping item = new HouseKeeping();
            if (StringUtil.isNotEmpty(vo.getId()))
                item.setId(NumberUtil.toLong(vo.getId()));
            if (vo.getArchive() != null)
                item.setArchive(vo.getArchive());
            if (StringUtil.isNotEmpty(vo.getDateArchivage()))
                item.setDateArchivage(DateUtil.parse(vo.getDateArchivage()));
            if (StringUtil.isNotEmpty(vo.getDateCreation()))
                item.setDateCreation(DateUtil.parse(vo.getDateCreation()));
            if (vo.getRoomVo() != null && this.room)
                item.setRoom(roomConverter.toItem(vo.getRoomVo()));
            if (vo.getRoomTypeVo() != null && this.roomType)
                item.setRoomType(roomTypeConverter.toItem(vo.getRoomTypeVo()));
            if (vo.getFloorVo() != null && this.floor)
                item.setFloor(floorConverter.toItem(vo.getFloorVo()));
            if (vo.getHouseKeepingStatusVo() != null && this.houseKeepingStatus)
                item.setHouseKeepingStatus(houseKeepingStatutConverter.toItem(vo.getHouseKeepingStatusVo()));
            if (vo.getEmployeeVo() != null && this.employee)
                item.setEmployee(employeeConverter.toItem(vo.getEmployeeVo()));


            return item;
        }
    }

    @Override
    public HouseKeepingVo toVo(HouseKeeping item) {
        if (item == null) {
            return null;
        } else {
            HouseKeepingVo vo = new HouseKeepingVo();
            if (item.getId() != null)
                vo.setId(NumberUtil.toString(item.getId()));

            if (item.getArchive() != null)
                vo.setArchive(item.getArchive());
            if (item.getDateArchivage() != null)
                vo.setDateArchivage(DateUtil.formateDate(item.getDateArchivage()));
            if (item.getDateCreation() != null)
                vo.setDateCreation(DateUtil.formateDate(item.getDateCreation()));
            if (item.getRoom() != null && this.room) {
                roomConverter.setHouseKeeping(false);
                vo.setRoomVo(roomConverter.toVo(item.getRoom()));
                roomConverter.setHouseKeeping(true);
            }
            if (item.getRoomType() != null && this.roomType) {
                vo.setRoomTypeVo(roomTypeConverter.toVo(item.getRoomType()));
            }
            if (item.getFloor() != null && this.floor) {
                vo.setFloorVo(floorConverter.toVo(item.getFloor()));
            }
            if (item.getHouseKeepingStatus() != null && this.houseKeepingStatus) {
                vo.setHouseKeepingStatusVo(houseKeepingStatutConverter.toVo(item.getHouseKeepingStatus()));
            }
            if (item.getEmployee() != null && this.employee) {
                vo.setEmployeeVo(employeeConverter.toVo(item.getEmployee()));
            }

            return vo;
        }
    }

    public void init(Boolean value) {
        room = value;
        roomType = value;
        floor = value;
        houseKeepingStatus = value;
        employee = value;
    }


    public EmployeeConverter getEmployeeConverter() {
        return this.employeeConverter;
    }

    public void setEmployeeConverter(EmployeeConverter employeeConverter) {
        this.employeeConverter = employeeConverter;
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

    public HouseKeepingStatutConverter getHouseKeepingStatutConverter() {
        return this.houseKeepingStatutConverter;
    }

    public void setHouseKeepingStatutConverter(HouseKeepingStatutConverter houseKeepingStatutConverter) {
        this.houseKeepingStatutConverter = houseKeepingStatutConverter;
    }

    public RoomConverter getRoomConverter() {
        return this.roomConverter;
    }

    public void setRoomConverter(RoomConverter roomConverter) {
        this.roomConverter = roomConverter;
    }

    public boolean isRoom() {
        return this.room;
    }

    public void setRoom(boolean room) {
       this.room = room;
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

    public boolean isHouseKeepingStatus() {
        return this.houseKeepingStatus;
    }

    public void setHouseKeepingStatus(boolean houseKeepingStatus) {
        this.houseKeepingStatus = houseKeepingStatus;
    }

    public boolean isEmployee() {
        return this.employee;
    }

    public void setEmployee(boolean employee) {
        this.employee = employee;
    }


}
