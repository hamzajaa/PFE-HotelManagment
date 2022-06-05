package com.ird.faa.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;


@Entity
@Table(name = "paidService_Item_type")
public class PaidServiceItemType implements Archivable {

    @Id
    @SequenceGenerator(name = "paidService_Item_type_seq", sequenceName = "paidService_Item_type_seq",
            allocationSize = 1, initialValue = 10000)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "paidService_Item_type_seq")
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
    private PaidService paidService;

    public PaidServiceItemType() {
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

    public PaidService getPaidService() {
        return this.paidService;
    }

    public void setPaidService(PaidService paidService) {
        this.paidService = paidService;
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
        PaidServiceItemType paidServiceItemRoomType = (PaidServiceItemType) o;
        return id != null && id.equals(paidServiceItemRoomType.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

