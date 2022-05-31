package  com.ird.faa.ws.rest.provided.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.ird.faa.service.util.*;


import com.ird.faa.bean.Expenses;
import com.ird.faa.ws.rest.provided.vo.ExpensesVo;

@Component
public class ExpensesConverter extends AbstractConverter<Expenses,ExpensesVo>{

        @Autowired
        private ExpensesCategoryConverter expensesCategoryConverter ;
    private Boolean expensesCategory;

public  ExpensesConverter(){
init(true);
}

@Override
public Expenses toItem(ExpensesVo vo) {
if (vo == null) {
return null;
} else {
Expenses item = new Expenses();
        if(StringUtil.isNotEmpty(vo.getId()))
        item.setId(NumberUtil.toLong(vo.getId()));
        if(StringUtil.isNotEmpty(vo.getTitle()))
        item.setTitle(vo.getTitle());
        if(StringUtil.isNotEmpty(vo.getAmount()))
        item.setAmount(vo.getAmount());
    if(vo.getExpensesCategoryVo()!=null && this.expensesCategory)
        item.setExpensesCategory(expensesCategoryConverter.toItem(vo.getExpensesCategoryVo())) ;


return item;
}
}

@Override
public ExpensesVo toVo(Expenses item) {
if (item == null) {
return null;
} else {
ExpensesVo vo = new ExpensesVo();
        if(item.getId()!=null)
        vo.setId(NumberUtil.toString(item.getId()));

        if(StringUtil.isNotEmpty(item.getTitle()))
        vo.setTitle(item.getTitle());

        if(StringUtil.isNotEmpty(item.getAmount()))
        vo.setAmount(item.getAmount());

    if(item.getExpensesCategory()!=null && this.expensesCategory) {
        vo.setExpensesCategoryVo(expensesCategoryConverter.toVo(item.getExpensesCategory())) ;
    }

return vo;
}
}

public void init(Boolean value) {
    expensesCategory = value;
}


        public ExpensesCategoryConverter getExpensesCategoryConverter(){
        return this.expensesCategoryConverter;
        }
        public void setExpensesCategoryConverter(ExpensesCategoryConverter expensesCategoryConverter ){
        this.expensesCategoryConverter = expensesCategoryConverter;
        }

    public boolean  isExpensesCategory(){
    return this.expensesCategory;
    }
    public void  setExpensesCategory(boolean expensesCategory){
    this.expensesCategory = expensesCategory;
    }








}
