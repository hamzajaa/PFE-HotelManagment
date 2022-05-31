package com.ird.faa.service.chercheur.facade;

import java.util.List;
import com.ird.faa.bean.Expenses;
import com.ird.faa.ws.rest.provided.vo.ExpensesVo;
import com.ird.faa.service.core.facade.AbstractService;

public interface ExpensesChercheurService extends AbstractService<Expenses,Long,ExpensesVo>{





/**
    * delete Expenses from database
    * @param id - id of Expenses to be deleted
    *
    */
    int deleteById(Long id);



    List<Expenses> findByExpensesCategoryId(Long id);

    int deleteByExpensesCategoryId(Long id);







}
