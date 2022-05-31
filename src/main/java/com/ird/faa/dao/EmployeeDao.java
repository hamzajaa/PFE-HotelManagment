package com.ird.faa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

import com.ird.faa.bean.Employee;

import org.springframework.data.jpa.repository.Query;

@Repository
public interface EmployeeDao extends JpaRepository<Employee,Long> {

    Employee findByUsername(String username);


    @Query("SELECT item FROM Employee item ORDER BY item.dob ASC")
    List<Employee> findAll();

    Employee findByCin(String cin);

    int deleteByCin(String cin);

    List<Employee> findByDepartementCode(String code);
    int deleteByDepartementCode(String code);

    List<Employee> findByDepartementId(Long id);

    int deleteByDepartementId(Long id);
    List<Employee> findByGradeCode(String code);
    int deleteByGradeCode(String code);

    List<Employee> findByGradeId(Long id);

    int deleteByGradeId(Long id);
    List<Employee> findByCountryCode(String code);
    int deleteByCountryCode(String code);

    List<Employee> findByCountryId(Long id);

    int deleteByCountryId(Long id);
    List<Employee> findByCityCode(String code);
    int deleteByCityCode(String code);

    List<Employee> findByCityId(Long id);

    int deleteByCityId(Long id);


}
