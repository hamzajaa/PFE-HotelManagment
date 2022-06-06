package com.ird.faa.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;


@Entity
@Table(name = "couponManagment_item_guest")
public class CouponManagmentItemGuest implements Archivable {

    @Id
    @SequenceGenerator(name = "couponManagment_item_guest_seq", sequenceName = "couponManagment_item_guest_seq",
            allocationSize = 1, initialValue = 10000)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "couponManagment_item_guest_seq")
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
    private Guest guest;
    @ManyToOne
    private CouponManagment couponManagment;

    public CouponManagmentItemGuest() {
        super();
    }


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Guest getGuest() {
        return this.guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
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
        CouponManagmentItemGuest couponManagmentItemGuest = (CouponManagmentItemGuest) o;
        return id != null && id.equals(couponManagmentItemGuest.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

