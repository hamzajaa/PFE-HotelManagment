package com.ird.faa.service.admin.impl;

import java.util.List;
    import java.util.Date;

import java.util.ArrayList;

import com.ird.faa.bean.Expenses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import com.ird.faa.bean.PaymentStatus;
import com.ird.faa.dao.PaymentStatusDao;
import com.ird.faa.service.admin.facade.PaymentStatusAdminService;

import com.ird.faa.ws.rest.provided.vo.PaymentStatusVo;
import com.ird.faa.service.util.*;

    import com.ird.faa.service.core.facade.ArchivableService;
import com.ird.faa.service.core.impl.AbstractServiceImpl;

@Service
public class PaymentStatusAdminServiceImpl extends AbstractServiceImpl<PaymentStatus> implements PaymentStatusAdminService {

@Autowired
private PaymentStatusDao paymentStatusDao;

    @Autowired
    private ArchivableService<PaymentStatus> archivableService;


@Autowired
private EntityManager entityManager;


@Override
public List<PaymentStatus> findAll(){
        return paymentStatusDao.findAll();
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
    @Override
    public PaymentStatus archiver(PaymentStatus paymentStatus) {
    if (paymentStatus.getArchive() == null) {
    paymentStatus.setArchive(false);
    }
    paymentStatus.setArchive(true);
    paymentStatus.setDateArchivage(new Date());
    paymentStatusDao.save(paymentStatus);
    return paymentStatus;

    }

    @Override
    public PaymentStatus desarchiver(PaymentStatus paymentStatus) {
    if (paymentStatus.getArchive() == null) {
    paymentStatus.setArchive(false);
    }
    paymentStatus.setArchive(false);
    paymentStatusDao.save(paymentStatus);
    return paymentStatus;
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





    }
