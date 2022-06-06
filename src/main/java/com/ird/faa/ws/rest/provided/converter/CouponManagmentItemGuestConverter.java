package com.ird.faa.ws.rest.provided.converter;

import com.ird.faa.bean.CouponManagmentItemGuest;
import com.ird.faa.service.util.DateUtil;
import com.ird.faa.service.util.NumberUtil;
import com.ird.faa.service.util.StringUtil;
import com.ird.faa.ws.rest.provided.vo.CouponManagmentItemGuestVo;
import org.springframework.beans.factory.annotation.Autowired;

public class CouponManagmentItemGuestConverter extends AbstractConverter<CouponManagmentItemGuest, CouponManagmentItemGuestVo> {

    @Autowired
    private CouponManagmentConverter couponManagmentConverter;
    @Autowired
    private GuestConverter guestConverter;
    private Boolean guest;
    private Boolean couponManagment;

    public CouponManagmentItemGuestConverter() {
        init(true);
    }

    @Override
    public CouponManagmentItemGuest toItem(CouponManagmentItemGuestVo vo) {
        if (vo == null) {
            return null;
        } else {
            CouponManagmentItemGuest item = new CouponManagmentItemGuest();
            if (StringUtil.isNotEmpty(vo.getId()))
                item.setId(NumberUtil.toLong(vo.getId()));
            if (vo.getArchive() != null)
                item.setArchive(vo.getArchive());
            if (StringUtil.isNotEmpty(vo.getDateArchivage()))
                item.setDateArchivage(DateUtil.parse(vo.getDateArchivage()));
            if (StringUtil.isNotEmpty(vo.getDateCreation()))
                item.setDateCreation(DateUtil.parse(vo.getDateCreation()));
            if (vo.getGuestVo()!= null && this.guest)
                item.setGuest(guestConverter.toItem(vo.getGuestVo()));
            if (vo.getCouponManagmentVo() != null && this.couponManagment)
                item.setCouponManagment(couponManagmentConverter.toItem(vo.getCouponManagmentVo()));

            return item;
        }
    }

    @Override
    public CouponManagmentItemGuestVo toVo(CouponManagmentItemGuest item) {
        if (item == null) {
            return null;
        } else {
            CouponManagmentItemGuestVo vo = new CouponManagmentItemGuestVo();
            if (item.getId() != null)
                vo.setId(NumberUtil.toString(item.getId()));

            if (item.getArchive() != null)
                vo.setArchive(item.getArchive());
            if (item.getDateArchivage() != null)
                vo.setDateArchivage(DateUtil.formateDate(item.getDateArchivage()));
            if (item.getDateCreation() != null)
                vo.setDateCreation(DateUtil.formateDate(item.getDateCreation()));
            if (item.getGuest() != null && this.guest) {
                vo.setGuestVo(guestConverter.toVo(item.getGuest()));
            }
            if (item.getCouponManagment() != null && this.couponManagment) {
                vo.setCouponManagmentVo(couponManagmentConverter.toVo(item.getCouponManagment()));
            }

            return vo;
        }
    }

    public void init(Boolean value) {
        guest = value;
        couponManagment = value;

    }



    public CouponManagmentConverter getCouponManagmentConverter() {
        return this.couponManagmentConverter;
    }

    public void setCouponManagmentConverter(CouponManagmentConverter couponManagmentConverter) {
        this.couponManagmentConverter = couponManagmentConverter;
    }

    public GuestConverter getGuestConverter() {
        return this.guestConverter;
    }

    public void setGuestConverter(GuestConverter roomConverter) {
        this.guestConverter = roomConverter;
    }

    public boolean isGuest() {
        return this.guest;
    }

    public void setGuest(boolean guest) {
        this.guest = guest;
    }

    public boolean isCouponManagment() {
        return this.couponManagment;
    }

    public void setCouponManagment(boolean couponManagment) {
        this.couponManagment = couponManagment;
    }


}
