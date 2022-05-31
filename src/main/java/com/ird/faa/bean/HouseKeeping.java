package com.ird.faa.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;


@Entity
@Table(name = "house_keeping")
public class HouseKeeping implements Archivable {

    @Id
    @SequenceGenerator(name = "house_keeping_seq", sequenceName = "house_keeping_seq",
            allocationSize = 1, initialValue = 10000)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "house_keeping_seq")
    private Long id;

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
    private RoomType roomType;
    @ManyToOne
    private Floor floor;
    @ManyToOne
    private HouseKeepingStatut houseKeepingStatus;
    @ManyToOne
    private Employee employee;


    public HouseKeeping() {
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

    public RoomType getRoomType() {
        return this.roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public Floor getFloor() {
        return this.floor;
    }

    public void setFloor(Floor floor) {
        this.floor = floor;
    }

    public HouseKeepingStatut getHouseKeepingStatus() {
        return this.houseKeepingStatus;
    }

    public void setHouseKeepingStatus(HouseKeepingStatut houseKeepingStatus) {
        this.houseKeepingStatus = houseKeepingStatus;
    }

    public Employee getEmployee() {
        return this.employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
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
        HouseKeeping houseKeeping = (HouseKeeping) o;
        return id != null && id.equals(houseKeeping.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

