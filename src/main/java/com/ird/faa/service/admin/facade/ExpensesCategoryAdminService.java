package com.ird.faa.service.admin.facade;

import java.util.List;
import com.ird.faa.bean.ExpensesCategory;
import com.ird.faa.ws.rest.provided.vo.ExpensesCategoryVo;
import com.ird.faa.service.core.facade.AbstractService;

public interface ExpensesCategoryAdminService extends AbstractService<ExpensesCategory,Long,ExpensesCategoryVo>{





/**
    * delete ExpensesCategory from database
    * @param id - id of ExpensesCategory to be deleted
    *
    */
    int deleteById(Long id);








    ExpensesCategory archiver(ExpensesCategory expensesCategory) ;
    ExpensesCategory desarchiver(ExpensesCategory expensesCategory);

}
