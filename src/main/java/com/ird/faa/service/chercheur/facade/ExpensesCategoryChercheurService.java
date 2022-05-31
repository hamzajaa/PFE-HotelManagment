package com.ird.faa.service.chercheur.facade;

import java.util.List;
import com.ird.faa.bean.ExpensesCategory;
import com.ird.faa.ws.rest.provided.vo.ExpensesCategoryVo;
import com.ird.faa.service.core.facade.AbstractService;

public interface ExpensesCategoryChercheurService extends AbstractService<ExpensesCategory,Long,ExpensesCategoryVo>{





/**
    * delete ExpensesCategory from database
    * @param id - id of ExpensesCategory to be deleted
    *
    */
    int deleteById(Long id);









}
