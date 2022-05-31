package com.ird.faa.service.guest.impl;

import java.util.List;
    import java.util.Date;

import java.util.ArrayList;

import com.ird.faa.bean.Expenses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import com.ird.faa.bean.BookingRoomState;
import com.ird.faa.dao.BookingRoomStateDao;
import com.ird.faa.service.guest.facade.BookingRoomStateGuestService;

import com.ird.faa.ws.rest.provided.vo.BookingRoomStateVo;
import com.ird.faa.service.util.*;

    import com.ird.faa.service.core.facade.ArchivableService;
import com.ird.faa.service.core.impl.AbstractServiceImpl;

@Service
public class BookingRoomStateGuestServiceImpl extends AbstractServiceImpl<BookingRoomState> implements BookingRoomStateGuestService {

@Autowired
private BookingRoomStateDao bookingRoomStateDao;

    @Autowired
    private ArchivableService<BookingRoomState> archivableService;


@Autowired
private EntityManager entityManager;


@Override
public List<BookingRoomState> findAll(){
        return bookingRoomStateDao.findAll();
}
    @Override
    public BookingRoomState findByCode(String code){
    if( code==null) return null;
    return bookingRoomStateDao.findByCode(code);
    }

    @Override
    @Transactional
    public int deleteByCode(String  code) {
    return bookingRoomStateDao.deleteByCode(code);
    }
    @Override
    public BookingRoomState findByIdOrCode(BookingRoomState bookingRoomState){
    BookingRoomState resultat=null;
    if(bookingRoomState != null){
    if(StringUtil.isNotEmpty(bookingRoomState.getId())){
    resultat= bookingRoomStateDao.getOne(bookingRoomState.getId());
    }else if(StringUtil.isNotEmpty(bookingRoomState.getCode())) {
    resultat= bookingRoomStateDao.findByCode(bookingRoomState.getCode());
    }
    }
    return resultat;
    }

@Override
public BookingRoomState findById(Long id){
if(id==null) return null;
return bookingRoomStateDao.getOne(id);
}

@Override
public BookingRoomState findByIdWithAssociatedList(Long id){
    return findById(id);
}
    @Override
    public BookingRoomState archiver(BookingRoomState bookingRoomState) {
    if (bookingRoomState.getArchive() == null) {
    bookingRoomState.setArchive(false);
    }
    bookingRoomState.setArchive(true);
    bookingRoomState.setDateArchivage(new Date());
    bookingRoomStateDao.save(bookingRoomState);
    return bookingRoomState;

    }

    @Override
    public BookingRoomState desarchiver(BookingRoomState bookingRoomState) {
    if (bookingRoomState.getArchive() == null) {
    bookingRoomState.setArchive(false);
    }
    bookingRoomState.setArchive(false);
    bookingRoomStateDao.save(bookingRoomState);
    return bookingRoomState;
    }




@Transactional
public int deleteById(Long id){
int res=0;
if(bookingRoomStateDao.findById(id).isPresent())  {
bookingRoomStateDao.deleteById(id);
res = 1;
}
return res;
}


@Override
public BookingRoomState update(BookingRoomState bookingRoomState){
BookingRoomState foundedBookingRoomState = findById(bookingRoomState.getId());
if(foundedBookingRoomState==null) return null;
else{
    archivableService.prepare(bookingRoomState);
return  bookingRoomStateDao.save(bookingRoomState);
}
}
    private void prepareSave(BookingRoomState bookingRoomState){
        bookingRoomState.setDateCreation(new Date());
        if(bookingRoomState.getDateArchivage() == null)
        bookingRoomState.setDateArchivage(new Date());
                    if(bookingRoomState.getArchive() == null)
                bookingRoomState.setArchive(false);




    }

@Override
public BookingRoomState save (BookingRoomState bookingRoomState){
    prepareSave(bookingRoomState);

    BookingRoomState result =null;
    BookingRoomState foundedBookingRoomState = findByCode(bookingRoomState.getCode());
    if(foundedBookingRoomState == null){




    BookingRoomState savedBookingRoomState = bookingRoomStateDao.save(bookingRoomState);

    result = savedBookingRoomState;
    }

    return result;
}

@Override
public List<BookingRoomState> save(List<BookingRoomState> bookingRoomStates){
List<BookingRoomState> list = new ArrayList<>();
for(BookingRoomState bookingRoomState: bookingRoomStates){
list.add(save(bookingRoomState));
}
return list;
}



@Override
@Transactional
public int delete(BookingRoomState bookingRoomState){
    if(bookingRoomState.getCode()==null) return -1;

    BookingRoomState foundedBookingRoomState = findByCode(bookingRoomState.getCode());
    if(foundedBookingRoomState==null) return -1;
bookingRoomStateDao.delete(foundedBookingRoomState);
return 1;
}


public List<BookingRoomState> findByCriteria(BookingRoomStateVo bookingRoomStateVo){

String query = "SELECT o FROM BookingRoomState o where 1=1 ";

            query += SearchUtil.addConstraint( "o", "id","=",bookingRoomStateVo.getId());
            query += SearchUtil.addConstraint( "o", "libelle","LIKE",bookingRoomStateVo.getLibelle());
            query += SearchUtil.addConstraint( "o", "code","LIKE",bookingRoomStateVo.getCode());
            query += SearchUtil.addConstraint( "o", "archive","=",bookingRoomStateVo.getArchive());
        query += SearchUtil.addConstraintDate( "o", "dateArchivage","=",bookingRoomStateVo.getDateArchivage());
        query += SearchUtil.addConstraintDate( "o", "dateCreation","=",bookingRoomStateVo.getDateCreation());
            query += SearchUtil.addConstraintMinMaxDate("o","dateArchivage",bookingRoomStateVo.getDateArchivageMin(),bookingRoomStateVo.getDateArchivageMax());
            query += SearchUtil.addConstraintMinMaxDate("o","dateCreation",bookingRoomStateVo.getDateCreationMin(),bookingRoomStateVo.getDateCreationMax());
return entityManager.createQuery(query).getResultList();
}


@Override
@Transactional
public void delete(List<BookingRoomState> bookingRoomStates){
if(ListUtil.isNotEmpty(bookingRoomStates)){
bookingRoomStates.forEach(e->bookingRoomStateDao.delete(e));
}
}
@Override
public void update(List<BookingRoomState> bookingRoomStates){
if(ListUtil.isNotEmpty(bookingRoomStates)){
bookingRoomStates.forEach(e->bookingRoomStateDao.save(e));
}
}





    }
