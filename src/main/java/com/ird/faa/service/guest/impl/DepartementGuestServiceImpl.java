package com.ird.faa.service.guest.impl;

import java.util.List;

import java.util.ArrayList;

import com.ird.faa.bean.Expenses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import com.ird.faa.bean.Departement;
        import com.ird.faa.bean.Employee;
import com.ird.faa.dao.DepartementDao;
import com.ird.faa.service.guest.facade.DepartementGuestService;
        import com.ird.faa.service.guest.facade.EmployeeGuestService;

import com.ird.faa.ws.rest.provided.vo.DepartementVo;
import com.ird.faa.service.util.*;

import com.ird.faa.service.core.impl.AbstractServiceImpl;

@Service
public class DepartementGuestServiceImpl extends AbstractServiceImpl<Departement> implements DepartementGuestService {

@Autowired
private DepartementDao departementDao;

        @Autowired
        private EmployeeGuestService employeeService ;


@Autowired
private EntityManager entityManager;


@Override
public List<Departement> findAll(){
        return departementDao.findAll();
}
    @Override
    public Departement findByCode(String code){
    if( code==null) return null;
    return departementDao.findByCode(code);
    }

    @Override
    @Transactional
    public int deleteByCode(String  code) {
    return departementDao.deleteByCode(code);
    }
    @Override
    public Departement findByIdOrCode(Departement departement){
    Departement resultat=null;
    if(departement != null){
    if(StringUtil.isNotEmpty(departement.getId())){
    resultat= departementDao.getOne(departement.getId());
    }else if(StringUtil.isNotEmpty(departement.getCode())) {
    resultat= departementDao.findByCode(departement.getCode());
    }
    }
    return resultat;
    }

@Override
public Departement findById(Long id){
if(id==null) return null;
return departementDao.getOne(id);
}

@Override
public Departement findByIdWithAssociatedList(Long id){
    Departement departement  = findById(id);
    findAssociatedLists(departement);
    return departement;
}

    private void findAssociatedLists(Departement departement){
    if(departement!=null && departement.getId() != null) {
            List<Employee> employees = employeeService.findByDepartementId(departement.getId());
            departement.setEmployees(employees);
    }
    }
    private void deleteAssociatedLists(Long id){
    if(id != null ) {
            employeeService.deleteByDepartementId(id);
    }
    }

    private void updateAssociatedLists(Departement departement){
    if(departement !=null && departement.getId() != null){
            List
            <List<Employee>> resultEmployees= employeeService.getToBeSavedAndToBeDeleted(employeeService.findByDepartementId(departement.getId()),departement.getEmployees());
            employeeService.delete(resultEmployees.get(1));
            associateEmployee(departement,resultEmployees.get(0));
            employeeService.update(resultEmployees.get(0));

    }
    }

@Transactional
public int deleteById(Long id){
int res=0;
if(departementDao.findById(id).isPresent())  {
    deleteAssociatedLists(id);
departementDao.deleteById(id);
res = 1;
}
return res;
}


@Override
public Departement update(Departement departement){
Departement foundedDepartement = findById(departement.getId());
if(foundedDepartement==null) return null;
else{
    updateAssociatedLists(departement);
return  departementDao.save(departement);
}
}

@Override
public Departement save (Departement departement){

    Departement result =null;
    Departement foundedDepartement = findByCode(departement.getCode());
    if(foundedDepartement == null){




    Departement savedDepartement = departementDao.save(departement);

        saveEmployees(savedDepartement,departement.getEmployees());
    result = savedDepartement;
    }

    return result;
}

@Override
public List<Departement> save(List<Departement> departements){
List<Departement> list = new ArrayList<>();
for(Departement departement: departements){
list.add(save(departement));
}
return list;
}

        private List<Employee> prepareEmployees(Departement departement,List<Employee> employees){
        for(Employee employee:employees ){
        employee.setDepartement(departement);
        }
        return employees;
        }


@Override
@Transactional
public int delete(Departement departement){
    if(departement.getCode()==null) return -1;

    Departement foundedDepartement = findByCode(departement.getCode());
    if(foundedDepartement==null) return -1;
departementDao.delete(foundedDepartement);
return 1;
}


public List<Departement> findByCriteria(DepartementVo departementVo){

String query = "SELECT o FROM Departement o where 1=1 ";

            query += SearchUtil.addConstraint( "o", "id","=",departementVo.getId());
            query += SearchUtil.addConstraint( "o", "libelle","LIKE",departementVo.getLibelle());
            query += SearchUtil.addConstraint( "o", "code","LIKE",departementVo.getCode());
return entityManager.createQuery(query).getResultList();
}
        private  void saveEmployees(Departement departement,List<Employee> employees){

        if (ListUtil.isNotEmpty(departement.getEmployees())) {
        List<Employee> savedEmployees = new ArrayList<>();
        employees.forEach(element -> {
        element.setDepartement(departement);
         employeeService.save(element);
        });
        departement.setEmployees(savedEmployees);
        }
        }


@Override
@Transactional
public void delete(List<Departement> departements){
if(ListUtil.isNotEmpty(departements)){
departements.forEach(e->departementDao.delete(e));
}
}
@Override
public void update(List<Departement> departements){
if(ListUtil.isNotEmpty(departements)){
departements.forEach(e->departementDao.save(e));
}
}

        private void associateEmployee(Departement departement, List<Employee> employee) {
        if (ListUtil.isNotEmpty(employee)) {
        employee.forEach(e -> e.setDepartement(departement));
        }
        }




    }
