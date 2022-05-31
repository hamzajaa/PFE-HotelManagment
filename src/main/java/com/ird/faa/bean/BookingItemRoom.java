package com.ird.faa.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;


@Entity
@Table(name = "booking_item_room")
public class BookingItemRoom implements Archivable {

    @Id
    @SequenceGenerator(name = "booking_item_room_seq", sequenceName = "booking_item_room_seq",
            allocationSize = 1, initialValue = 10000)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "booking_item_room_seq")
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date dateRoomState;
    @Column(columnDefinition = "boolean default false")
    private Boolean archive = false;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date dateArchivage;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date dateCreation;

    @ManyToOne
    private Room room;
    @ManyToOne
    private Booking booking;
    @ManyToOne
    private BookingRoomState bookingRoomState;


    public BookingItemRoom() {
        super();
    }


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Room getRoom() {
        return this.room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Booking getBooking() {
        return this.booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public BookingRoomState getBookingRoomState() {
        return this.bookingRoomState;
    }

    public void setBookingRoomState(BookingRoomState bookingRoomState) {
        this.bookingRoomState = bookingRoomState;
    }

    public Date getDateRoomState() {
        return this.dateRoomState;
    }

    public void setDateRoomState(Date dateRoomState) {
        this.dateRoomState = dateRoomState;
    }

    public Boolean getArchive() {
        return this.archive;
    }

    public void setArchive(Boolean archive) {
        this.archive = archive;
    }

    public Date getDateArchivage() {
        return this.dateArchivage;
    }

    public void setDateArchivage(Date dateArchivage) {
        this.dateArchivage = dateArchivage;
    }

    public Date getDateCreation() {
        return this.dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookingItemRoom bookingItemRoom = (BookingItemRoom) o;
        return id != null && id.equals(bookingItemRoom.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

