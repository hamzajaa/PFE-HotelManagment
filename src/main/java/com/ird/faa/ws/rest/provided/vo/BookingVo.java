package com.ird.faa.ws.rest.provided.vo;

import java.util.List;

public class BookingVo {

    private String id;
    private String bookingNumber;
    private String checkIn;
    private String checkOut;
    private String bookingDate;
    private String total;
    private String totalPay;
    private Boolean archive;
    private String dateArchivage;
    private String dateCreation;


    private String bookingNumberMax;
    private String bookingNumberMin;
    private String checkInMax;
    private String checkInMin;
    private String checkOutMax;
    private String checkOutMin;
    private String bookingDateMax;
    private String bookingDateMin;
    private String totalMax;
    private String totalMin;
    private String totalPayMax;
    private String totalPayMin;
    private String dateArchivageMax;
    private String dateArchivageMin;
    private String dateCreationMax;
    private String dateCreationMin;

    private GuestVo guestVo;
    private PaymentStatusVo paymentStatusVo;
    private BookingStatusVo bookingStatusVo;
    private PriceManagerVo priceManagerVo;

    private List<BookingItemRoomVo> bookingItemRoomsVo;
    private List<PaymentVo> paymentsVo;

    public BookingVo() {
        super();
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBookingNumber() {
        return this.bookingNumber;
    }

    public void setBookingNumber(String bookingNumber) {
        this.bookingNumber = bookingNumber;
    }

    public String getCheckIn() {
        return this.checkIn;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public String getCheckOut() {
        return this.checkOut;
    }

    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }

    public String getBookingDate() {
        return this.bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getTotal() {
        return this.total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getTotalPay() {
        return this.totalPay;
    }

    public void setTotalPay(String totalPay) {
        this.totalPay = totalPay;
    }

    public Boolean getArchive() {
        return this.archive;
    }

    public void setArchive(Boolean archive) {
        this.archive = archive;
    }

    public String getDateArchivage() {
        return this.dateArchivage;
    }

    public void setDateArchivage(String dateArchivage) {
        this.dateArchivage = dateArchivage;
    }

    public String getDateCreation() {
        return this.dateCreation;
    }

    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }


    public String getBookingNumberMax() {
        return this.bookingNumberMax;
    }

    public String getBookingNumberMin() {
        return this.bookingNumberMin;
    }

    public void setBookingNumberMax(String bookingNumberMax) {
        this.bookingNumberMax = bookingNumberMax;
    }

    public void setBookingNumberMin(String bookingNumberMin) {
        this.bookingNumberMin = bookingNumberMin;
    }

    public String getCheckInMax() {
        return this.checkInMax;
    }

    public String getCheckInMin() {
        return this.checkInMin;
    }

    public void setCheckInMax(String checkInMax) {
        this.checkInMax = checkInMax;
    }

    public void setCheckInMin(String checkInMin) {
        this.checkInMin = checkInMin;
    }

    public String getCheckOutMax() {
        return this.checkOutMax;
    }

    public String getCheckOutMin() {
        return this.checkOutMin;
    }

    public void setCheckOutMax(String checkOutMax) {
        this.checkOutMax = checkOutMax;
    }

    public void setCheckOutMin(String checkOutMin) {
        this.checkOutMin = checkOutMin;
    }

    public String getBookingDateMax() {
        return this.bookingDateMax;
    }

    public String getBookingDateMin() {
        return this.bookingDateMin;
    }

    public void setBookingDateMax(String bookingDateMax) {
        this.bookingDateMax = bookingDateMax;
    }

    public void setBookingDateMin(String bookingDateMin) {
        this.bookingDateMin = bookingDateMin;
    }

    public String getTotalMax() {
        return this.totalMax;
    }

    public String getTotalMin() {
        return this.totalMin;
    }

    public void setTotalMax(String totalMax) {
        this.totalMax = totalMax;
    }

    public void setTotalMin(String totalMin) {
        this.totalMin = totalMin;
    }

    public String getTotalPayMax() {
        return this.totalPayMax;
    }

    public String getTotalPayMin() {
        return this.totalPayMin;
    }

    public void setTotalPayMax(String totalPayMax) {
        this.totalPayMax = totalPayMax;
    }

    public void setTotalPayMin(String totalPayMin) {
        this.totalPayMin = totalPayMin;
    }

    public String getDateArchivageMax() {
        return this.dateArchivageMax;
    }

    public String getDateArchivageMin() {
        return this.dateArchivageMin;
    }

    public void setDateArchivageMax(String dateArchivageMax) {
        this.dateArchivageMax = dateArchivageMax;
    }

    public void setDateArchivageMin(String dateArchivageMin) {
        this.dateArchivageMin = dateArchivageMin;
    }

    public String getDateCreationMax() {
        return this.dateCreationMax;
    }

    public String getDateCreationMin() {
        return this.dateCreationMin;
    }

    public void setDateCreationMax(String dateCreationMax) {
        this.dateCreationMax = dateCreationMax;
    }

    public void setDateCreationMin(String dateCreationMin) {
        this.dateCreationMin = dateCreationMin;
    }


    public GuestVo getGuestVo() {
        return this.guestVo;
    }

    public void setGuestVo(GuestVo guestVo) {
        this.guestVo = guestVo;
    }

    public PaymentStatusVo getPaymentStatusVo() {
        return this.paymentStatusVo;
    }

    public void setPaymentStatusVo(PaymentStatusVo paymentStatusVo) {
        this.paymentStatusVo = paymentStatusVo;
    }

    public BookingStatusVo getBookingStatusVo() {
        return this.bookingStatusVo;
    }

    public void setBookingStatusVo(BookingStatusVo bookingStatusVo) {
        this.bookingStatusVo = bookingStatusVo;
    }

    public PriceManagerVo getPriceManagerVo() {
        return this.priceManagerVo;
    }

    public void setPriceManagerVo(PriceManagerVo priceManagerVo) {
        this.priceManagerVo = priceManagerVo;
    }


    public List<BookingItemRoomVo> getBookingItemRoomsVo() {
        return this.bookingItemRoomsVo;
    }

    public void setBookingItemRoomsVo(List<BookingItemRoomVo> bookingItemRoomsVo) {
        this.bookingItemRoomsVo = bookingItemRoomsVo;
    }

    public List<PaymentVo> getPaymentsVo() {
        return this.paymentsVo;
    }

    public void setPaymentsVo(List<PaymentVo> paymentsVo) {
        this.paymentsVo = paymentsVo;
    }

}
