package com.ird.faa.service.chercheur.impl;

import java.util.List;
    import java.util.Date;

import java.util.ArrayList;

import com.ird.faa.bean.Expenses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.transaction.annotation.Transactional;
    import com.ird.faa.service.util.StringUtil;
    import com.ird.faa.security.common.SecurityUtil;
    import com.ird.faa.security.bean.User;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import com.ird.faa.bean.PaymentStatus;
import com.ird.faa.dao.PaymentStatusDao;
import com.ird.faa.service.chercheur.facade.PaymentStatusChercheurService;

import com.ird.faa.ws.rest.provided.vo.PaymentStatusVo;
import com.ird.faa.service.util.*;

    import com.ird.faa.service.core.facade.ArchivableService;
import com.ird.faa.service.core.impl.AbstractServiceImpl;

@Service
public class PaymentStatusChercheurServiceImpl extends AbstractServiceImpl<PaymentStatus> implements PaymentStatusChercheurService {

@Autowired
private PaymentStatusDao paymentStatusDao;

    @Autowired
    private ArchivableService<PaymentStatus> archivableService;


@Autowired
private EntityManager entityManager;


@Override
public List<PaymentStatus> findAll(){
    List<PaymentStatus> result= new ArrayList();
    result.addAll(findAllNonArchive());
    result.addAll(findAllByOwner());
    return result;
}
    @Override
    public PaymentStatus findByCode(String code){
    if( code==null) return null;
    return paymentStatusDao.findByCode(code);
    }

    @Override
    @Transactional
    public int deleteByCode(String  code) {
    return paymentStatusDao.deleteByCode(code);
    }
    @Override
    public PaymentStatus findByIdOrCode(PaymentStatus paymentStatus){
    PaymentStatus resultat=null;
    if(paymentStatus != null){
    if(StringUtil.isNotEmpty(paymentStatus.getId())){
    resultat= paymentStatusDao.getOne(paymentStatus.getId());
    }else if(StringUtil.isNotEmpty(paymentStatus.getCode())) {
    resultat= paymentStatusDao.findByCode(paymentStatus.getCode());
    }
    }
    return resultat;
    }

@Override
public PaymentStatus findById(Long id){
if(id==null) return null;
return paymentStatusDao.getOne(id);
}

@Override
public PaymentStatus findByIdWithAssociatedList(Long id){
    return findById(id);
}



@Transactional
public int deleteById(Long id){
int res=0;
if(paymentStatusDao.findById(id).isPresent())  {
paymentStatusDao.deleteById(id);
res = 1;
}
return res;
}


@Override
public PaymentStatus update(PaymentStatus paymentStatus){
PaymentStatus foundedPaymentStatus = findById(paymentStatus.getId());
if(foundedPaymentStatus==null) return null;
else{
    archivableService.prepare(paymentStatus);
return  paymentStatusDao.save(paymentStatus);
}
}
    private void prepareSave(PaymentStatus paymentStatus){
        paymentStatus.setDateCreation(new Date());
        if(paymentStatus.getDateArchivage() == null)
        paymentStatus.setDateArchivage(new Date());
                    if(paymentStatus.getArchive() == null)
                paymentStatus.setArchive(false);




    }

@Override
public PaymentStatus save (PaymentStatus paymentStatus){
    prepareSave(paymentStatus);

    PaymentStatus result =null;
    PaymentStatus foundedPaymentStatus = findByCode(paymentStatus.getCode());
    if(foundedPaymentStatus == null){




    PaymentStatus savedPaymentStatus = paymentStatusDao.save(paymentStatus);

    result = savedPaymentStatus;
    }

    return result;
}

@Override
public List<PaymentStatus> save(List<PaymentStatus> paymentStatuss){
List<PaymentStatus> list = new ArrayList<>();
for(PaymentStatus paymentStatus: paymentStatuss){
list.add(save(paymentStatus));
}
return list;
}



@Override
@Transactional
public int delete(PaymentStatus paymentStatus){
    if(paymentStatus.getCode()==null) return -1;

    PaymentStatus foundedPaymentStatus = findByCode(paymentStatus.getCode());
    if(foundedPaymentStatus==null) return -1;
paymentStatusDao.delete(foundedPaymentStatus);
return 1;
}


public List<PaymentStatus> findByCriteria(PaymentStatusVo paymentStatusVo){

String query = "SELECT o FROM PaymentStatus o where 1=1 ";

            query += SearchUtil.addConstraint( "o", "id","=",paymentStatusVo.getId());
            query += SearchUtil.addConstraint( "o", "libelle","LIKE",paymentStatusVo.getLibelle());
            query += SearchUtil.addConstraint( "o", "code","LIKE",paymentStatusVo.getCode());
            query += SearchUtil.addConstraint( "o", "archive","=",paymentStatusVo.getArchive());
        query += SearchUtil.addConstraintDate( "o", "dateArchivage","=",paymentStatusVo.getDateArchivage());
        query += SearchUtil.addConstraintDate( "o", "dateCreation","=",paymentStatusVo.getDateCreation());
            query += SearchUtil.addConstraintMinMaxDate("o","dateArchivage",paymentStatusVo.getDateArchivageMin(),paymentStatusVo.getDateArchivageMax());
            query += SearchUtil.addConstraintMinMaxDate("o","dateCreation",paymentStatusVo.getDateCreationMin(),paymentStatusVo.getDateCreationMax());
return entityManager.createQuery(query).getResultList();
}


@Override
@Transactional
public void delete(List<PaymentStatus> paymentStatuss){
if(ListUtil.isNotEmpty(paymentStatuss)){
paymentStatuss.forEach(e->paymentStatusDao.delete(e));
}
}
@Override
public void update(List<PaymentStatus> paymentStatuss){
if(ListUtil.isNotEmpty(paymentStatuss)){
paymentStatuss.forEach(e->paymentStatusDao.save(e));
}
}




        public List<PaymentStatus> findAllNonArchive(){
        String query = "SELECT o FROM PaymentStatus o  WHERE o.archive != true AND o.visible = true";
        return entityManager.createQuery(query).getResultList();
        }

        public List<PaymentStatus> findAllByOwner(){
        List<PaymentStatus> result= new ArrayList();
        User currentUser = SecurityUtil.getCurrentUser();
        if (currentUser != null && StringUtil.isNotEmpty(currentUser.getUsername())){
        String query = "SELECT o FROM PaymentStatus o  WHERE o.archive != true AND o.visible = false AND o.username = '"+ currentUser.getUsername()+"'";
        result = entityManager.createQuery(query).getResultList();
        }
        return result;
        }



    }
