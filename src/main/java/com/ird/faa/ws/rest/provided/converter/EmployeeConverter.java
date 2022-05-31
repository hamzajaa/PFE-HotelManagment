package com.ird.faa.ws.rest.provided.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.ird.faa.service.util.*;


import com.ird.faa.bean.Employee;
import com.ird.faa.ws.rest.provided.vo.EmployeeVo;

@Component
public class EmployeeConverter extends AbstractConverter<Employee, EmployeeVo> {

    @Autowired
    private DepartementConverter departementConverter;
    @Autowired
    private CountryConverter countryConverter;
    @Autowired
    private GenderConverter genderConverter;
    @Autowired
    private CityConverter cityConverter;
    @Autowired
    private GradeConverter gradeConverter;
    private Boolean departement;
    private Boolean grade;
    private Boolean country;
    private Boolean gender;
    private Boolean city;
    private Boolean couponManagment;

    public EmployeeConverter() {
        init(true);
    }

    @Override
    public Employee toItem(EmployeeVo vo) {
        if (vo == null) {
            return null;
        } else {
            Employee item = new Employee();
            if (StringUtil.isNotEmpty(vo.getId()))
                item.setId(NumberUtil.toLong(vo.getId()));
            if (StringUtil.isNotEmpty(vo.getCin()))
                item.setCin(vo.getCin());
            if (StringUtil.isNotEmpty(vo.getDob()))
                item.setDob(DateUtil.parse(vo.getDob()));
            if (StringUtil.isNotEmpty(vo.getPhone()))
                item.setPhone(vo.getPhone());
            if (StringUtil.isNotEmpty(vo.getRegion()))
                item.setRegion(vo.getRegion());
            if (StringUtil.isNotEmpty(vo.getAddress()))
                item.setAddress(vo.getAddress());
            item.setCredentialsNonExpired(vo.getCredentialsNonExpired());
            item.setEnabled(vo.getEnabled());
            item.setAccountNonExpired(vo.getAccountNonExpired());
            item.setAccountNonLocked(vo.getAccountNonLocked());
            item.setPasswordChanged(vo.getPasswordChanged());
            if (StringUtil.isNotEmpty(vo.getCreatedAt()))
                item.setCreatedAt(DateUtil.parse(vo.getCreatedAt()));
            if (StringUtil.isNotEmpty(vo.getUpdatedAt()))
                item.setUpdatedAt(DateUtil.parse(vo.getUpdatedAt()));
            if (StringUtil.isNotEmpty(vo.getUsername()))
                item.setUsername(vo.getUsername());
            if (StringUtil.isNotEmpty(vo.getPassword()))
                item.setPassword(vo.getPassword());
            if (StringUtil.isNotEmpty(vo.getPrenom()))
                item.setPrenom(vo.getPrenom());
            if (StringUtil.isNotEmpty(vo.getNom()))
                item.setNom(vo.getNom());
            if (vo.getArchive() != null)
                item.setArchive(vo.getArchive());
            if (StringUtil.isNotEmpty(vo.getDateArchivage()))
                item.setDateArchivage(DateUtil.parse(vo.getDateArchivage()));
            if (StringUtil.isNotEmpty(vo.getDateCreation()))
                item.setDateCreation(DateUtil.parse(vo.getDateCreation()));
            if (vo.getDepartementVo() != null && this.departement)
                item.setDepartement(departementConverter.toItem(vo.getDepartementVo()));
            if (vo.getGradeVo() != null && this.grade)
                item.setGrade(gradeConverter.toItem(vo.getGradeVo()));
            if (vo.getCountryVo() != null && this.country)
                item.setCountry(countryConverter.toItem(vo.getCountryVo()));
            if (vo.getGradeVo() != null && this.gender)
                item.setGender(genderConverter.toItem(vo.getGenderVo()));
            if (vo.getCityVo() != null && this.city)
                item.setCity(cityConverter.toItem(vo.getCityVo()));


            return item;
        }
    }

