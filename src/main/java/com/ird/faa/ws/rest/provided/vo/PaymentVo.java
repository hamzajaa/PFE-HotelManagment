package com.ird.faa.ws.rest.provided.vo;

import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;

public class PaymentVo {

    private String id;
    private String invoiceNumber;
    private String amount;
    private String paymentDate;


    private String invoiceNumberMax;
    private String invoiceNumberMin;
    private String amountMax;
    private String amountMin;
    private String paymentDateMax;
    private String paymentDateMin;

    private TypePaymentVo typePaymentVo;
    private BookingVo bookingVo;


    public PaymentVo() {
        super();
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInvoiceNumber() {
        return this.invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getAmount() {
        return this.amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPaymentDate() {
        return this.paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }


    public String getInvoiceNumberMax() {
        return this.invoiceNumberMax;
    }

    public String getInvoiceNumberMin() {
        return this.invoiceNumberMin;
    }

    public void setInvoiceNumberMax(String invoiceNumberMax) {
        this.invoiceNumberMax = invoiceNumberMax;
    }

    public void setInvoiceNumberMin(String invoiceNumberMin) {
        this.invoiceNumberMin = invoiceNumberMin;
    }

    public String getAmountMax() {
        return this.amountMax;
    }

    public String getAmountMin() {
        return this.amountMin;
    }

    public void setAmountMax(String amountMax) {
        this.amountMax = amountMax;
    }

    public void setAmountMin(String amountMin) {
        this.amountMin = amountMin;
    }

    public String getPaymentDateMax() {
        return this.paymentDateMax;
    }

    public String getPaymentDateMin() {
        return this.paymentDateMin;
    }

    public void setPaymentDateMax(String paymentDateMax) {
        this.paymentDateMax = paymentDateMax;
    }

    public void setPaymentDateMin(String paymentDateMin) {
        this.paymentDateMin = paymentDateMin;
    }


    public TypePaymentVo getTypePaymentVo() {
        return this.typePaymentVo;
    }

    public void setTypePaymentVo(TypePaymentVo typePaymentVo) {
        this.typePaymentVo = typePaymentVo;
    }

    public BookingVo getBookingVo() {
        return this.bookingVo;
    }

    public void setBookingVo(BookingVo bookingVo) {
        this.bookingVo = bookingVo;
    }


}
