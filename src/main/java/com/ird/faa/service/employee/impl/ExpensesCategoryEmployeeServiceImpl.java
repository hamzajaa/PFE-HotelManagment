package com.ird.faa.service.employee.impl;

import java.util.List;
    import java.util.Date;

import java.util.ArrayList;

import com.ird.faa.bean.Expenses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import com.ird.faa.bean.ExpensesCategory;
import com.ird.faa.dao.ExpensesCategoryDao;
import com.ird.faa.service.employee.facade.ExpensesCategoryEmployeeService;

import com.ird.faa.ws.rest.provided.vo.ExpensesCategoryVo;
import com.ird.faa.service.util.*;

    import com.ird.faa.service.core.facade.ArchivableService;
import com.ird.faa.service.core.impl.AbstractServiceImpl;

@Service
public class ExpensesCategoryEmployeeServiceImpl extends AbstractServiceImpl<ExpensesCategory> implements ExpensesCategoryEmployeeService {

@Autowired
private ExpensesCategoryDao expensesCategoryDao;

    @Autowired
    private ArchivableService<ExpensesCategory> archivableService;


@Autowired
private EntityManager entityManager;


@Override
public List<ExpensesCategory> findAll(){
        return expensesCategoryDao.findAll();
}

@Override
public ExpensesCategory findById(Long id){
if(id==null) return null;
return expensesCategoryDao.getOne(id);
}

@Override
public ExpensesCategory findByIdWithAssociatedList(Long id){
    return findById(id);
}
    @Override
    public ExpensesCategory archiver(ExpensesCategory expensesCategory) {
    if (expensesCategory.getArchive() == null) {
    expensesCategory.setArchive(false);
    }
    expensesCategory.setArchive(true);
    expensesCategory.setDateArchivage(new Date());
    expensesCategoryDao.save(expensesCategory);
    return expensesCategory;

    }

    @Override
    public ExpensesCategory desarchiver(ExpensesCategory expensesCategory) {
    if (expensesCategory.getArchive() == null) {
    expensesCategory.setArchive(false);
    }
    expensesCategory.setArchive(false);
    expensesCategoryDao.save(expensesCategory);
    return expensesCategory;
    }




@Transactional
public int deleteById(Long id){
int res=0;
if(expensesCategoryDao.findById(id).isPresent())  {
expensesCategoryDao.deleteById(id);
res = 1;
}
return res;
}


@Override
public ExpensesCategory update(ExpensesCategory expensesCategory){
ExpensesCategory foundedExpensesCategory = findById(expensesCategory.getId());
if(foundedExpensesCategory==null) return null;
else{
    archivableService.prepare(expensesCategory);
return  expensesCategoryDao.save(expensesCategory);
}
}
    private void prepareSave(ExpensesCategory expensesCategory){
        expensesCategory.setDateCreation(new Date());
        if(expensesCategory.getDateArchivage() == null)
        expensesCategory.setDateArchivage(new Date());
                    if(expensesCategory.getArchive() == null)
                expensesCategory.setArchive(false);




    }

@Override
public ExpensesCategory save (ExpensesCategory expensesCategory){
    prepareSave(expensesCategory);




    return expensesCategoryDao.save(expensesCategory);


}

@Override
public List<ExpensesCategory> save(List<ExpensesCategory> expensesCategorys){
List<ExpensesCategory> list = new ArrayList<>();
for(ExpensesCategory expensesCategory: expensesCategorys){
list.add(save(expensesCategory));
}
return list;
}



@Override
@Transactional
public int delete(ExpensesCategory expensesCategory){
    if(expensesCategory.getId()==null) return -1;
    ExpensesCategory foundedExpensesCategory = findById(expensesCategory.getId());
    if(foundedExpensesCategory==null) return -1;
expensesCategoryDao.delete(foundedExpensesCategory);
return 1;
}


public List<ExpensesCategory> findByCriteria(ExpensesCategoryVo expensesCategoryVo){

String query = "SELECT o FROM ExpensesCategory o where 1=1 ";

            query += SearchUtil.addConstraint( "o", "id","=",expensesCategoryVo.getId());
            query += SearchUtil.addConstraint( "o", "name","LIKE",expensesCategoryVo.getName());
            query += SearchUtil.addConstraint( "o", "archive","=",expensesCategoryVo.getArchive());
        query += SearchUtil.addConstraintDate( "o", "dateArchivage","=",expensesCategoryVo.getDateArchivage());
        query += SearchUtil.addConstraintDate( "o", "dateCreation","=",expensesCategoryVo.getDateCreation());
            query += SearchUtil.addConstraintMinMaxDate("o","dateArchivage",expensesCategoryVo.getDateArchivageMin(),expensesCategoryVo.getDateArchivageMax());
            query += SearchUtil.addConstraintMinMaxDate("o","dateCreation",expensesCategoryVo.getDateCreationMin(),expensesCategoryVo.getDateCreationMax());
return entityManager.createQuery(query).getResultList();
}


@Override
@Transactional
public void delete(List<ExpensesCategory> expensesCategorys){
if(ListUtil.isNotEmpty(expensesCategorys)){
expensesCategorys.forEach(e->expensesCategoryDao.delete(e));
}
}
@Override
public void update(List<ExpensesCategory> expensesCategorys){
if(ListUtil.isNotEmpty(expensesCategorys)){
expensesCategorys.forEach(e->expensesCategoryDao.save(e));
}
}





    }
