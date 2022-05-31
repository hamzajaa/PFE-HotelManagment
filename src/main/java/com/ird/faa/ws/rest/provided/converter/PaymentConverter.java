package com.ird.faa.ws.rest.provided.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.ird.faa.service.util.*;


import com.ird.faa.bean.Payment;
import com.ird.faa.ws.rest.provided.vo.PaymentVo;

@Component
public class PaymentConverter extends AbstractConverter<Payment, PaymentVo> {

    @Autowired
    private TypePaymentConverter typePaymentConverter;
    @Autowired
    private BookingConverter bookingConverter;
    private Boolean typePayment;
    private Boolean booking;

    public PaymentConverter() {
        init(true);
    }

    @Override
    public Payment toItem(PaymentVo vo) {
        if (vo == null) {
            return null;
        } else {
            Payment item = new Payment();
            if (StringUtil.isNotEmpty(vo.getId()))
                item.setId(NumberUtil.toLong(vo.getId()));
            if (StringUtil.isNotEmpty(vo.getInvoiceNumber()))
                item.setInvoiceNumber(NumberUtil.toLong(vo.getInvoiceNumber()));
            if (StringUtil.isNotEmpty(vo.getAmount()))
                item.setAmount(NumberUtil.toBigDecimal(vo.getAmount()));
            if (StringUtil.isNotEmpty(vo.getPaymentDate()))
                item.setPaymentDate(DateUtil.parse(vo.getPaymentDate()));
            if (vo.getTypePaymentVo() != null && this.typePayment)
                item.setTypePayment(typePaymentConverter.toItem(vo.getTypePaymentVo()));
            if (vo.getBookingVo() != null && this.booking)
                item.setBooking(bookingConverter.toItem(vo.getBookingVo()));


            return item;
        }
    }

    @Override
    public PaymentVo toVo(Payment item) {
        if (item == null) {
            return null;
        } else {
            PaymentVo vo = new PaymentVo();
            if (item.getId() != null)
                vo.setId(NumberUtil.toString(item.getId()));

            if (item.getInvoiceNumber() != null)
                vo.setInvoiceNumber(NumberUtil.toString(item.getInvoiceNumber()));

            if (item.getAmount() != null)
                vo.setAmount(NumberUtil.toString(item.getAmount()));

            if (item.getPaymentDate() != null)
                vo.setPaymentDate(DateUtil.formateDate(item.getPaymentDate()));
            if (item.getTypePayment() != null && this.typePayment) {
                vo.setTypePaymentVo(typePaymentConverter.toVo(item.getTypePayment()));
            }
            if (item.getBooking() != null && this.booking) {
                vo.setBookingVo(bookingConverter.toVo(item.getBooking()));
            }

            return vo;
        }
    }

    public void init(Boolean value) {
        typePayment = value;
        booking = value;
    }


    public TypePaymentConverter getTypePaymentConverter() {
        return this.typePaymentConverter;
    }

    public void setTypePaymentConverter(TypePaymentConverter typePaymentConverter) {
        this.typePaymentConverter = typePaymentConverter;
    }

    public BookingConverter getBookingConverter() {
        return this.bookingConverter;
    }

    public void setBookingConverter(BookingConverter bookingConverter) {
        this.bookingConverter = bookingConverter;
    }

    public boolean isTypePayment() {
        return this.typePayment;
    }

    public void setTypePayment(boolean typePayment) {
        this.typePayment = typePayment;
    }

    public boolean isBooking() {
        return this.booking;
    }

    public void setBooking(boolean booking) {
        this.booking = booking;
    }


}
