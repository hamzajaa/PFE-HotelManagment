package com.ird.faa.ws.rest.provided.vo;

public class BookingItemRoomVo {

    private String id;
    private String dateRoomState;
    private Boolean archive;
    private String dateArchivage;
    private String dateCreation;


    private String dateRoomStateMax;
    private String dateRoomStateMin;
    private String dateArchivageMax;
    private String dateArchivageMin;
    private String dateCreationMax;
    private String dateCreationMin;

    private RoomVo roomVo;
    private BookingVo bookingVo;
    private BookingRoomStateVo bookingRoomStateVo;


    public BookingItemRoomVo() {
        super();
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDateRoomState() {
        return this.dateRoomState;
    }

    public void setDateRoomState(String dateRoomState) {
        this.dateRoomState = dateRoomState;
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


    public String getDateRoomStateMax() {
        return this.dateRoomStateMax;
    }

    public String getDateRoomStateMin() {
        return this.dateRoomStateMin;
    }

    public void setDateRoomStateMax(String dateRoomStateMax) {
        this.dateRoomStateMax = dateRoomStateMax;
    }

    public void setDateRoomStateMin(String dateRoomStateMin) {
        this.dateRoomStateMin = dateRoomStateMin;
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


    public RoomVo getRoomVo() {
        return this.roomVo;
    }

    public void setRoomVo(RoomVo roomVo) {
        this.roomVo = roomVo;
    }

    public BookingVo getBookingVo() {
        return this.bookingVo;
    }

    public void setBookingVo(BookingVo bookingVo) {
        this.bookingVo = bookingVo;
    }

    public BookingRoomStateVo getBookingRoomStateVo() {
        return this.bookingRoomStateVo;
    }

    public void setBookingRoomStateVo(BookingRoomStateVo bookingRoomStateVo) {
        this.bookingRoomStateVo = bookingRoomStateVo;
    }


}
