package com.ird.faa.ws.rest.provided.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.ird.faa.service.util.*;


import com.ird.faa.bean.BookingItemRoom;
import com.ird.faa.ws.rest.provided.vo.BookingItemRoomVo;

@Component
public class BookingItemRoomConverter extends AbstractConverter<BookingItemRoom, BookingItemRoomVo> {

    @Autowired
    private BookingRoomStateConverter bookingRoomStateConverter;
    @Autowired
    private BookingConverter bookingConverter;
    @Autowired
    private RoomConverter roomConverter;
    private Boolean room;
    private Boolean booking;
    private Boolean bookingRoomState;

    public BookingItemRoomConverter() {
        init(true);
    }

    @Override
    public BookingItemRoom toItem(BookingItemRoomVo vo) {
        if (vo == null) {
            return null;
        } else {
            BookingItemRoom item = new BookingItemRoom();
            if (StringUtil.isNotEmpty(vo.getId()))
                item.setId(NumberUtil.toLong(vo.getId()));
            if (StringUtil.isNotEmpty(vo.getDateRoomState()))
                item.setDateRoomState(DateUtil.parse(vo.getDateRoomState()));
            if (vo.getArchive() != null)
                item.setArchive(vo.getArchive());
            if (StringUtil.isNotEmpty(vo.getDateArchivage()))
                item.setDateArchivage(DateUtil.parse(vo.getDateArchivage()));
            if (StringUtil.isNotEmpty(vo.getDateCreation()))
                item.setDateCreation(DateUtil.parse(vo.getDateCreation()));
            if (vo.getRoomVo() != null && this.room)
                item.setRoom(roomConverter.toItem(vo.getRoomVo()));
            if (vo.getBookingVo() != null && this.booking)
                item.setBooking(bookingConverter.toItem(vo.getBookingVo()));
            if (vo.getBookingRoomStateVo() != null && this.bookingRoomState)
                item.setBookingRoomState(bookingRoomStateConverter.toItem(vo.getBookingRoomStateVo()));


            return item;
        }
    }

    @Override
    public BookingItemRoomVo toVo(BookingItemRoom item) {
        if (item == null) {
            return null;
        } else {
            BookingItemRoomVo vo = new BookingItemRoomVo();
            if (item.getId() != null)
                vo.setId(NumberUtil.toString(item.getId()));

            if (item.getDateRoomState() != null)
                vo.setDateRoomState(DateUtil.formateDate(item.getDateRoomState()));
            if (item.getArchive() != null)
                vo.setArchive(item.getArchive());
            if (item.getDateArchivage() != null)
                vo.setDateArchivage(DateUtil.formateDate(item.getDateArchivage()));
            if (item.getDateCreation() != null)
                vo.setDateCreation(DateUtil.formateDate(item.getDateCreation()));
            if (item.getRoom() != null && this.room) {
                vo.setRoomVo(roomConverter.toVo(item.getRoom()));
            }
            if (item.getBooking() != null && this.booking) {
                vo.setBookingVo(bookingConverter.toVo(item.getBooking()));
            }
            if (item.getBookingRoomState() != null && this.bookingRoomState) {
                vo.setBookingRoomStateVo(bookingRoomStateConverter.toVo(item.getBookingRoomState()));
            }

            return vo;
        }
    }

    public void init(Boolean value) {
        room = value;
        booking = value;
        bookingRoomState = value;
    }


    public BookingRoomStateConverter getBookingRoomStateConverter() {
        return this.bookingRoomStateConverter;
    }

    public void setBookingRoomStateConverter(BookingRoomStateConverter bookingRoomStateConverter) {
        this.bookingRoomStateConverter = bookingRoomStateConverter;
    }

    public BookingConverter getBookingConverter() {
        return this.bookingConverter;
    }

    public void setBookingConverter(BookingConverter bookingConverter) {
        this.bookingConverter = bookingConverter;
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

    public boolean isBooking() {
        return this.booking;
    }

    public void setBooking(boolean booking) {
        this.booking = booking;
    }

    public boolean isBookingRoomState() {
        return this.bookingRoomState;
    }

    public void setBookingRoomState(boolean bookingRoomState) {
        this.bookingRoomState = bookingRoomState;
    }


}
