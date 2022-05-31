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
import com.ird.faa.bean.BookingStatus;
import com.ird.faa.dao.BookingStatusDao;
import com.ird.faa.service.chercheur.facade.BookingStatusChercheurService;

import com.ird.faa.ws.rest.provided.vo.BookingStatusVo;
import com.ird.faa.service.util.*;

    import com.ird.faa.service.core.facade.ArchivableService;
import com.ird.faa.service.core.impl.AbstractServiceImpl;

@Service
public class BookingStatusChercheurServiceImpl extends AbstractServiceImpl<BookingStatus> implements BookingStatusChercheurService {

@Autowired
private BookingStatusDao bookingStatusDao;

    @Autowired
    private ArchivableService<BookingStatus> archivableService;


@Autowired
private EntityManager entityManager;


@Override
public List<BookingStatus> findAll(){
    List<BookingStatus> result= new ArrayList();
    result.addAll(findAllNonArchive());
    result.addAll(findAllByOwner());
    return result;
}
    @Override
    public BookingStatus findByCode(String code){
    if( code==null) return null;
    return bookingStatusDao.findByCode(code);
    }

    @Override
    @Transactional
    public int deleteByCode(String  code) {
    return bookingStatusDao.deleteByCode(code);
    }
    @Override
    public BookingStatus findByIdOrCode(BookingStatus bookingStatus){
    BookingStatus resultat=null;
    if(bookingStatus != null){
    if(StringUtil.isNotEmpty(bookingStatus.getId())){
    resultat= bookingStatusDao.getOne(bookingStatus.getId());
    }else if(StringUtil.isNotEmpty(bookingStatus.getCode())) {
    resultat= bookingStatusDao.findByCode(bookingStatus.getCode());
    }
    }
    return resultat;
    }

@Override
public BookingStatus findById(Long id){
if(id==null) return null;
return bookingStatusDao.getOne(id);
}

@Override
public BookingStatus findByIdWithAssociatedList(Long id){
    return findById(id);
}



@Transactional
public int deleteById(Long id){
int res=0;
if(bookingStatusDao.findById(id).isPresent())  {
bookingStatusDao.deleteById(id);
res = 1;
}
return res;
}


@Override
public BookingStatus update(BookingStatus bookingStatus){
BookingStatus foundedBookingStatus = findById(bookingStatus.getId());
if(foundedBookingStatus==null) return null;
else{
    archivableService.prepare(bookingStatus);
return  bookingStatusDao.save(bookingStatus);
}
}
    private void prepareSave(BookingStatus bookingStatus){
        bookingStatus.setDateCreation(new Date());
        if(bookingStatus.getDateArchivage() == null)
        bookingStatus.setDateArchivage(new Date());
                    if(bookingStatus.getArchive() == null)
                bookingStatus.setArchive(false);




    }

@Override
public BookingStatus save (BookingStatus bookingStatus){
    prepareSave(bookingStatus);

    BookingStatus result =null;
    BookingStatus foundedBookingStatus = findByCode(bookingStatus.getCode());
    if(foundedBookingStatus == null){




    BookingStatus savedBookingStatus = bookingStatusDao.save(bookingStatus);

    result = savedBookingStatus;
    }

    return result;
}

@Override
public List<BookingStatus> save(List<BookingStatus> bookingStatuss){
List<BookingStatus> list = new ArrayList<>();
for(BookingStatus bookingStatus: bookingStatuss){
list.add(save(bookingStatus));
}
return list;
}



@Override
@Transactional
public int delete(BookingStatus bookingStatus){
    if(bookingStatus.getCode()==null) return -1;

    BookingStatus foundedBookingStatus = findByCode(bookingStatus.getCode());
    if(foundedBookingStatus==null) return -1;
bookingStatusDao.delete(foundedBookingStatus);
return 1;
}


public List<BookingStatus> findByCriteria(BookingStatusVo bookingStatusVo){

String query = "SELECT o FROM BookingStatus o where 1=1 ";

            query += SearchUtil.addConstraint( "o", "id","=",bookingStatusVo.getId());
            query += SearchUtil.addConstraint( "o", "libelle","LIKE",bookingStatusVo.getLibelle());
            query += SearchUtil.addConstraint( "o", "code","LIKE",bookingStatusVo.getCode());
            query += SearchUtil.addConstraint( "o", "archive","=",bookingStatusVo.getArchive());
        query += SearchUtil.addConstraintDate( "o", "dateArchivage","=",bookingStatusVo.getDateArchivage());
        query += SearchUtil.addConstraintDate( "o", "dateCreation","=",bookingStatusVo.getDateCreation());
            query += SearchUtil.addConstraintMinMaxDate("o","dateArchivage",bookingStatusVo.getDateArchivageMin(),bookingStatusVo.getDateArchivageMax());
            query += SearchUtil.addConstraintMinMaxDate("o","dateCreation",bookingStatusVo.getDateCreationMin(),bookingStatusVo.getDateCreationMax());
return entityManager.createQuery(query).getResultList();
}


@Override
@Transactional
public void delete(List<BookingStatus> bookingStatuss){
if(ListUtil.isNotEmpty(bookingStatuss)){
bookingStatuss.forEach(e->bookingStatusDao.delete(e));
}
}
@Override
public void update(List<BookingStatus> bookingStatuss){
if(ListUtil.isNotEmpty(bookingStatuss)){
bookingStatuss.forEach(e->bookingStatusDao.save(e));
}
}




        public List<BookingStatus> findAllNonArchive(){
        String query = "SELECT o FROM BookingStatus o  WHERE o.archive != true AND o.visible = true";
        return entityManager.createQuery(query).getResultList();
        }

        public List<BookingStatus> findAllByOwner(){
        List<BookingStatus> result= new ArrayList();
        User currentUser = SecurityUtil.getCurrentUser();
        if (currentUser != null && StringUtil.isNotEmpty(currentUser.getUsername())){
        String query = "SELECT o FROM BookingStatus o  WHERE o.archive != true AND o.visible = false AND o.username = '"+ currentUser.getUsername()+"'";
        result = entityManager.createQuery(query).getResultList();
        }
        return result;
        }



    }
