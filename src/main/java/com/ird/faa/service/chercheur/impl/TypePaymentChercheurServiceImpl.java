package com.ird.faa.service.chercheur.impl;

import java.util.List;
    import java.util.Date;

import java.util.ArrayList;

import com.ird.faa.bean.Expenses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
    import com.ird.faa.service.util.StringUtil;
    import com.ird.faa.security.common.SecurityUtil;
    import com.ird.faa.security.bean.User;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import com.ird.faa.bean.TypePayment;
import com.ird.faa.dao.TypePaymentDao;
import com.ird.faa.service.chercheur.facade.TypePaymentChercheurService;

import com.ird.faa.ws.rest.provided.vo.TypePaymentVo;
import com.ird.faa.service.util.*;

    import com.ird.faa.service.core.facade.ArchivableService;
import com.ird.faa.service.core.impl.AbstractServiceImpl;

@Service
public class TypePaymentChercheurServiceImpl extends AbstractServiceImpl<TypePayment> implements TypePaymentChercheurService {

@Autowired
private TypePaymentDao typePaymentDao;

    @Autowired
    private ArchivableService<TypePayment> archivableService;


@Autowired
private EntityManager entityManager;


@Override
public List<TypePayment> findAll(){
    List<TypePayment> result= new ArrayList();
    result.addAll(findAllNonArchive());
    result.addAll(findAllByOwner());
    return result;
}
    @Override
    public TypePayment findByCode(String code){
    if( code==null) return null;
    return typePaymentDao.findByCode(code);
    }

    @Override
    @Transactional
    public int deleteByCode(String  code) {
    return typePaymentDao.deleteByCode(code);
    }
    @Override
    public TypePayment findByIdOrCode(TypePayment typePayment){
    TypePayment resultat=null;
    if(typePayment != null){
    if(StringUtil.isNotEmpty(typePayment.getId())){
    resultat= typePaymentDao.getOne(typePayment.getId());
    }else if(StringUtil.isNotEmpty(typePayment.getCode())) {
    resultat= typePaymentDao.findByCode(typePayment.getCode());
    }
    }
    return resultat;
    }

@Override
public TypePayment findById(Long id){
if(id==null) return null;
return typePaymentDao.getOne(id);
}

@Override
public TypePayment findByIdWithAssociatedList(Long id){
    return findById(id);
}



@Transactional
public int deleteById(Long id){
int res=0;
if(typePaymentDao.findById(id).isPresent())  {
typePaymentDao.deleteById(id);
res = 1;
}
return res;
}


@Override
public TypePayment update(TypePayment typePayment){
TypePayment foundedTypePayment = findById(typePayment.getId());
if(foundedTypePayment==null) return null;
else{
    archivableService.prepare(typePayment);
return  typePaymentDao.save(typePayment);
}
}
    private void prepareSave(TypePayment typePayment){
        typePayment.setDateCreation(new Date());
        if(typePayment.getDateArchivage() == null)
        typePayment.setDateArchivage(new Date());
                    if(typePayment.getArchive() == null)
                typePayment.setArchive(false);




    }

@Override
public TypePayment save (TypePayment typePayment){
    prepareSave(typePayment);

    TypePayment result =null;
    TypePayment foundedTypePayment = findByCode(typePayment.getCode());
    if(foundedTypePayment == null){




    TypePayment savedTypePayment = typePaymentDao.save(typePayment);

    result = savedTypePayment;
    }

    return result;
}

@Override
public List<TypePayment> save(List<TypePayment> typePayments){
List<TypePayment> list = new ArrayList<>();
for(TypePayment typePayment: typePayments){
list.add(save(typePayment));
}
return list;
}



@Override
@Transactional
public int delete(TypePayment typePayment){
    if(typePayment.getCode()==null) return -1;

    TypePayment foundedTypePayment = findByCode(typePayment.getCode());
    if(foundedTypePayment==null) return -1;
typePaymentDao.delete(foundedTypePayment);
return 1;
}


public List<TypePayment> findByCriteria(TypePaymentVo typePaymentVo){

String query = "SELECT o FROM TypePayment o where 1=1 ";

            query += SearchUtil.addConstraint( "o", "id","=",typePaymentVo.getId());
            query += SearchUtil.addConstraint( "o", "name","LIKE",typePaymentVo.getName());
            query += SearchUtil.addConstraint( "o", "code","LIKE",typePaymentVo.getCode());
            query += SearchUtil.addConstraint( "o", "archive","=",typePaymentVo.getArchive());
        query += SearchUtil.addConstraintDate( "o", "dateArchivage","=",typePaymentVo.getDateArchivage());
        query += SearchUtil.addConstraintDate( "o", "dateCreation","=",typePaymentVo.getDateCreation());
            query += SearchUtil.addConstraintMinMaxDate("o","dateArchivage",typePaymentVo.getDateArchivageMin(),typePaymentVo.getDateArchivageMax());
            query += SearchUtil.addConstraintMinMaxDate("o","dateCreation",typePaymentVo.getDateCreationMin(),typePaymentVo.getDateCreationMax());
return entityManager.createQuery(query).getResultList();
}


@Override
@Transactional
public void delete(List<TypePayment> typePayments){
if(ListUtil.isNotEmpty(typePayments)){
typePayments.forEach(e->typePaymentDao.delete(e));
}
}
@Override
public void update(List<TypePayment> typePayments){
if(ListUtil.isNotEmpty(typePayments)){
typePayments.forEach(e->typePaymentDao.save(e));
}
}




        public List<TypePayment> findAllNonArchive(){
        String query = "SELECT o FROM TypePayment o  WHERE o.archive != true AND o.visible = true";
        return entityManager.createQuery(query).getResultList();
        }

        public List<TypePayment> findAllByOwner(){
        List<TypePayment> result= new ArrayList();
        User currentUser = SecurityUtil.getCurrentUser();
        if (currentUser != null && StringUtil.isNotEmpty(currentUser.getUsername())){
        String query = "SELECT o FROM TypePayment o  WHERE o.archive != true AND o.visible = false AND o.username = '"+ currentUser.getUsername()+"'";
        result = entityManager.createQuery(query).getResultList();
        }
        return result;
        }



    }
