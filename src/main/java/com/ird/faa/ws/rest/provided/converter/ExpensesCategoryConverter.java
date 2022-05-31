package  com.ird.faa.ws.rest.provided.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.ird.faa.service.util.*;


import com.ird.faa.bean.ExpensesCategory;
import com.ird.faa.ws.rest.provided.vo.ExpensesCategoryVo;

@Component
public class ExpensesCategoryConverter extends AbstractConverter<ExpensesCategory,ExpensesCategoryVo>{


public  ExpensesCategoryConverter(){
init(true);
}

@Override
public ExpensesCategory toItem(ExpensesCategoryVo vo) {
if (vo == null) {
return null;
} else {
ExpensesCategory item = new ExpensesCategory();
        if(StringUtil.isNotEmpty(vo.getId()))
        item.setId(NumberUtil.toLong(vo.getId()));
        if(StringUtil.isNotEmpty(vo.getName()))
        item.setName(vo.getName());
            if(vo.getArchive() != null)
            item.setArchive(vo.getArchive());
        if(StringUtil.isNotEmpty(vo.getDateArchivage()))
        item.setDateArchivage(DateUtil.parse(vo.getDateArchivage()));
        if(StringUtil.isNotEmpty(vo.getDateCreation()))
        item.setDateCreation(DateUtil.parse(vo.getDateCreation()));


return item;
}
}

@Override
public ExpensesCategoryVo toVo(ExpensesCategory item) {
if (item == null) {
return null;
} else {
ExpensesCategoryVo vo = new ExpensesCategoryVo();
        if(item.getId()!=null)
        vo.setId(NumberUtil.toString(item.getId()));

        if(StringUtil.isNotEmpty(item.getName()))
        vo.setName(item.getName());

        if(item.getArchive()!=null)
        vo.setArchive(item.getArchive());
        if(item.getDateArchivage()!=null)
        vo.setDateArchivage(DateUtil.formateDate(item.getDateArchivage()));
        if(item.getDateCreation()!=null)
        vo.setDateCreation(DateUtil.formateDate(item.getDateCreation()));

return vo;
}
}

public void init(Boolean value) {
}













}
