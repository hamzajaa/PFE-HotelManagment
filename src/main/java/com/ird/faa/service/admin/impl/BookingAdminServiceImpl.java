package com.ird.faa.service.admin.impl;

import com.ird.faa.bean.*;
import com.ird.faa.dao.BookingDao;
import com.ird.faa.service.admin.facade.*;
import com.ird.faa.service.core.facade.ArchivableService;
import com.ird.faa.service.core.impl.AbstractServiceImpl;
import com.ird.faa.service.util.ListUtil;
import com.ird.faa.service.util.SearchUtil;
import com.ird.faa.ws.rest.provided.vo.BookingVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BookingAdminServiceImpl extends AbstractServiceImpl<Booking> implements BookingAdminService {

    @Autowired
    private BookingDao bookingDao;

    @Autowired
    private ArchivableService<Booking> archivableService;
    @Autowired
    private PaymentStatusAdminService paymentStatusService;
    @Autowired
    private PaymentAdminService paymentService;
    @Autowired
    private GuestAdminService guestService;
    @Autowired
    private PriceManagerAdminService priceManagerService;
    @Autowired
    private BookingStatusAdminService bookingStatusService;
    @Autowired
    private BookingItemRoomAdminService bookingItemRoomService;


    @Autowired
    private EntityManager entityManager;


    @Override
    public List<Booking> findAll() {
        String query = "SELECT o FROM Booking o where 1=1 ";
        query += " ORDER BY o.bookingDate";
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public List<Booking> findByGuestId(Long id) {
        return bookingDao.findByGuestId(id);
    }

    @Override
    @Transactional
    public int deleteByGuestId(Long id) {
        return bookingDao.deleteByGuestId(id);
    }


    @Override
    public List<Booking> findByPaymentStatusCode(String code) {
        return bookingDao.findByPaymentStatusCode(code);
    }

    @Override
    @Transactional
    public int deleteByPaymentStatusCode(String code) {
        return bookingDao.deleteByPaymentStatusCode(code);
    }

    @Override
    public List<Booking> findByPaymentStatusId(Long id) {
        return bookingDao.findByPaymentStatusId(id);
    }

    @Override
    @Transactional
    public int deleteByPaymentStatusId(Long id) {
        return bookingDao.deleteByPaymentStatusId(id);
    }


    @Override
    public List<Booking> findByBookingStatusCode(String code) {
        return bookingDao.findByBookingStatusCode(code);
    }

    @Override
    @Transactional
    public int deleteByBookingStatusCode(String code) {
        return bookingDao.deleteByBookingStatusCode(code);
    }

    @Override
    public List<Booking> findByBookingStatusId(Long id) {
        return bookingDao.findByBookingStatusId(id);
    }

    @Override
    @Transactional
    public int deleteByBookingStatusId(Long id) {
        return bookingDao.deleteByBookingStatusId(id);
    }

    @Override
    public List<Booking> findByPriceManagerId(Long id) {
        return bookingDao.findByPriceManagerId(id);
    }

    @Override
    @Transactional
    public int deleteByPriceManagerId(Long id) {
        return bookingDao.deleteByPriceManagerId(id);
    }


    @Override
    public Booking findById(Long id) {
        if (id == null) return null;
        return bookingDao.getOne(id);
    }

    @Override
    public Booking findByIdWithAssociatedList(Long id) {
        Booking booking = findById(id);
        findAssociatedLists(booking);
        return booking;
    }

    @Override
    public Booking archiver(Booking booking) {
        if (booking.getArchive() == null) {
            booking.setArchive(false);
        }
        booking.setArchive(true);
        booking.setDateArchivage(new Date());
        bookingDao.save(booking);
        return booking;

    }

    @Override
    public Booking desarchiver(Booking booking) {
        if (booking.getArchive() == null) {
            booking.setArchive(false);
        }
        booking.setArchive(false);
        bookingDao.save(booking);
        return booking;
    }


    private void findAssociatedLists(Booking booking) {
        if (booking != null && booking.getId() != null) {
            List<BookingItemRoom> bookingItemRooms = bookingItemRoomService.findByBookingId(booking.getId());
            booking.setBookingItemRooms(bookingItemRooms);
            List<Payment> payments = paymentService.findByBookingId(booking.getId());
            booking.setPayments(payments);
        }
    }

    private void deleteAssociatedLists(Long id) {
        if (id != null) {
            bookingItemRoomService.deleteByBookingId(id);
            paymentService.deleteByBookingId(id);
        }
    }

    private void updateAssociatedLists(Booking booking) {
        if (booking != null && booking.getId() != null) {
            List
                    <List<BookingItemRoom>> resultBookingItemRooms = bookingItemRoomService.getToBeSavedAndToBeDeleted(bookingItemRoomService.findByBookingId(booking.getId()), booking.getBookingItemRooms());
            bookingItemRoomService.delete(resultBookingItemRooms.get(1));
            associateBookingItemRoom(booking, resultBookingItemRooms.get(0));
            bookingItemRoomService.update(resultBookingItemRooms.get(0));

            List
                    <List<Payment>> resultPayments = paymentService.getToBeSavedAndToBeDeleted(paymentService.findByBookingId(booking.getId()), booking.getPayments());
            paymentService.delete(resultPayments.get(1));
            associatePayment(booking, resultPayments.get(0));
            paymentService.update(resultPayments.get(0));

        }
    }

    @Transactional
    public int deleteById(Long id) {
        int res = 0;
        if (bookingDao.findById(id).isPresent()) {
            deleteAssociatedLists(id);
            bookingDao.deleteById(id);
            res = 1;
        }
        return res;
    }

    @Override
    public Booking findByBookingNumber(Long bookingNumber) {
        return bookingDao.findByBookingNumber(bookingNumber);
    }


    @Override
    public Booking update(Booking booking) {
        Booking foundedBooking = findById(booking.getId());
        if (foundedBooking == null) return null;
        else {
            archivableService.prepare(booking);
            updateAssociatedLists(booking);
            return bookingDao.save(booking);
        }
    }

    private void prepareSave(Booking booking) {
        booking.setDateCreation(new Date());
        if (booking.getDateArchivage() == null)
            booking.setDateArchivage(new Date());
        if (booking.getArchive() == null)
            booking.setArchive(false);


    }

    @Override
    public Booking save(Booking booking) {
        prepareSave(booking);

        Booking result = null;

        findGuest(booking);
        findPaymentStatus(booking);
        findBookingStatus(booking);
        findPriceManager(booking);


        if (booking.getTotal() == null) {
            booking.setTotal(BigDecimal.valueOf(0));
        }
        booking.setTotal(calcTotal(booking.getBookingItemRooms()));

        booking.setTotalPay(caltTotalPay(booking.getPayments()).add(booking.getTotalPay()));

        Booking savedBooking = bookingDao.save(booking);

        saveBookingItemRooms(savedBooking, booking.getBookingItemRooms());
        savePayments(savedBooking, booking.getPayments());


        result = savedBooking;

        return result;
    }

    private BigDecimal calcTotal(List<BookingItemRoom> bookingItemRooms) {
        BigDecimal total = new BigDecimal(0);
        for (BookingItemRoom bi : bookingItemRooms) {
            if (bi.getRoom().getRoomType().getBasePrice() == null) {
                bi.getRoom().getRoomType().setBasePrice(BigDecimal.valueOf(0));
            }
            if (bi.getRoom().getRoomType().getExtraBedPrice() == null) {
                bi.getRoom().getRoomType().setExtraBedPrice(BigDecimal.valueOf(0));
            }
            if (bi.getRoom().getRoomType().getNumberOfExtraBed() == null) {
                bi.getRoom().getRoomType().setNumberOfExtraBed(0L);
            }
            total = bi.getRoom().getRoomType().getBasePrice().add(bi.getRoom().getRoomType().getExtraBedPrice().multiply(BigDecimal.valueOf(bi.getRoom().getRoomType().getNumberOfExtraBed())));
        }
        return total;

    }

    private BigDecimal caltTotalPay(List<Payment> payments) {
        BigDecimal total = new BigDecimal(0);
        for (Payment p : payments) {
            if (p.getAmount() == null) {
                p.setAmount(BigDecimal.valueOf(0));
            }
            total = total.add(p.getAmount());
        }
        return total;
    }


    @Override
    public List<Booking> save(List<Booking> bookings) {
        List<Booking> list = new ArrayList<>();
        for (Booking booking : bookings) {
            list.add(save(booking));
        }
        return list;
    }

    private List<BookingItemRoom> prepareBookingItemRooms(Booking booking, List<BookingItemRoom> bookingItemRooms) {
        for (BookingItemRoom bookingItemRoom : bookingItemRooms) {
            bookingItemRoom.setBooking(booking);
        }
        return bookingItemRooms;
    }

    private List<Payment> preparePayments(Booking booking, List<Payment> payments) {
        for (Payment payment : payments) {
            payment.setBooking(booking);
        }
        return payments;
    }


    @Override
    @Transactional
    public int delete(Booking booking) {
        if (booking.getId() == null) return -1;
        Booking foundedBooking = findById(booking.getId());
        if (foundedBooking == null) return -1;
        bookingDao.delete(foundedBooking);
        return 1;
    }


    public List<Booking> findByCriteria(BookingVo bookingVo) {

        String query = "SELECT o FROM Booking o where 1=1 ";

        query += SearchUtil.addConstraint("o", "id", "=", bookingVo.getId());
        query += SearchUtil.addConstraint("o", "bookingNumber", "=", bookingVo.getBookingNumber());
        query += SearchUtil.addConstraintDate("o", "checkIn", "=", bookingVo.getCheckIn());
        query += SearchUtil.addConstraintDate("o", "checkOut", "=", bookingVo.getCheckOut());
        query += SearchUtil.addConstraintDate("o", "bookingDate", "=", bookingVo.getBookingDate());
        query += SearchUtil.addConstraint("o", "total", "=", bookingVo.getTotal());
        query += SearchUtil.addConstraint("o", "totalPay", "=", bookingVo.getTotalPay());
        query += SearchUtil.addConstraint("o", "archive", "=", bookingVo.getArchive());
        query += SearchUtil.addConstraintDate("o", "dateArchivage", "=", bookingVo.getDateArchivage());
        query += SearchUtil.addConstraintDate("o", "dateCreation", "=", bookingVo.getDateCreation());
        query += SearchUtil.addConstraintMinMax("o", "bookingNumber", bookingVo.getBookingNumberMin(), bookingVo.getBookingNumberMax());
        query += SearchUtil.addConstraintMinMaxDate("o", "checkIn", bookingVo.getCheckInMin(), bookingVo.getCheckInMax());
        query += SearchUtil.addConstraintMinMaxDate("o", "checkOut", bookingVo.getCheckOutMin(), bookingVo.getCheckOutMax());
        query += SearchUtil.addConstraintMinMaxDate("o", "bookingDate", bookingVo.getBookingDateMin(), bookingVo.getBookingDateMax());
        query += SearchUtil.addConstraintMinMax("o", "total", bookingVo.getTotalMin(), bookingVo.getTotalMax());
        query += SearchUtil.addConstraintMinMax("o", "totalPay", bookingVo.getTotalPayMin(), bookingVo.getTotalPayMax());
        query += SearchUtil.addConstraintMinMaxDate("o", "dateArchivage", bookingVo.getDateArchivageMin(), bookingVo.getDateArchivageMax());
        query += SearchUtil.addConstraintMinMaxDate("o", "dateCreation", bookingVo.getDateCreationMin(), bookingVo.getDateCreationMax());
        if (bookingVo.getGuestVo() != null) {
            query += SearchUtil.addConstraint("o", "guest.id", "=", bookingVo.getGuestVo().getId());
        }

        if (bookingVo.getPaymentStatusVo() != null) {
            query += SearchUtil.addConstraint("o", "paymentStatus.id", "=", bookingVo.getPaymentStatusVo().getId());
            query += SearchUtil.addConstraint("o", "paymentStatus.code", "LIKE", bookingVo.getPaymentStatusVo().getCode());
        }

        if (bookingVo.getBookingStatusVo() != null) {
            query += SearchUtil.addConstraint("o", "bookingStatus.id", "=", bookingVo.getBookingStatusVo().getId());
            query += SearchUtil.addConstraint("o", "bookingStatus.code", "LIKE", bookingVo.getBookingStatusVo().getCode());
        }

        if (bookingVo.getPriceManagerVo() != null) {
            query += SearchUtil.addConstraint("o", "priceManager.id", "=", bookingVo.getPriceManagerVo().getId());
        }

        query += " ORDER BY o.bookingDate";
        return entityManager.createQuery(query).getResultList();
    }

    private void saveBookingItemRooms(Booking booking, List<BookingItemRoom> bookingItemRooms) {

        if (ListUtil.isNotEmpty(booking.getBookingItemRooms())) {
            List<BookingItemRoom> savedBookingItemRooms = new ArrayList<>();
            bookingItemRooms.forEach(element -> {
                element.setBooking(booking);
                bookingItemRoomService.save(element);
            });
            booking.setBookingItemRooms(savedBookingItemRooms);
        }
    }

    private void savePayments(Booking booking, List<Payment> payments) {

        if (ListUtil.isNotEmpty(booking.getPayments())) {
            List<Payment> savedPayments = new ArrayList<>();
            payments.forEach(element -> {
                element.setBooking(booking);
                paymentService.save(element);
            });
            booking.setPayments(savedPayments);
        }
    }

    private void findGuest(Booking booking) {
        Guest loadedGuest = null;
        if (booking.getGuest() != null && booking.getGuest().getId() != null)
            loadedGuest = guestService.findById(booking.getGuest().getId());

        if (loadedGuest == null) {
            return;
        }
        booking.setGuest(loadedGuest);
    }

    private void findPaymentStatus(Booking booking) {
        PaymentStatus loadedPaymentStatus = paymentStatusService.findByIdOrCode(booking.getPaymentStatus());

        if (loadedPaymentStatus == null) {
            return;
        }
        booking.setPaymentStatus(loadedPaymentStatus);
    }

    private void findBookingStatus(Booking booking) {
        BookingStatus loadedBookingStatus = bookingStatusService.findByIdOrCode(booking.getBookingStatus());

        if (loadedBookingStatus == null) {
            return;
        }
        booking.setBookingStatus(loadedBookingStatus);
    }

    private void findPriceManager(Booking booking) {
        PriceManager loadedPriceManager = null;
        if (booking.getPriceManager() != null && booking.getPriceManager().getId() != null)
            loadedPriceManager = priceManagerService.findById(booking.getPriceManager().getId());

        if (loadedPriceManager == null) {
            return;
        }
        booking.setPriceManager(loadedPriceManager);
    }

    @Override
    @Transactional
    public void delete(List<Booking> bookings) {
        if (ListUtil.isNotEmpty(bookings)) {
            bookings.forEach(e -> bookingDao.delete(e));
        }
    }

    @Override
    public void update(List<Booking> bookings) {
        if (ListUtil.isNotEmpty(bookings)) {
            bookings.forEach(e -> bookingDao.save(e));
        }
    }

    private void associateBookingItemRoom(Booking booking, List<BookingItemRoom> bookingItemRoom) {
        if (ListUtil.isNotEmpty(bookingItemRoom)) {
            bookingItemRoom.forEach(e -> e.setBooking(booking));
        }
    }

    private void associatePayment(Booking booking, List<Payment> payment) {
        if (ListUtil.isNotEmpty(payment)) {
            payment.forEach(e -> e.setBooking(booking));
        }
    }


    @Override
    public void updateb(Booking booking) {
        if (findById(booking.getId()) != null) {
            bookingDao.save(booking);
        }
    }

}
