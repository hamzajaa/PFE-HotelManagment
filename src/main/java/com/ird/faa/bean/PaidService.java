package com.ird.faa.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "paid_service")
public class PaidService implements Archivable {

    @Id
    @SequenceGenerator(name = "paid_service_seq", sequenceName = "paid_service_seq",
            allocationSize = 1, initialValue = 10000)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "paid_service_seq")
    private Long id;

    @Column(length = 500)
    private String title;
    @Column(length = 500)
    private String code;
    private BigDecimal price;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column(columnDefinition = "boolean default false")
    private Boolean active = false;
    @Column(columnDefinition = "boolean default false")
    private Boolean archive = false;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date dateArchivage;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date dateCreation;

    @ManyToOne(cascade = CascadeType.ALL)
    private PriceType priceType;

//    @OneToMany(cascade = {CascadeType.ALL})
//    private List<RoomType> roomTypes;


    @OneToMany(mappedBy = "paidService", cascade = CascadeType.ALL)
    List<PaidServiceItemType> paidServiceItemTypes;

    @ManyToOne
    private CouponManagment couponManagment;

    public CouponManagment getCouponManagment() {
        return couponManagment;
    }

    public void setCouponManagment(CouponManagment couponManagment) {
        this.couponManagment = couponManagment;
    }

    public PaidService() {
        super();
    }


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public PriceType getPriceType() {
        return this.priceType;
    }

    public void setPriceType(PriceType priceType) {
        this.priceType = priceType;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getActive() {
        return this.active;
    }

    public void setActive(Boolean active) {
        this.active = active;
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

    public List<PaidServiceItemType> getPaidServiceItemTypes() {
        return this.paidServiceItemTypes;
    }

    public void setPaidServiceItemTypes(List<PaidServiceItemType> paidServiceItemTypes) {
        this.paidServiceItemTypes = paidServiceItemTypes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaidService paidService = (PaidService) o;
        return id != null && id.equals(paidService.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

