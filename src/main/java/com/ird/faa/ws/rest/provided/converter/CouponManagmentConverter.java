package com.ird.faa.ws.rest.provided.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.ird.faa.service.util.*;


import com.ird.faa.bean.CouponManagment;
import com.ird.faa.ws.rest.provided.vo.CouponManagmentVo;

@Component
public class CouponManagmentConverter extends AbstractConverter<CouponManagment, CouponManagmentVo> {

    @Autowired
    private EmployeeConverter employeeConverter;
    @Autowired
    private RoomTypeConverter roomTypeConverter;
    @Autowired
    private CouponTypeConverter couponTypeConverter;
    @Autowired
    private PaidServiceConverter paidServiceConverter;
    private Boolean couponType;
    private Boolean employees;
    private Boolean roomTypes;
    private Boolean paidServices;


    public CouponManagmentConverter() {
        init(true);
    }

    @Override
    public CouponManagment toItem(CouponManagmentVo vo) {
        if (vo == null) {
            return null;
        } else {
            CouponManagment item = new CouponManagment();
            if (StringUtil.isNotEmpty(vo.getId()))
                item.setId(NumberUtil.toLong(vo.getId()));
            if (StringUtil.isNotEmpty(vo.getTitle()))
                item.setTitle(vo.getTitle());
            if (StringUtil.isNotEmpty(vo.getDescription()))
                item.setDescription(vo.getDescription());
            if (StringUtil.isNotEmpty(vo.getImage()))
                item.setImage(vo.getImage());
            if (StringUtil.isNotEmpty(vo.getCouponDePeriod()))
                item.setCouponDePeriod(DateUtil.parse(vo.getCouponDePeriod()));
            if (StringUtil.isNotEmpty(vo.getCouponFiPeriod()))
                item.setCouponFiPeriod(DateUtil.parse(vo.getCouponFiPeriod()));
            if (StringUtil.isNotEmpty(vo.getCouponCode()))
                item.setCouponCode(vo.getCouponCode());
            if (StringUtil.isNotEmpty(vo.getCouponValue()))
                item.setCouponValue(NumberUtil.toBigDecimal(vo.getCouponValue()));
            if (StringUtil.isNotEmpty(vo.getMinAmount()))
                item.setMinAmount(NumberUtil.toBigDecimal(vo.getMinAmount()));
            if (StringUtil.isNotEmpty(vo.getMaxAmount()))
                item.setMaxAmount(NumberUtil.toBigDecimal(vo.getMaxAmount()));
            if (StringUtil.isNotEmpty(vo.getLimitPerUser()))
                item.setLimitPerUser(NumberUtil.toBigDecimal(vo.getLimitPerUser()));
            if (StringUtil.isNotEmpty(vo.getLimitPerCoupon()))
                item.setLimitPerCoupon(NumberUtil.toBigDecimal(vo.getLimitPerCoupon()));
            if (vo.getCouponTypeVo() != null && this.couponType)
                item.setCouponType(couponTypeConverter.toItem(vo.getCouponTypeVo()));

            if (ListUtil.isNotEmpty(vo.getEmployeesVo()) && this.employees)
                item.setEmployees(employeeConverter.toItem(vo.getEmployeesVo()));
            if (ListUtil.isNotEmpty(vo.getRoomTypesVo()) && this.roomTypes)
                item.setRoomTypes(roomTypeConverter.toItem(vo.getRoomTypesVo()));
            if (ListUtil.isNotEmpty(vo.getPaidServicesVo()) && this.paidServices)
                item.setPaidServices(paidServiceConverter.toItem(vo.getPaidServicesVo()));

            return item;
        }
    }

