package com.ird.faa.service.guest.impl;

import java.util.List;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import com.ird.faa.bean.Expenses;
        import com.ird.faa.bean.ExpensesCategory;
import com.ird.faa.dao.ExpensesDao;
import com.ird.faa.service.guest.facade.ExpensesGuestService;
        import com.ird.faa.service.guest.facade.ExpensesCategoryGuestService;

import com.ird.faa.ws.rest.provided.vo.ExpensesVo;
import com.ird.faa.service.util.*;

import com.ird.faa.service.core.impl.AbstractServiceImpl;

@Service
public class ExpensesGuestServiceImpl extends AbstractServiceImpl<Expenses> implements ExpensesGuestService {

@Autowired
private ExpensesDao expensesDao;

        @Autowired
        private ExpensesCategoryGuestService expensesCategoryService ;


@Autowired
private EntityManager entityManager;


@Override
public List<Expenses> findAll(){
        return expensesDao.findAll();
}
        @Override
        public List<Expenses> findByExpensesCategoryId(Long id){
        return expensesDao.findByExpensesCategoryId(id);
        }

        @Override
        @Transactional
        public int deleteByExpensesCategoryId(Long id){
        return expensesDao.deleteByExpensesCategoryId(id);
        }


@Override
public Expenses findById(Long id){
if(id==null) return null;
return expensesDao.getOne(id);
}

@Override
public Expenses findByIdWithAssociatedList(Long id){
    return findById(id);
}



@Transactional
public int deleteById(Long id){
int res=0;
if(expensesDao.findById(id).isPresent())  {
expensesDao.deleteById(id);
res = 1;
}
return res;
}


@Override
public Expenses update(Expenses expenses){
Expenses foundedExpenses = findById(expenses.getId());
if(foundedExpenses==null) return null;
else{
return  expensesDao.save(expenses);
}
}

@Override
public Expenses save (Expenses expenses){



    findExpensesCategory(expenses);

    return expensesDao.save(expenses);


}

@Override
public List<Expenses> save(List<Expenses> expensess){
List<Expenses> list = new ArrayList<>();
for(Expenses expenses: expensess){
list.add(save(expenses));
}
return list;
}



@Override
@Transactional
public int delete(Expenses expenses){
    if(expenses.getId()==null) return -1;
    Expenses foundedExpenses = findById(expenses.getId());
    if(foundedExpenses==null) return -1;
expensesDao.delete(foundedExpenses);
return 1;
}


public List<Expenses> findByCriteria(ExpensesVo expensesVo){

String query = "SELECT o FROM Expenses o where 1=1 ";

            query += SearchUtil.addConstraint( "o", "id","=",expensesVo.getId());
            query += SearchUtil.addConstraint( "o", "title","LIKE",expensesVo.getTitle());
            query += SearchUtil.addConstraint( "o", "amount","LIKE",expensesVo.getAmount());
    if(expensesVo.getExpensesCategoryVo()!=null){
        query += SearchUtil.addConstraint( "o", "expensesCategory.id","=",expensesVo.getExpensesCategoryVo().getId());
    }

return entityManager.createQuery(query).getResultList();
}

    private void findExpensesCategory(Expenses expenses){
        ExpensesCategory loadedExpensesCategory = null;
        if(expenses.getExpensesCategory() != null && expenses.getExpensesCategory().getId() !=null)
        loadedExpensesCategory =expensesCategoryService.findById(expenses.getExpensesCategory().getId());

    if(loadedExpensesCategory==null ) {
    return;
    }
    expenses.setExpensesCategory(loadedExpensesCategory);
    }

@Override
@Transactional
public void delete(List<Expenses> expensess){
if(ListUtil.isNotEmpty(expensess)){
expensess.forEach(e->expensesDao.delete(e));
}
}
@Override
public void update(List<Expenses> expensess){
if(ListUtil.isNotEmpty(expensess)){
expensess.forEach(e->expensesDao.save(e));
}
}





    }
