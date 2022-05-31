package com.ird.faa.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "room_type")
public class RoomType implements Archivable {

    @Id
    @SequenceGenerator(name = "room_type_seq", sequenceName = "room_type_seq",
            allocationSize = 1, initialValue = 10000)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "room_type_seq")
    private Long id;

    @Column(length = 500)
    private String name;
    @Column(length = 500)
    private String image;
    @Column(length = 500)
    private String shortCode;
    @Column(length = 500)
    private String slug;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String description;
    private BigDecimal baseOccupancy;
    private BigDecimal higherOcuupancy;
    private Long numberOfExtraBed;
    private Long kidsOccupancy;
    private BigDecimal basePrice;
    private BigDecimal additionalPersonPrice;
    private BigDecimal extraBedPrice;
    @Column(columnDefinition = "boolean default false")
    private Boolean archive = false;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date dateArchivage;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date dateCreation;

    @ManyToOne
    private PaidService paidService;

    @OneToMany(mappedBy = "roomType")
    private List<RoomTypeItemAmenity> roomTypeItemAmenities;

    public List<RoomTypeItemAmenity> getRoomTypeItemAmenities() {
        return roomTypeItemAmenities;
    }

    public void setRoomTypeItemAmenities(List<RoomTypeItemAmenity> roomTypeItemAmenities) {
        this.roomTypeItemAmenities = roomTypeItemAmenities;
    }


    public PaidService getPaidService() {
        return paidService;
    }

    public void setPaidService(PaidService paidService) {
        this.paidService = paidService;
    }

    @ManyToOne
    private CouponManagment couponManagment;

    public CouponManagment getCouponManagment() {
        return couponManagment;
    }

    public void setCouponManagment(CouponManagment couponManagment) {
        this.couponManagment = couponManagment;
    }

    @OneToMany(cascade = {CascadeType.ALL})
    private List<Amenity> amenitys;

    public RoomType() {
        super();
    }


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getShortCode() {
        return this.shortCode;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    public String getSlug() {
        return this.slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getBaseOccupancy() {
        return this.baseOccupancy;
    }

    public void setBaseOccupancy(BigDecimal baseOccupancy) {
        this.baseOccupancy = baseOccupancy;
    }

    public BigDecimal getHigherOcuupancy() {
        return this.higherOcuupancy;
    }

    public void setHigherOcuupancy(BigDecimal higherOcuupancy) {
        this.higherOcuupancy = higherOcuupancy;
    }

    public Long getNumberOfExtraBed() {
        return this.numberOfExtraBed;
    }

    public void setNumberOfExtraBed(Long numberOfExtraBed) {
        this.numberOfExtraBed = numberOfExtraBed;
    }

    public Long getKidsOccupancy() {
        return this.kidsOccupancy;
    }

    public void setKidsOccupancy(Long kidsOccupancy) {
        this.kidsOccupancy = kidsOccupancy;
    }

    public List<Amenity> getAmenitys() {
        return this.amenitys;
    }

    public void setAmenitys(List<Amenity> amenitys) {
        this.amenitys = amenitys;
    }

    public BigDecimal getBasePrice() {
        return this.basePrice;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

    public BigDecimal getAdditionalPersonPrice() {
        return this.additionalPersonPrice;
    }

    public void setAdditionalPersonPrice(BigDecimal additionalPersonPrice) {
        this.additionalPersonPrice = additionalPersonPrice;
    }

    public BigDecimal getExtraBedPrice() {
        return this.extraBedPrice;
    }

    public void setExtraBedPrice(BigDecimal extraBedPrice) {
        this.extraBedPrice = extraBedPrice;
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
        RoomType roomType = (RoomType) o;
        return id != null && id.equals(roomType.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


}

