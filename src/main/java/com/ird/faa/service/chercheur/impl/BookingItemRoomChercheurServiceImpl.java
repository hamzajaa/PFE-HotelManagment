package com.ird.faa.service.chercheur.impl;

import java.util.List;
    import java.util.Date;

import java.util.ArrayList;

import com.ird.faa.bean.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
    import com.ird.faa.service.util.StringUtil;
    import com.ird.faa.security.common.SecurityUtil;
    import com.ird.faa.security.bean.User;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;

import com.ird.faa.dao.BookingItemRoomDao;
import com.ird.faa.service.chercheur.facade.BookingItemRoomChercheurService;
        import com.ird.faa.service.chercheur.facade.BookingRoomStateChercheurService;
        import com.ird.faa.service.chercheur.facade.BookingChercheurService;
        import com.ird.faa.service.chercheur.facade.RoomChercheurService;

import com.ird.faa.ws.rest.provided.vo.BookingItemRoomVo;
import com.ird.faa.service.util.*;

    import com.ird.faa.service.core.facade.ArchivableService;
import com.ird.faa.service.core.impl.AbstractServiceImpl;

@Service
public class BookingItemRoomChercheurServiceImpl extends AbstractServiceImpl<BookingItemRoom> implements BookingItemRoomChercheurService {

@Autowired
private BookingItemRoomDao bookingItemRoomDao;

    @Autowired
    private ArchivableService<BookingItemRoom> archivableService;
        @Autowired
        private BookingRoomStateChercheurService bookingRoomStateService ;
        @Autowired
        private BookingChercheurService bookingService ;
        @Autowired
        private RoomChercheurService roomService ;


@Autowired
private EntityManager entityManager;


@Override
public List<BookingItemRoom> findAll(){
    List<BookingItemRoom> result= new ArrayList();
    result.addAll(findAllNonArchive());
    result.addAll(findAllByOwner());
    return result;
}

        @Override
        public List<BookingItemRoom> findByRoomRoomNumber(Long roomNumber){
        return bookingItemRoomDao.findByRoomRoomNumber(roomNumber);
        }

        @Override
        @Transactional
        public int deleteByRoomRoomNumber(Long roomNumber){
        return bookingItemRoomDao.deleteByRoomRoomNumber(roomNumber);
        }

        @Override
        public List<BookingItemRoom> findByRoomId(Long id){
        return bookingItemRoomDao.findByRoomId(id);
        }

        @Override
        @Transactional
        public int deleteByRoomId(Long id){
        return bookingItemRoomDao.deleteByRoomId(id);
        }

        @Override
        public List<BookingItemRoom> findByBookingId(Long id){
        return bookingItemRoomDao.findByBookingId(id);
        }

        @Override
        @Transactional
        public int deleteByBookingId(Long id){
        return bookingItemRoomDao.deleteByBookingId(id);
        }


        @Override
        public List<BookingItemRoom> findByBookingRoomStateCode(String code){
        return bookingItemRoomDao.findByBookingRoomStateCode(code);
        }

        @Override
        @Transactional
        public int deleteByBookingRoomStateCode(String code){
        return bookingItemRoomDao.deleteByBookingRoomStateCode(code);
        }

        @Override
        public List<BookingItemRoom> findByBookingRoomStateId(Long id){
        return bookingItemRoomDao.findByBookingRoomStateId(id);
        }