    @Override
    public EmployeeVo toVo(Employee item) {
        if (item == null) {
            return null;
        } else {
            EmployeeVo vo = new EmployeeVo();
            if (item.getId() != null)
                vo.setId(NumberUtil.toString(item.getId()));

            if (StringUtil.isNotEmpty(item.getCin()))
                vo.setCin(item.getCin());

            if (item.getDob() != null)
                vo.setDob(DateUtil.formateDate(item.getDob()));
            if (StringUtil.isNotEmpty(item.getPhone()))
                vo.setPhone(item.getPhone());

            if (StringUtil.isNotEmpty(item.getRegion()))
                vo.setRegion(item.getRegion());

            if (StringUtil.isNotEmpty(item.getAddress()))
                vo.setAddress(item.getAddress());

            vo.setCredentialsNonExpired(item.getCredentialsNonExpired());
            vo.setEnabled(item.getEnabled());
            vo.setAccountNonExpired(item.getAccountNonExpired());
            vo.setAccountNonLocked(item.getAccountNonLocked());
            vo.setPasswordChanged(item.getPasswordChanged());
            if (item.getCreatedAt() != null)
                vo.setCreatedAt(DateUtil.formateDate(item.getCreatedAt()));
            if (item.getUpdatedAt() != null)
                vo.setUpdatedAt(DateUtil.formateDate(item.getUpdatedAt()));
            if (StringUtil.isNotEmpty(item.getUsername()))
                vo.setUsername(item.getUsername());

            if (StringUtil.isNotEmpty(item.getPassword()))
                vo.setPassword(item.getPassword());

            if (StringUtil.isNotEmpty(item.getPrenom()))
                vo.setPrenom(item.getPrenom());

            if (StringUtil.isNotEmpty(item.getNom()))
                vo.setNom(item.getNom());

            if (item.getArchive() != null)
                vo.setArchive(item.getArchive());
            if (item.getDateArchivage() != null)
                vo.setDateArchivage(DateUtil.formateDate(item.getDateArchivage()));
            if (item.getDateCreation() != null)
                vo.setDateCreation(DateUtil.formateDate(item.getDateCreation()));
            if (item.getDepartement() != null && this.departement) {
                vo.setDepartementVo(departementConverter.toVo(item.getDepartement()));
            }
            if (item.getGrade() != null && this.grade) {
                vo.setGradeVo(gradeConverter.toVo(item.getGrade()));
            }
            if (item.getCountry() != null && this.country) {
                vo.setCountryVo(countryConverter.toVo(item.getCountry()));
            }

            if (item.getGender() != null && this.gender) {
                vo.setGenderVo(genderConverter.toVo(item.getGender()));
            }

            if (item.getCity() != null && this.city) {
                vo.setCityVo(cityConverter.toVo(item.getCity()));
            }

            return vo;
        }
    }

    public void init(Boolean value) {
        departement = value;
        grade = value;
        country = value;
        gender = value;
        city = value;
        couponManagment = value;
    }


    public DepartementConverter getDepartementConverter() {
        return this.departementConverter;
    }

    public void setDepartementConverter(DepartementConverter departementConverter) {
        this.departementConverter = departementConverter;
    }

    public CountryConverter getCountryConverter() {
        return this.countryConverter;
    }

    public void setCountryConverter(CountryConverter countryConverter) {
        this.countryConverter = countryConverter;
    }

    public CityConverter getCityConverter() {
        return this.cityConverter;
    }

    public void setCityConverter(CityConverter cityConverter) {
        this.cityConverter = cityConverter;
    }

    public GradeConverter getGradeConverter() {
        return this.gradeConverter;
    }

    public void setGradeConverter(GradeConverter gradeConverter) {
        this.gradeConverter = gradeConverter;
    }

    public boolean isDepartement() {
        return this.departement;
    }

    public void setDepartement(boolean departement) {
        this.departement = departement;
    }

    public boolean isGrade() {
        return this.grade;
    }

    public void setGrade(boolean grade) {
        this.grade = grade;
    }

    public boolean isCountry() {
        return this.country;
    }

    public void setCountry(boolean country) {
        this.country = country;
    }

    public boolean isGender() {
        return this.gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public boolean isCity() {
        return this.city;
    }

    public void setCity(boolean city) {
        this.city = city;
    }


    public void setCouponManagment(Boolean couponManagment) {
        this.couponManagment = couponManagment;
    }
}
