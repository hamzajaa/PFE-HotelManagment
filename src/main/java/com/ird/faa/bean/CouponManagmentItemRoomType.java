package com.ird.faa.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;


@Entity
@Table(name = "couponManagment_item_roomType")
public class CouponManagmentItemRoomType implements Archivable {

    @Id
    @SequenceGenerator(name = "couponManagment_item_roomType_seq", sequenceName = "couponManagment_item_roomType_seq",
            allocationSize = 1, initialValue = 10000)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "couponManagment_item_roomType_seq")
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
    private RoomType roomType;
    @ManyToOne
    private CouponManagment couponManagment;

    public CouponManagmentItemRoomType() {
        super();
    }


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoomType getRoomType() {
        return this.roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public CouponManagment getCouponManagment() {
        return this.couponManagment;
    }

    public void setCouponManagment(CouponManagment couponManagment) {
        this.couponManagment = couponManagment;
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
        CouponManagmentItemRoomType couponManagmentItemRoomType = (CouponManagmentItemRoomType) o;
        return id != null && id.equals(couponManagmentItemRoomType.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

