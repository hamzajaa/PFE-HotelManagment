package com.ird.faa.service.employee.impl;

import java.util.List;

import java.util.ArrayList;

import com.ird.faa.bean.Expenses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import com.ird.faa.bean.Country;
import com.ird.faa.dao.CountryDao;
import com.ird.faa.service.employee.facade.CountryEmployeeService;

import com.ird.faa.ws.rest.provided.vo.CountryVo;
import com.ird.faa.service.util.*;

import com.ird.faa.service.core.impl.AbstractServiceImpl;

@Service
public class CountryEmployeeServiceImpl extends AbstractServiceImpl<Country> implements CountryEmployeeService {

@Autowired
private CountryDao countryDao;



@Autowired
private EntityManager entityManager;


@Override
public List<Country> findAll(){
        return countryDao.findAll();
}
    @Override
    public Country findByCode(String code){
    if( code==null) return null;
    return countryDao.findByCode(code);
    }

    @Override
    @Transactional
    public int deleteByCode(String  code) {
    return countryDao.deleteByCode(code);
    }
    @Override
    public Country findByIdOrCode(Country country){
    Country resultat=null;
    if(country != null){
    if(StringUtil.isNotEmpty(country.getId())){
    resultat= countryDao.getOne(country.getId());
    }else if(StringUtil.isNotEmpty(country.getCode())) {
    resultat= countryDao.findByCode(country.getCode());
    }
    }
    return resultat;
    }

@Override
public Country findById(Long id){
if(id==null) return null;
return countryDao.getOne(id);
}

@Override
public Country findByIdWithAssociatedList(Long id){
    return findById(id);
}



@Transactional
public int deleteById(Long id){
int res=0;
if(countryDao.findById(id).isPresent())  {
countryDao.deleteById(id);
res = 1;
}
return res;
}


@Override
public Country update(Country country){
Country foundedCountry = findById(country.getId());
if(foundedCountry==null) return null;
else{
return  countryDao.save(country);
}
}

@Override
public Country save (Country country){

    Country result =null;
    Country foundedCountry = findByCode(country.getCode());
    if(foundedCountry == null){




    Country savedCountry = countryDao.save(country);

    result = savedCountry;
    }

    return result;
}

@Override
public List<Country> save(List<Country> countrys){
List<Country> list = new ArrayList<>();
for(Country country: countrys){
list.add(save(country));
}
return list;
}



@Override
@Transactional
public int delete(Country country){
    if(country.getCode()==null) return -1;

    Country foundedCountry = findByCode(country.getCode());
    if(foundedCountry==null) return -1;
countryDao.delete(foundedCountry);
return 1;
}


public List<Country> findByCriteria(CountryVo countryVo){

String query = "SELECT o FROM Country o where 1=1 ";

            query += SearchUtil.addConstraint( "o", "id","=",countryVo.getId());
            query += SearchUtil.addConstraint( "o", "libelle","LIKE",countryVo.getLibelle());
            query += SearchUtil.addConstraint( "o", "code","LIKE",countryVo.getCode());
return entityManager.createQuery(query).getResultList();
}


@Override
@Transactional
public void delete(List<Country> countrys){
if(ListUtil.isNotEmpty(countrys)){
countrys.forEach(e->countryDao.delete(e));
}
}
@Override
public void update(List<Country> countrys){
if(ListUtil.isNotEmpty(countrys)){
countrys.forEach(e->countryDao.save(e));
}
}





    }