    @Override
    public CouponManagmentVo toVo(CouponManagment item) {
        if (item == null) {
            return null;
        } else {
            CouponManagmentVo vo = new CouponManagmentVo();
            if (item.getId() != null)
                vo.setId(NumberUtil.toString(item.getId()));

            if (StringUtil.isNotEmpty(item.getTitle()))
                vo.setTitle(item.getTitle());

            if (StringUtil.isNotEmpty(item.getDescription()))
                vo.setDescription(item.getDescription());

            if (StringUtil.isNotEmpty(item.getImage()))
                vo.setImage(item.getImage());

            if (item.getCouponDePeriod() != null)
                vo.setCouponDePeriod(DateUtil.formateDate(item.getCouponDePeriod()));
            if (item.getCouponFiPeriod() != null)
                vo.setCouponFiPeriod(DateUtil.formateDate(item.getCouponFiPeriod()));
            if (StringUtil.isNotEmpty(item.getCouponCode()))
                vo.setCouponCode(item.getCouponCode());

            if (item.getCouponValue() != null)
                vo.setCouponValue(NumberUtil.toString(item.getCouponValue()));

            if (item.getMinAmount() != null)
                vo.setMinAmount(NumberUtil.toString(item.getMinAmount()));

            if (item.getMaxAmount() != null)
                vo.setMaxAmount(NumberUtil.toString(item.getMaxAmount()));

            if (item.getLimitPerUser() != null)
                vo.setLimitPerUser(NumberUtil.toString(item.getLimitPerUser()));

            if (item.getLimitPerCoupon() != null)
                vo.setLimitPerCoupon(NumberUtil.toString(item.getLimitPerCoupon()));

            if (item.getCouponType() != null && this.couponType) {
                vo.setCouponTypeVo(couponTypeConverter.toVo(item.getCouponType()));
            }
            if (ListUtil.isNotEmpty(item.getEmployees()) && this.employees) {
                employeeConverter.init(true);
                employeeConverter.setCouponManagment(false);
                vo.setEmployeesVo(employeeConverter.toVo(item.getEmployees()));
                employeeConverter.setCouponManagment(true);
            }
            if (ListUtil.isNotEmpty(item.getRoomTypes()) && this.roomTypes) {
                roomTypeConverter.init(true);
                roomTypeConverter.setCouponManagment(false);
                vo.setRoomTypesVo(roomTypeConverter.toVo(item.getRoomTypes()));
                roomTypeConverter.setCouponManagment(true);
            }
            if (ListUtil.isNotEmpty(item.getPaidServices()) && this.paidServices) {
                paidServiceConverter.init(true);
                paidServiceConverter.setCouponManagment(false);
                vo.setPaidServicesVo(paidServiceConverter.toVo(item.getPaidServices()));
                paidServiceConverter.setCouponManagment(true);
            }

            return vo;
        }
    }

    public void init(Boolean value) {
        couponType = value;
        employees = value;
        roomTypes = value;
        paidServices = value;
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

    public CouponTypeConverter getCouponTypeConverter() {
        return this.couponTypeConverter;
    }

    public void setCouponTypeConverter(CouponTypeConverter couponTypeConverter) {
        this.couponTypeConverter = couponTypeConverter;
    }

    public PaidServiceConverter getPaidServiceConverter() {
        return this.paidServiceConverter;
    }

    public void setPaidServiceConverter(PaidServiceConverter paidServiceConverter) {
        this.paidServiceConverter = paidServiceConverter;
    }

    public boolean isCouponType() {
        return this.couponType;
    }

    public void setCouponType(boolean couponType) {
        this.couponType = couponType;
    }


    public Boolean isEmployees() {
        return this.employees;
    }

    public void setEmployees(Boolean employees) {
        this.employees = employees;
    }


    public Boolean isRoomTypes() {
        return this.roomTypes;
    }

    public void setRoomTypes(Boolean roomTypes) {
        this.roomTypes = roomTypes;
    }


    public Boolean isPaidServices() {
        return this.paidServices;
    }

    public void setPaidServices(Boolean paidServices) {
        this.paidServices = paidServices;
    }


}
