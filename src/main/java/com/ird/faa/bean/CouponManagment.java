package com.ird.faa.bean;

import java.util.Objects;
import java.util.List;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;


import java.math.BigDecimal;
import javax.persistence.*;


@Entity
@Table(name = "coupon_managment")
public class CouponManagment {

    @Id
    @SequenceGenerator(name = "coupon_managment_seq", sequenceName = "coupon_managment_seq",
            allocationSize = 1, initialValue = 10000)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "coupon_managment_seq")
    private Long id;

    @Column(length = 500)
    private String title;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column(length = 500)
    private String image;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date couponDePeriod;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date couponFiPeriod;
    @Column(length = 500)
    private String couponCode;
    private BigDecimal couponValue;
    private BigDecimal minAmount;
    private BigDecimal maxAmount;
    private BigDecimal limitPerUser;
    private BigDecimal limitPerCoupon;

    @ManyToOne
    private CouponType couponType;

    @OneToMany(cascade = {CascadeType.ALL})
    private List<Employee> employees;
    @OneToMany(cascade = {CascadeType.ALL})
    private List<RoomType> roomTypes;
    @OneToMany(cascade = {CascadeType.ALL})
    private List<PaidService> paidServices;

    public CouponManagment() {
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

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getCouponDePeriod() {
        return this.couponDePeriod;
    }

    public void setCouponDePeriod(Date couponDePeriod) {
        this.couponDePeriod = couponDePeriod;
    }

    public Date getCouponFiPeriod() {
        return this.couponFiPeriod;
    }

    public void setCouponFiPeriod(Date couponFiPeriod) {
        this.couponFiPeriod = couponFiPeriod;
    }

    public String getCouponCode() {
        return this.couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public CouponType getCouponType() {
        return this.couponType;
    }

    public void setCouponType(CouponType couponType) {
        this.couponType = couponType;
    }

    public BigDecimal getCouponValue() {
        return this.couponValue;
    }

    public void setCouponValue(BigDecimal couponValue) {
        this.couponValue = couponValue;
    }

    public BigDecimal getMinAmount() {
        return this.minAmount;
    }

    public void setMinAmount(BigDecimal minAmount) {
        this.minAmount = minAmount;
    }

    public BigDecimal getMaxAmount() {
        return this.maxAmount;
    }

    public void setMaxAmount(BigDecimal maxAmount) {
        this.maxAmount = maxAmount;
    }

    public List<Employee> getEmployees() {
        return this.employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<RoomType> getRoomTypes() {
        return this.roomTypes;
    }

    public void setRoomTypes(List<RoomType> roomTypes) {
        this.roomTypes = roomTypes;
    }

    public BigDecimal getLimitPerUser() {
        return this.limitPerUser;
    }

    public void setLimitPerUser(BigDecimal limitPerUser) {
        this.limitPerUser = limitPerUser;
    }

    public BigDecimal getLimitPerCoupon() {
        return this.limitPerCoupon;
    }

    public void setLimitPerCoupon(BigDecimal limitPerCoupon) {
        this.limitPerCoupon = limitPerCoupon;
    }

    public List<PaidService> getPaidServices() {
        return this.paidServices;
    }

    public void setPaidServices(List<PaidService> paidServices) {
        this.paidServices = paidServices;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CouponManagment couponManagment = (CouponManagment) o;
        return id != null && id.equals(couponManagment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

