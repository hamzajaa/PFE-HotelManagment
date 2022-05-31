package com.ird.faa.service.employee.impl;

import java.util.List;
    import java.util.Date;

import java.util.ArrayList;

import com.ird.faa.bean.Expenses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import com.ird.faa.bean.CouponType;
import com.ird.faa.dao.CouponTypeDao;
import com.ird.faa.service.employee.facade.CouponTypeEmployeeService;

import com.ird.faa.ws.rest.provided.vo.CouponTypeVo;
import com.ird.faa.service.util.*;

    import com.ird.faa.service.core.facade.ArchivableService;
import com.ird.faa.service.core.impl.AbstractServiceImpl;

@Service
public class CouponTypeEmployeeServiceImpl extends AbstractServiceImpl<CouponType> implements CouponTypeEmployeeService {

@Autowired
private CouponTypeDao couponTypeDao;

    @Autowired
    private ArchivableService<CouponType> archivableService;


@Autowired
private EntityManager entityManager;


@Override
public List<CouponType> findAll(){
        return couponTypeDao.findAll();
}
    @Override
    public CouponType findByCode(String code){
    if( code==null) return null;
    return couponTypeDao.findByCode(code);
    }

    @Override
    @Transactional
    public int deleteByCode(String  code) {
    return couponTypeDao.deleteByCode(code);
    }
    @Override
    public CouponType findByIdOrCode(CouponType couponType){
    CouponType resultat=null;
    if(couponType != null){
    if(StringUtil.isNotEmpty(couponType.getId())){
    resultat= couponTypeDao.getOne(couponType.getId());
    }else if(StringUtil.isNotEmpty(couponType.getCode())) {
    resultat= couponTypeDao.findByCode(couponType.getCode());
    }
    }
    return resultat;
    }

@Override
public CouponType findById(Long id){
if(id==null) return null;
return couponTypeDao.getOne(id);
}

@Override
public CouponType findByIdWithAssociatedList(Long id){
    return findById(id);
}
    @Override
    public CouponType archiver(CouponType couponType) {
    if (couponType.getArchive() == null) {
    couponType.setArchive(false);
    }
    couponType.setArchive(true);
    couponType.setDateArchivage(new Date());
    couponTypeDao.save(couponType);
    return couponType;

    }

    @Override
    public CouponType desarchiver(CouponType couponType) {
    if (couponType.getArchive() == null) {
    couponType.setArchive(false);
    }
    couponType.setArchive(false);
    couponTypeDao.save(couponType);
    return couponType;
    }




@Transactional
public int deleteById(Long id){
int res=0;
if(couponTypeDao.findById(id).isPresent())  {
couponTypeDao.deleteById(id);
res = 1;
}
return res;
}


@Override
public CouponType update(CouponType couponType){
CouponType foundedCouponType = findById(couponType.getId());
if(foundedCouponType==null) return null;
else{
    archivableService.prepare(couponType);
return  couponTypeDao.save(couponType);
}
}
    private void prepareSave(CouponType couponType){
        couponType.setDateCreation(new Date());
        if(couponType.getDateArchivage() == null)
        couponType.setDateArchivage(new Date());
                    if(couponType.getArchive() == null)
                couponType.setArchive(false);




    }

@Override
public CouponType save (CouponType couponType){
    prepareSave(couponType);

    CouponType result =null;
    CouponType foundedCouponType = findByCode(couponType.getCode());
    if(foundedCouponType == null){




    CouponType savedCouponType = couponTypeDao.save(couponType);

    result = savedCouponType;
    }

    return result;
}

@Override
public List<CouponType> save(List<CouponType> couponTypes){
List<CouponType> list = new ArrayList<>();
for(CouponType couponType: couponTypes){
list.add(save(couponType));
}
return list;
}



@Override
@Transactional
public int delete(CouponType couponType){
    if(couponType.getCode()==null) return -1;

    CouponType foundedCouponType = findByCode(couponType.getCode());
    if(foundedCouponType==null) return -1;
couponTypeDao.delete(foundedCouponType);
return 1;
}


public List<CouponType> findByCriteria(CouponTypeVo couponTypeVo){

String query = "SELECT o FROM CouponType o where 1=1 ";

            query += SearchUtil.addConstraint( "o", "id","=",couponTypeVo.getId());
            query += SearchUtil.addConstraint( "o", "name","LIKE",couponTypeVo.getName());
            query += SearchUtil.addConstraint( "o", "code","LIKE",couponTypeVo.getCode());
            query += SearchUtil.addConstraint( "o", "archive","=",couponTypeVo.getArchive());
        query += SearchUtil.addConstraintDate( "o", "dateArchivage","=",couponTypeVo.getDateArchivage());
        query += SearchUtil.addConstraintDate( "o", "dateCreation","=",couponTypeVo.getDateCreation());
            query += SearchUtil.addConstraintMinMaxDate("o","dateArchivage",couponTypeVo.getDateArchivageMin(),couponTypeVo.getDateArchivageMax());
            query += SearchUtil.addConstraintMinMaxDate("o","dateCreation",couponTypeVo.getDateCreationMin(),couponTypeVo.getDateCreationMax());
return entityManager.createQuery(query).getResultList();
}


@Override
@Transactional
public void delete(List<CouponType> couponTypes){
if(ListUtil.isNotEmpty(couponTypes)){
couponTypes.forEach(e->couponTypeDao.delete(e));
}
}
@Override
public void update(List<CouponType> couponTypes){
if(ListUtil.isNotEmpty(couponTypes)){
couponTypes.forEach(e->couponTypeDao.save(e));
}
}





    }
