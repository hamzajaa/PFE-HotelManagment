package com.ird.faa.service.chercheur.facade;

import java.util.List;
import com.ird.faa.bean.Employee;
import com.ird.faa.ws.rest.provided.vo.EmployeeVo;
import com.ird.faa.service.core.facade.AbstractService;

public interface EmployeeChercheurService extends AbstractService<Employee,Long,EmployeeVo>{

    Employee findByUsername(String username);


    /**
    * find Employee from database by cin (reference)
    * @param cin - reference of Employee
    * @return the founded Employee , If no Employee were
    *         found in database return  null.
    */
    Employee findByCin(String cin);

    /**
    * find Employee from database by id (PK) or cin (reference)
    * @param id - id of Employee
    * @param cin - reference of Employee
    * @return the founded Employee , If no Employee were
    *         found in database return  null.
    */
    Employee findByIdOrCin(Employee employee);


/**
    * delete Employee from database
    * @param id - id of Employee to be deleted
    *
    */
    int deleteById(Long id);


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


    /**
    * delete Employee from database by cin (reference)
    *
    * @param cin - reference of Employee to be deleted
    * @return 1 if Employee deleted successfully
    */
    int deleteByCin(String cin);


    List<Employee> findByCouponManagmentId(Long id);

    void deleteByCouponManagmentId(Long id);
}
