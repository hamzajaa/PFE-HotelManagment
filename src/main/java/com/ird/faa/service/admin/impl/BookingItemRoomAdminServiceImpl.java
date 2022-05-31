package com.ird.faa.service.admin.impl;

import com.ird.faa.bean.Booking;
import com.ird.faa.bean.BookingItemRoom;
import com.ird.faa.bean.BookingRoomState;
import com.ird.faa.bean.Room;
import com.ird.faa.dao.BookingItemRoomDao;
import com.ird.faa.service.admin.facade.BookingAdminService;
import com.ird.faa.service.admin.facade.BookingItemRoomAdminService;
import com.ird.faa.service.admin.facade.BookingRoomStateAdminService;
import com.ird.faa.service.admin.facade.RoomAdminService;
import com.ird.faa.service.core.facade.ArchivableService;
import com.ird.faa.service.core.impl.AbstractServiceImpl;
import com.ird.faa.service.util.ListUtil;
import com.ird.faa.service.util.SearchUtil;
import com.ird.faa.ws.rest.provided.vo.BookingItemRoomVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BookingItemRoomAdminServiceImpl extends AbstractServiceImpl<BookingItemRoom> implements BookingItemRoomAdminService {

    @Autowired
    private BookingItemRoomDao bookingItemRoomDao;

    @Autowired
    private ArchivableService<BookingItemRoom> archivableService;
    @Autowired
    private BookingRoomStateAdminService bookingRoomStateService;
    @Autowired
    private BookingAdminService bookingService;
    @Autowired
    private RoomAdminService roomService;


    @Autowired
    private EntityManager entityManager;


    @Override
    public List<BookingItemRoom> findAll() {
        return bookingItemRoomDao.findAll();
    }

    @Override
    public List<BookingItemRoom> findByRoomRoomNumber(Long roomNumber) {
        return bookingItemRoomDao.findByRoomRoomNumber(roomNumber);
    }

    @Override
    @Transactional
    public int deleteByRoomRoomNumber(Long roomNumber) {
        return bookingItemRoomDao.deleteByRoomRoomNumber(roomNumber);
    }

    @Override
    public List<BookingItemRoom> findByRoomId(Long id) {
        return bookingItemRoomDao.findByRoomId(id);
    }

    @Override
    @Transactional
    public int deleteByRoomId(Long id) {
        return bookingItemRoomDao.deleteByRoomId(id);
    }

    @Override
    public List<BookingItemRoom> findByBookingId(Long id) {
        return bookingItemRoomDao.findByBookingId(id);
    }

    @Override
    @Transactional
    public int deleteByBookingId(Long id) {
        return bookingItemRoomDao.deleteByBookingId(id);
    }


    @Override
    public List<BookingItemRoom> findByBookingRoomStateCode(String code) {
        return bookingItemRoomDao.findByBookingRoomStateCode(code);
    }

    @Override
    @Transactional
    public int deleteByBookingRoomStateCode(String code) {
        return bookingItemRoomDao.deleteByBookingRoomStateCode(code);
    }

    @Override
    public List<BookingItemRoom> findByBookingRoomStateId(Long id) {
        return bookingItemRoomDao.findByBookingRoomStateId(id);
    }

    @Override
    @Transactional
    public int deleteByBookingRoomStateId(Long id) {
        return bookingItemRoomDao.deleteByBookingRoomStateId(id);
    }


    @Override
    public BookingItemRoom findById(Long id) {
        if (id == null) return null;
        return bookingItemRoomDao.getOne(id);
    }

    @Override
    public BookingItemRoom findByIdWithAssociatedList(Long id) {
        return findById(id);
    }

    @Override
    public BookingItemRoom archiver(BookingItemRoom bookingItemRoom) {
        if (bookingItemRoom.getArchive() == null) {
            bookingItemRoom.setArchive(false);
        }
        bookingItemRoom.setArchive(true);
        bookingItemRoom.setDateArchivage(new Date());
        bookingItemRoomDao.save(bookingItemRoom);
        return bookingItemRoom;

    }

    @Override
    public BookingItemRoom desarchiver(BookingItemRoom bookingItemRoom) {
        if (bookingItemRoom.getArchive() == null) {
            bookingItemRoom.setArchive(false);
        }
        bookingItemRoom.setArchive(false);
        bookingItemRoomDao.save(bookingItemRoom);
        return bookingItemRoom;
    }


    @Transactional
    public int deleteById(Long id) {
        int res = 0;
        if (bookingItemRoomDao.findById(id).isPresent()) {
            bookingItemRoomDao.deleteById(id);
            res = 1;
        }
        return res;
    }


    @Override
    public BookingItemRoom update(BookingItemRoom bookingItemRoom) {
        BookingItemRoom foundedBookingItemRoom = findById(bookingItemRoom.getId());
        if (foundedBookingItemRoom == null) return null;
        else {
            archivableService.prepare(bookingItemRoom);
            return bookingItemRoomDao.save(bookingItemRoom);
        }
    }

    private void prepareSave(BookingItemRoom bookingItemRoom) {
        bookingItemRoom.setDateCreation(new Date());
        if (bookingItemRoom.getDateArchivage() == null)
            bookingItemRoom.setDateArchivage(new Date());
        if (bookingItemRoom.getArchive() == null)
            bookingItemRoom.setArchive(false);


    }

    @Override
    public BookingItemRoom save(BookingItemRoom bookingItemRoom) {
        prepareSave(bookingItemRoom);


        findRoom(bookingItemRoom);
        findBooking(bookingItemRoom);
        findBookingRoomState(bookingItemRoom);

        return bookingItemRoomDao.save(bookingItemRoom);


    }

    @Override
    public List<BookingItemRoom> save(List<BookingItemRoom> bookingItemRooms) {
        List<BookingItemRoom> list = new ArrayList<>();
        for (BookingItemRoom bookingItemRoom : bookingItemRooms) {
            list.add(save(bookingItemRoom));
        }
        return list;
    }


    @Override
    @Transactional
    public int delete(BookingItemRoom bookingItemRoom) {
        if (bookingItemRoom.getId() == null) return -1;
        BookingItemRoom foundedBookingItemRoom = findById(bookingItemRoom.getId());
        if (foundedBookingItemRoom == null) return -1;
        bookingItemRoomDao.delete(foundedBookingItemRoom);
        return 1;
    }


    public List<BookingItemRoom> findByCriteria(BookingItemRoomVo bookingItemRoomVo) {

        String query = "SELECT o FROM BookingItemRoom o where 1=1 ";

        query += SearchUtil.addConstraint("o", "id", "=", bookingItemRoomVo.getId());
        query += SearchUtil.addConstraintDate("o", "dateRoomState", "=", bookingItemRoomVo.getDateRoomState());
        query += SearchUtil.addConstraint("o", "archive", "=", bookingItemRoomVo.getArchive());
        query += SearchUtil.addConstraintDate("o", "dateArchivage", "=", bookingItemRoomVo.getDateArchivage());
        query += SearchUtil.addConstraintDate("o", "dateCreation", "=", bookingItemRoomVo.getDateCreation());
        query += SearchUtil.addConstraintMinMaxDate("o", "dateRoomState", bookingItemRoomVo.getDateRoomStateMin(), bookingItemRoomVo.getDateRoomStateMax());
        query += SearchUtil.addConstraintMinMaxDate("o", "dateArchivage", bookingItemRoomVo.getDateArchivageMin(), bookingItemRoomVo.getDateArchivageMax());
        query += SearchUtil.addConstraintMinMaxDate("o", "dateCreation", bookingItemRoomVo.getDateCreationMin(), bookingItemRoomVo.getDateCreationMax());
        if (bookingItemRoomVo.getRoomVo() != null) {
            query += SearchUtil.addConstraint("o", "room.id", "=", bookingItemRoomVo.getRoomVo().getId());
            query += SearchUtil.addConstraint("o", "room.roomNumber", "=", bookingItemRoomVo.getRoomVo().getRoomNumber());
        }

        if (bookingItemRoomVo.getBookingVo() != null) {
            query += SearchUtil.addConstraint("o", "booking.id", "=", bookingItemRoomVo.getBookingVo().getId());
        }

        if (bookingItemRoomVo.getBookingRoomStateVo() != null) {
            query += SearchUtil.addConstraint("o", "bookingRoomState.id", "=", bookingItemRoomVo.getBookingRoomStateVo().getId());
            query += SearchUtil.addConstraint("o", "bookingRoomState.code", "LIKE", bookingItemRoomVo.getBookingRoomStateVo().getCode());
        }

        return entityManager.createQuery(query).getResultList();
    }

    private void findRoom(BookingItemRoom bookingItemRoom) {
        Room loadedRoom = roomService.findByIdOrRoomNumber(bookingItemRoom.getRoom());

        if (loadedRoom == null) {
            return;
        }
        bookingItemRoom.setRoom(loadedRoom);
    }

    private void findBooking(BookingItemRoom bookingItemRoom) {
        Booking loadedBooking = null;
        if (bookingItemRoom.getBooking() != null && bookingItemRoom.getBooking().getId() != null)
            loadedBooking = bookingService.findById(bookingItemRoom.getBooking().getId());

        if (loadedBooking == null) {
            return;
        }
        bookingItemRoom.setBooking(loadedBooking);
    }

    private void findBookingRoomState(BookingItemRoom bookingItemRoom) {
        BookingRoomState loadedBookingRoomState = bookingRoomStateService.findByIdOrCode(bookingItemRoom.getBookingRoomState());

        if (loadedBookingRoomState == null) {
            return;
        }
        bookingItemRoom.setBookingRoomState(loadedBookingRoomState);
    }

    @Override
    @Transactional
    public void delete(List<BookingItemRoom> bookingItemRooms) {
        if (ListUtil.isNotEmpty(bookingItemRooms)) {
            bookingItemRooms.forEach(e -> bookingItemRoomDao.delete(e));
        }
    }

    @Override
    public void update(List<BookingItemRoom> bookingItemRooms) {
        if (ListUtil.isNotEmpty(bookingItemRooms)) {
            bookingItemRooms.forEach(e -> bookingItemRoomDao.save(e));
        }
    }


}