        @Override
        @Transactional
        public int deleteByBookingRoomStateId(Long id){
        return bookingItemRoomDao.deleteByBookingRoomStateId(id);
        }


@Override
public BookingItemRoom findById(Long id){
if(id==null) return null;
return bookingItemRoomDao.getOne(id);
}

@Override
public BookingItemRoom findByIdWithAssociatedList(Long id){
    return findById(id);
}



@Transactional
public int deleteById(Long id){
int res=0;
if(bookingItemRoomDao.findById(id).isPresent())  {
bookingItemRoomDao.deleteById(id);
res = 1;
}
return res;
}


@Override
public BookingItemRoom update(BookingItemRoom bookingItemRoom){
BookingItemRoom foundedBookingItemRoom = findById(bookingItemRoom.getId());
if(foundedBookingItemRoom==null) return null;
else{
    archivableService.prepare(bookingItemRoom);
return  bookingItemRoomDao.save(bookingItemRoom);
}
}
    private void prepareSave(BookingItemRoom bookingItemRoom){
        bookingItemRoom.setDateCreation(new Date());
        if(bookingItemRoom.getDateArchivage() == null)
        bookingItemRoom.setDateArchivage(new Date());
                    if(bookingItemRoom.getArchive() == null)
                bookingItemRoom.setArchive(false);




    }

@Override
public BookingItemRoom save (BookingItemRoom bookingItemRoom){
    prepareSave(bookingItemRoom);



    findRoom(bookingItemRoom);
    findBooking(bookingItemRoom);
    findBookingRoomState(bookingItemRoom);

    return bookingItemRoomDao.save(bookingItemRoom);


}

@Override
public List<BookingItemRoom> save(List<BookingItemRoom> bookingItemRooms){
List<BookingItemRoom> list = new ArrayList<>();
for(BookingItemRoom bookingItemRoom: bookingItemRooms){
list.add(save(bookingItemRoom));
}
return list;
}



@Override
@Transactional
public int delete(BookingItemRoom bookingItemRoom){
    if(bookingItemRoom.getId()==null) return -1;
    BookingItemRoom foundedBookingItemRoom = findById(bookingItemRoom.getId());
    if(foundedBookingItemRoom==null) return -1;
bookingItemRoomDao.delete(foundedBookingItemRoom);
return 1;
}


public List<BookingItemRoom> findByCriteria(BookingItemRoomVo bookingItemRoomVo){

String query = "SELECT o FROM BookingItemRoom o where 1=1 ";

            query += SearchUtil.addConstraint( "o", "id","=",bookingItemRoomVo.getId());
        query += SearchUtil.addConstraintDate( "o", "dateRoomState","=",bookingItemRoomVo.getDateRoomState());
            query += SearchUtil.addConstraint( "o", "archive","=",bookingItemRoomVo.getArchive());
        query += SearchUtil.addConstraintDate( "o", "dateArchivage","=",bookingItemRoomVo.getDateArchivage());
        query += SearchUtil.addConstraintDate( "o", "dateCreation","=",bookingItemRoomVo.getDateCreation());
            query += SearchUtil.addConstraintMinMaxDate("o","dateRoomState",bookingItemRoomVo.getDateRoomStateMin(),bookingItemRoomVo.getDateRoomStateMax());
            query += SearchUtil.addConstraintMinMaxDate("o","dateArchivage",bookingItemRoomVo.getDateArchivageMin(),bookingItemRoomVo.getDateArchivageMax());
            query += SearchUtil.addConstraintMinMaxDate("o","dateCreation",bookingItemRoomVo.getDateCreationMin(),bookingItemRoomVo.getDateCreationMax());
    if(bookingItemRoomVo.getRoomVo()!=null){
        query += SearchUtil.addConstraint( "o", "room.id","=",bookingItemRoomVo.getRoomVo().getId());
            query += SearchUtil.addConstraint( "o", "room.roomNumber","=",bookingItemRoomVo.getRoomVo().getRoomNumber());
    }

    if(bookingItemRoomVo.getBookingVo()!=null){
        query += SearchUtil.addConstraint( "o", "booking.id","=",bookingItemRoomVo.getBookingVo().getId());
    }

    if(bookingItemRoomVo.getBookingRoomStateVo()!=null){
        query += SearchUtil.addConstraint( "o", "bookingRoomState.id","=",bookingItemRoomVo.getBookingRoomStateVo().getId());
            query += SearchUtil.addConstraint( "o", "bookingRoomState.code","LIKE",bookingItemRoomVo.getBookingRoomStateVo().getCode());
    }

return entityManager.createQuery(query).getResultList();
}

    private void findRoom(BookingItemRoom bookingItemRoom){
        Room loadedRoom =roomService.findByIdOrRoomNumber(bookingItemRoom.getRoom());

    if(loadedRoom==null ) {
    return;
    }
    bookingItemRoom.setRoom(loadedRoom);
    }
    private void findBooking(BookingItemRoom bookingItemRoom){
        Booking loadedBooking = null;
        if(bookingItemRoom.getBooking() != null && bookingItemRoom.getBooking().getId() !=null)
        loadedBooking =bookingService.findById(bookingItemRoom.getBooking().getId());

    if(loadedBooking==null ) {
    return;
    }
    bookingItemRoom.setBooking(loadedBooking);
    }
    private void findBookingRoomState(BookingItemRoom bookingItemRoom){
        BookingRoomState loadedBookingRoomState =bookingRoomStateService.findByIdOrCode(bookingItemRoom.getBookingRoomState());

    if(loadedBookingRoomState==null ) {
    return;
    }
    bookingItemRoom.setBookingRoomState(loadedBookingRoomState);
    }

@Override
@Transactional
public void delete(List<BookingItemRoom> bookingItemRooms){
if(ListUtil.isNotEmpty(bookingItemRooms)){
bookingItemRooms.forEach(e->bookingItemRoomDao.delete(e));
}
}
@Override
public void update(List<BookingItemRoom> bookingItemRooms){
if(ListUtil.isNotEmpty(bookingItemRooms)){
bookingItemRooms.forEach(e->bookingItemRoomDao.save(e));
}
}




        public List<BookingItemRoom> findAllNonArchive(){
        String query = "SELECT o FROM BookingItemRoom o  WHERE o.archive != true AND o.visible = true";
        return entityManager.createQuery(query).getResultList();
        }

        public List<BookingItemRoom> findAllByOwner(){
        List<BookingItemRoom> result= new ArrayList();
        User currentUser = SecurityUtil.getCurrentUser();
        if (currentUser != null && StringUtil.isNotEmpty(currentUser.getUsername())){
        String query = "SELECT o FROM BookingItemRoom o  WHERE o.archive != true AND o.visible = false AND o.username = '"+ currentUser.getUsername()+"'";
        result = entityManager.createQuery(query).getResultList();
        }
        return result;
        }



    }
