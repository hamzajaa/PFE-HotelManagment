package com.ird.faa.service.employee.impl;

import java.util.List;
    import java.util.Date;

import java.util.ArrayList;

import com.ird.faa.bean.Expenses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import com.ird.faa.bean.PriceType;
import com.ird.faa.dao.PriceTypeDao;
import com.ird.faa.service.employee.facade.PriceTypeEmployeeService;

import com.ird.faa.ws.rest.provided.vo.PriceTypeVo;
import com.ird.faa.service.util.*;

    import com.ird.faa.service.core.facade.ArchivableService;
import com.ird.faa.service.core.impl.AbstractServiceImpl;

@Service
public class PriceTypeEmployeeServiceImpl extends AbstractServiceImpl<PriceType> implements PriceTypeEmployeeService {

@Autowired
private PriceTypeDao priceTypeDao;

    @Autowired
    private ArchivableService<PriceType> archivableService;


@Autowired
private EntityManager entityManager;


@Override
public List<PriceType> findAll(){
        return priceTypeDao.findAll();
}
    @Override
    public PriceType findByCode(String code){
    if( code==null) return null;
    return priceTypeDao.findByCode(code);
    }

    @Override
    @Transactional
    public int deleteByCode(String  code) {
    return priceTypeDao.deleteByCode(code);
    }
    @Override
    public PriceType findByIdOrCode(PriceType priceType){
    PriceType resultat=null;
    if(priceType != null){
    if(StringUtil.isNotEmpty(priceType.getId())){
    resultat= priceTypeDao.getOne(priceType.getId());
    }else if(StringUtil.isNotEmpty(priceType.getCode())) {
    resultat= priceTypeDao.findByCode(priceType.getCode());
    }
    }
    return resultat;
    }

@Override
public PriceType findById(Long id){
if(id==null) return null;
return priceTypeDao.getOne(id);
}

@Override
public PriceType findByIdWithAssociatedList(Long id){
    return findById(id);
}
    @Override
    public PriceType archiver(PriceType priceType) {
    if (priceType.getArchive() == null) {
    priceType.setArchive(false);
    }
    priceType.setArchive(true);
    priceType.setDateArchivage(new Date());
    priceTypeDao.save(priceType);
    return priceType;

    }

    @Override
    public PriceType desarchiver(PriceType priceType) {
    if (priceType.getArchive() == null) {
    priceType.setArchive(false);
    }
    priceType.setArchive(false);
    priceTypeDao.save(priceType);
    return priceType;
    }




@Transactional
public int deleteById(Long id){
int res=0;
if(priceTypeDao.findById(id).isPresent())  {
priceTypeDao.deleteById(id);
res = 1;
}
return res;
}


@Override
public PriceType update(PriceType priceType){
PriceType foundedPriceType = findById(priceType.getId());
if(foundedPriceType==null) return null;
else{
    archivableService.prepare(priceType);
return  priceTypeDao.save(priceType);
}
}
    private void prepareSave(PriceType priceType){
        priceType.setDateCreation(new Date());
        if(priceType.getDateArchivage() == null)
        priceType.setDateArchivage(new Date());
                    if(priceType.getArchive() == null)
                priceType.setArchive(false);




    }

@Override
public PriceType save (PriceType priceType){
    prepareSave(priceType);

    PriceType result =null;
    PriceType foundedPriceType = findByCode(priceType.getCode());
    if(foundedPriceType == null){




    PriceType savedPriceType = priceTypeDao.save(priceType);

    result = savedPriceType;
    }

    return result;
}

@Override
public List<PriceType> save(List<PriceType> priceTypes){
List<PriceType> list = new ArrayList<>();
for(PriceType priceType: priceTypes){
list.add(save(priceType));
}
return list;
}



@Override
@Transactional
public int delete(PriceType priceType){
    if(priceType.getCode()==null) return -1;

    PriceType foundedPriceType = findByCode(priceType.getCode());
    if(foundedPriceType==null) return -1;
priceTypeDao.delete(foundedPriceType);
return 1;
}


public List<PriceType> findByCriteria(PriceTypeVo priceTypeVo){

String query = "SELECT o FROM PriceType o where 1=1 ";

            query += SearchUtil.addConstraint( "o", "id","=",priceTypeVo.getId());
            query += SearchUtil.addConstraint( "o", "libelle","LIKE",priceTypeVo.getLibelle());
            query += SearchUtil.addConstraint( "o", "code","LIKE",priceTypeVo.getCode());
            query += SearchUtil.addConstraint( "o", "archive","=",priceTypeVo.getArchive());
        query += SearchUtil.addConstraintDate( "o", "dateArchivage","=",priceTypeVo.getDateArchivage());
        query += SearchUtil.addConstraintDate( "o", "dateCreation","=",priceTypeVo.getDateCreation());
            query += SearchUtil.addConstraintMinMaxDate("o","dateArchivage",priceTypeVo.getDateArchivageMin(),priceTypeVo.getDateArchivageMax());
            query += SearchUtil.addConstraintMinMaxDate("o","dateCreation",priceTypeVo.getDateCreationMin(),priceTypeVo.getDateCreationMax());
return entityManager.createQuery(query).getResultList();
}


@Override
@Transactional
public void delete(List<PriceType> priceTypes){
if(ListUtil.isNotEmpty(priceTypes)){
priceTypes.forEach(e->priceTypeDao.delete(e));
}
}
@Override
public void update(List<PriceType> priceTypes){
if(ListUtil.isNotEmpty(priceTypes)){
priceTypes.forEach(e->priceTypeDao.save(e));
}
}





    }
