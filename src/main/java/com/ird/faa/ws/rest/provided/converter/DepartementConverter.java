package  com.ird.faa.ws.rest.provided.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.ird.faa.service.util.*;


import com.ird.faa.bean.Departement;
import com.ird.faa.ws.rest.provided.vo.DepartementVo;

@Component
public class DepartementConverter extends AbstractConverter<Departement,DepartementVo>{

        @Autowired
        private EmployeeConverter employeeConverter ;
        private Boolean employees;

public  DepartementConverter(){
init(true);
}

@Override
public Departement toItem(DepartementVo vo) {
if (vo == null) {
return null;
} else {
Departement item = new Departement();
        if(StringUtil.isNotEmpty(vo.getId()))
        item.setId(NumberUtil.toLong(vo.getId()));
        if(StringUtil.isNotEmpty(vo.getLibelle()))
        item.setLibelle(vo.getLibelle());
        if(StringUtil.isNotEmpty(vo.getCode()))
        item.setCode(vo.getCode());

    if(ListUtil.isNotEmpty(vo.getEmployeesVo()) && this.employees)
        item.setEmployees(employeeConverter.toItem(vo.getEmployeesVo()));

return item;
}
}

@Override
public DepartementVo toVo(Departement item) {
if (item == null) {
return null;
} else {
DepartementVo vo = new DepartementVo();
        if(item.getId()!=null)
        vo.setId(NumberUtil.toString(item.getId()));

        if(StringUtil.isNotEmpty(item.getLibelle()))
        vo.setLibelle(item.getLibelle());

        if(StringUtil.isNotEmpty(item.getCode()))
        vo.setCode(item.getCode());

        if(ListUtil.isNotEmpty(item.getEmployees()) && this.employees){
        employeeConverter.init(true);
        employeeConverter.setDepartement(false);
        vo.setEmployeesVo(employeeConverter.toVo(item.getEmployees()));
        employeeConverter.setDepartement(true);
        }

return vo;
}
}

public void init(Boolean value) {
        employees = value;
}


        public EmployeeConverter getEmployeeConverter(){
        return this.employeeConverter;
        }
        public void setEmployeeConverter(EmployeeConverter employeeConverter ){
        this.employeeConverter = employeeConverter;
        }








        public Boolean  isEmployees(){
        return this.employees ;
        }
        public void  setEmployees(Boolean employees ){
        this.employees  = employees ;
        }


}
