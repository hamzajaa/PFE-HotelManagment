package com.ird.faa.service.admin.impl;

import com.ird.faa.bean.*;
import com.ird.faa.dao.EmployeeDao;
import com.ird.faa.service.admin.facade.*;
import com.ird.faa.service.core.facade.ArchivableService;
import com.ird.faa.service.core.impl.AbstractServiceImpl;
import com.ird.faa.service.util.ListUtil;
import com.ird.faa.service.util.SearchUtil;
import com.ird.faa.service.util.StringUtil;
import com.ird.faa.ws.rest.provided.vo.EmployeeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class EmployeeAdminServiceImpl extends AbstractServiceImpl<Employee> implements EmployeeAdminService {

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private ArchivableService<Employee> archivableService;
    @Autowired
    private DepartementAdminService departementService;
    @Autowired
    private CountryAdminService countryService;
    @Autowired
    private CityAdminService cityService;
    @Autowired
    private GradeAdminService gradeService;


    @Autowired
    private EntityManager entityManager;

    @Override
    public Employee findByUsername(String username) {
        return employeeDao.findByUsername(username);
    }

    @Override
    public List<Employee> findAll() {
        String query = "SELECT o FROM Employee o where 1=1 ";
        query += " ORDER BY o.dob";
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public List<Employee> findByDepartementCode(String code) {
        return employeeDao.findByDepartementCode(code);
    }

    @Override
    @Transactional
    public int deleteByDepartementCode(String code) {
        return employeeDao.deleteByDepartementCode(code);
    }

    @Override
    public List<Employee> findByDepartementId(Long id) {
        return employeeDao.findByDepartementId(id);
    }

    @Override
    @Transactional
    public int deleteByDepartementId(Long id) {
        return employeeDao.deleteByDepartementId(id);
    }


    @Override
    public List<Employee> findByGradeCode(String code) {
        return employeeDao.findByGradeCode(code);
    }

    @Override
    @Transactional
    public int deleteByGradeCode(String code) {
        return employeeDao.deleteByGradeCode(code);
    }

    @Override
    public List<Employee> findByGradeId(Long id) {
        return employeeDao.findByGradeId(id);
    }

    @Override
    @Transactional
    public int deleteByGradeId(Long id) {
        return employeeDao.deleteByGradeId(id);
    }


    @Override
    public List<Employee> findByCountryCode(String code) {
        return employeeDao.findByCountryCode(code);
    }

    @Override
    @Transactional
    public int deleteByCountryCode(String code) {
        return employeeDao.deleteByCountryCode(code);
    }

    @Override
    public List<Employee> findByCountryId(Long id) {
        return employeeDao.findByCountryId(id);
    }

    @Override
    @Transactional
    public int deleteByCountryId(Long id) {
        return employeeDao.deleteByCountryId(id);
    }


    @Override
    public List<Employee> findByCityCode(String code) {
        return employeeDao.findByCityCode(code);
    }

    @Override
    @Transactional
    public int deleteByCityCode(String code) {
        return employeeDao.deleteByCityCode(code);
    }

    @Override
    public List<Employee> findByCityId(Long id) {
        return employeeDao.findByCityId(id);
    }

    @Override
    @Transactional
    public int deleteByCityId(Long id) {
        return employeeDao.deleteByCityId(id);
    }

    @Override
    public Employee findByCin(String cin) {
        if (cin == null) return null;
        return employeeDao.findByCin(cin);
    }

    @Override
    @Transactional
    public int deleteByCin(String cin) {
        return employeeDao.deleteByCin(cin);
    }

    @Override
    public Employee findByIdOrCin(Employee employee) {
        Employee resultat = null;
        if (employee != null) {
            if (StringUtil.isNotEmpty(employee.getId())) {
                resultat = employeeDao.getOne(employee.getId());
            } else if (StringUtil.isNotEmpty(employee.getCin())) {
                resultat = employeeDao.findByCin(employee.getCin());
            } else if (StringUtil.isNotEmpty(employee.getUsername())) {
                resultat = employeeDao.findByUsername(employee.getUsername());
            }
        }
        return resultat;
    }

    @Override
    public Employee findById(Long id) {
        if (id == null) return null;
        return employeeDao.getOne(id);
    }

    @Override
    public Employee findByIdWithAssociatedList(Long id) {
        return findById(id);
    }

    @Override
    public Employee archiver(Employee employee) {
        if (employee.getArchive() == null) {
            employee.setArchive(false);
        }
        employee.setArchive(true);
        employee.setDateArchivage(new Date());
        employeeDao.save(employee);
        return employee;

    }

    @Override
    public Employee desarchiver(Employee employee) {
        if (employee.getArchive() == null) {
            employee.setArchive(false);
        }
        employee.setArchive(false);
        employeeDao.save(employee);
        return employee;
    }

    @Override
    public List<Employee> findByCouponManagmentId(Long id) {
        return null;
    }

    @Override
    public void deleteByCouponManagmentId(Long id) {

    }


    @Transactional
    public int deleteById(Long id) {
        int res = 0;
        if (employeeDao.findById(id).isPresent()) {
            employeeDao.deleteById(id);
            res = 1;
        }
        return res;
    }


    @Override
    public Employee update(Employee employee) {
        Employee foundedEmployee = findById(employee.getId());
        if (foundedEmployee == null) return null;
        else {
            archivableService.prepare(employee);
            return employeeDao.save(employee);
        }
    }

    private void prepareSave(Employee employee) {
        employee.setDateCreation(new Date());
        if (employee.getDateArchivage() == null)
            employee.setDateArchivage(new Date());
        employee.setCredentialsNonExpired(false);
        employee.setEnabled(false);
        employee.setAccountNonExpired(false);
        employee.setAccountNonLocked(false);
        employee.setPasswordChanged(false);
        if (employee.getArchive() == null)
            employee.setArchive(false);


    }

    @Override
    public Employee save(Employee employee) {
        prepareSave(employee);

        Employee result = null;
        Employee foundedEmployee = findByCin(employee.getCin());
        Employee foundedEmployeeByUsername = findByCin(employee.getCin());
        if (foundedEmployee == null && foundedEmployeeByUsername == null) {


            findDepartement(employee);
            findGrade(employee);
            findCountry(employee);
            findCity(employee);

            Employee savedEmployee = employeeDao.save(employee);

            result = savedEmployee;
        }

        return result;
    }

    @Override
    public List<Employee> save(List<Employee> employees) {
        List<Employee> list = new ArrayList<>();
        for (Employee employee : employees) {
            list.add(save(employee));
        }
        return list;
    }


    @Override
    @Transactional
    public int delete(Employee employee) {
        if (employee.getCin() == null) return -1;

        Employee foundedEmployee = findByCin(employee.getCin());
        if (foundedEmployee == null) return -1;
        employeeDao.delete(foundedEmployee);
        return 1;
    }


    public List<Employee> findByCriteria(EmployeeVo employeeVo) {

        String query = "SELECT o FROM Employee o where 1=1 ";

        query += SearchUtil.addConstraint("o", "id", "=", employeeVo.getId());
        query += SearchUtil.addConstraint("o", "cin", "LIKE", employeeVo.getCin());
        query += SearchUtil.addConstraintDate("o", "dob", "=", employeeVo.getDob());
        query += SearchUtil.addConstraint("o", "phone", "LIKE", employeeVo.getPhone());
        query += SearchUtil.addConstraint("o", "region", "LIKE", employeeVo.getRegion());
        query += SearchUtil.addConstraint("o", "address", "LIKE", employeeVo.getAddress());
        query += SearchUtil.addConstraint("o", "credentialsNonExpired", "=", employeeVo.getCredentialsNonExpired());
        query += SearchUtil.addConstraint("o", "enabled", "=", employeeVo.getEnabled());
        query += SearchUtil.addConstraint("o", "accountNonExpired", "=", employeeVo.getAccountNonExpired());
        query += SearchUtil.addConstraint("o", "accountNonLocked", "=", employeeVo.getAccountNonLocked());
        query += SearchUtil.addConstraint("o", "passwordChanged", "=", employeeVo.getPasswordChanged());
        query += SearchUtil.addConstraintDate("o", "createdAt", "=", employeeVo.getCreatedAt());
        query += SearchUtil.addConstraintDate("o", "updatedAt", "=", employeeVo.getUpdatedAt());
        query += SearchUtil.addConstraint("o", "username", "LIKE", employeeVo.getUsername());
        query += SearchUtil.addConstraint("o", "password", "LIKE", employeeVo.getPassword());
        query += SearchUtil.addConstraint("o", "prenom", "LIKE", employeeVo.getPrenom());
        query += SearchUtil.addConstraint("o", "nom", "LIKE", employeeVo.getNom());
        query += SearchUtil.addConstraint("o", "archive", "=", employeeVo.getArchive());
        query += SearchUtil.addConstraintDate("o", "dateArchivage", "=", employeeVo.getDateArchivage());
        query += SearchUtil.addConstraintDate("o", "dateCreation", "=", employeeVo.getDateCreation());
        query += SearchUtil.addConstraintMinMaxDate("o", "dob", employeeVo.getDobMin(), employeeVo.getDobMax());
        query += SearchUtil.addConstraintMinMaxDate("o", "createdAt", employeeVo.getCreatedAtMin(), employeeVo.getCreatedAtMax());
        query += SearchUtil.addConstraintMinMaxDate("o", "updatedAt", employeeVo.getUpdatedAtMin(), employeeVo.getUpdatedAtMax());
        query += SearchUtil.addConstraintMinMaxDate("o", "dateArchivage", employeeVo.getDateArchivageMin(), employeeVo.getDateArchivageMax());
        query += SearchUtil.addConstraintMinMaxDate("o", "dateCreation", employeeVo.getDateCreationMin(), employeeVo.getDateCreationMax());
        if (employeeVo.getDepartementVo() != null) {
            query += SearchUtil.addConstraint("o", "departement.id", "=", employeeVo.getDepartementVo().getId());
            query += SearchUtil.addConstraint("o", "departement.code", "LIKE", employeeVo.getDepartementVo().getCode());
        }

        if (employeeVo.getGradeVo() != null) {
            query += SearchUtil.addConstraint("o", "grade.id", "=", employeeVo.getGradeVo().getId());
            query += SearchUtil.addConstraint("o", "grade.code", "LIKE", employeeVo.getGradeVo().getCode());
        }

        if (employeeVo.getCountryVo() != null) {
            query += SearchUtil.addConstraint("o", "country.id", "=", employeeVo.getCountryVo().getId());
            query += SearchUtil.addConstraint("o", "country.code", "LIKE", employeeVo.getCountryVo().getCode());
        }
        if (employeeVo.getGenderVo() != null) {
            query += SearchUtil.addConstraint("o", "gender.id", "=", employeeVo.getGenderVo().getId());
            query += SearchUtil.addConstraint("o", "gender.code", "LIKE", employeeVo.getGenderVo().getCode());
        }

        if (employeeVo.getCityVo() != null) {
            query += SearchUtil.addConstraint("o", "city.id", "=", employeeVo.getCityVo().getId());
            query += SearchUtil.addConstraint("o", "city.code", "LIKE", employeeVo.getCityVo().getCode());
        }

        query += " ORDER BY o.dob";
        return entityManager.createQuery(query).getResultList();
    }

    private void findDepartement(Employee employee) {
        Departement loadedDepartement = departementService.findByIdOrCode(employee.getDepartement());

        if (loadedDepartement == null) {
            return;
        }
        employee.setDepartement(loadedDepartement);
    }

    private void findGrade(Employee employee) {
        Grade loadedGrade = gradeService.findByIdOrCode(employee.getGrade());

        if (loadedGrade == null) {
            return;
        }
        employee.setGrade(loadedGrade);
    }

    private void findCountry(Employee employee) {
        Country loadedCountry = countryService.findByIdOrCode(employee.getCountry());

        if (loadedCountry == null) {
            return;
        }
        employee.setCountry(loadedCountry);
    }

    private void findCity(Employee employee) {
        City loadedCity = cityService.findByIdOrCode(employee.getCity());

        if (loadedCity == null) {
            return;
        }
        employee.setCity(loadedCity);
    }

    @Override
    @Transactional
    public void delete(List<Employee> employees) {
        if (ListUtil.isNotEmpty(employees)) {
            employees.forEach(e -> employeeDao.delete(e));
        }
    }

    @Override
    public void update(List<Employee> employees) {
        if (ListUtil.isNotEmpty(employees)) {
            employees.forEach(e -> employeeDao.save(e));
        }
    }


}
