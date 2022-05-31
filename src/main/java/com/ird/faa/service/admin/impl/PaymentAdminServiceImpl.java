package com.ird.faa.service.admin.impl;

import com.ird.faa.bean.Booking;
import com.ird.faa.bean.Payment;
import com.ird.faa.bean.TypePayment;
import com.ird.faa.dao.PaymentDao;
import com.ird.faa.service.admin.facade.BookingAdminService;
import com.ird.faa.service.admin.facade.PaymentAdminService;
import com.ird.faa.service.admin.facade.TypePaymentAdminService;
import com.ird.faa.service.core.impl.AbstractServiceImpl;
import com.ird.faa.service.util.ListUtil;
import com.ird.faa.service.util.SearchUtil;
import com.ird.faa.ws.rest.provided.vo.PaymentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentAdminServiceImpl extends AbstractServiceImpl<Payment> implements PaymentAdminService {

    @Autowired
    private PaymentDao paymentDao;

    @Autowired
    private TypePaymentAdminService typePaymentService;
    @Autowired
    private BookingAdminService bookingService;


    @Autowired
    private EntityManager entityManager;


    @Override
    public List<Payment> findAll() {
        return paymentDao.findAll();
    }

    @Override
    public List<Payment> findByTypePaymentCode(String code) {
        return paymentDao.findByTypePaymentCode(code);
    }

    @Override
    @Transactional
    public int deleteByTypePaymentCode(String code) {
        return paymentDao.deleteByTypePaymentCode(code);
    }

    @Override
    public List<Payment> findByTypePaymentId(Long id) {
        return paymentDao.findByTypePaymentId(id);
    }

    @Override
    @Transactional
    public int deleteByTypePaymentId(Long id) {
        return paymentDao.deleteByTypePaymentId(id);
    }

    @Override
    public List<Payment> findByBookingId(Long id) {
        return paymentDao.findByBookingId(id);
    }

    @Override
    @Transactional
    public int deleteByBookingId(Long id) {
        return paymentDao.deleteByBookingId(id);
    }


    @Override
    public Payment findById(Long id) {
        if (id == null) return null;
        return paymentDao.getOne(id);
    }

    @Override
    public Payment findByIdWithAssociatedList(Long id) {
        return findById(id);
    }


    @Transactional
    public int deleteById(Long id) {
        int res = 0;
        if (paymentDao.findById(id).isPresent()) {
            paymentDao.deleteById(id);
            res = 1;
        }
        return res;
    }

    @Override
    public Payment findByInvoiceNumber(Long invoiceNumber) {
        return paymentDao.findByInvoiceNumber(invoiceNumber);
    }


    @Override
    public Payment update(Payment payment) {
        Payment foundedPayment = findById(payment.getId());
        if (foundedPayment == null) return null;
        else {
            return paymentDao.save(payment);
        }
    }

    @Override
    public Payment save(Payment payment) {
//
        Payment result = null;

        Booking booking = bookingService.findById(payment.getBooking().getId());
        payment.setBooking(booking);
//
        findTypePayment(payment);
        findBooking(payment);

        BigDecimal a = new BigDecimal(booking.getTotal().compareTo(payment.getAmount().add(booking.getTotalPay())));
        BigDecimal b = new BigDecimal(-1);
        if (a.equals(b)) {
            System.out.println("Error");
        } else {


        BigDecimal nvAmount = payment.getAmount().add(booking.getTotalPay());
        booking.setTotalPay(nvAmount);
        bookingService.update(booking);

        result = paymentDao.save(payment);

        }
        return result;
    }


    @Override
    public List<Payment> save(List<Payment> payments) {
        List<Payment> list = new ArrayList<>();
        for (Payment payment : payments) {
            list.add(save(payment));
        }
        return list;
    }


    @Override
    @Transactional
    public int delete(Payment payment) {
        if (payment.getId() == null) return -1;
        Payment foundedPayment = findById(payment.getId());
        if (foundedPayment == null) return -1;
        paymentDao.delete(foundedPayment);
        return 1;
    }


    public List<Payment> findByCriteria(PaymentVo paymentVo) {

        String query = "SELECT o FROM Payment o where 1=1 ";

        query += SearchUtil.addConstraint("o", "id", "=", paymentVo.getId());
        query += SearchUtil.addConstraint("o", "invoiceNumber", "=", paymentVo.getInvoiceNumber());
        query += SearchUtil.addConstraint("o", "amount", "=", paymentVo.getAmount());
        query += SearchUtil.addConstraintDate("o", "paymentDate", "=", paymentVo.getPaymentDate());
        query += SearchUtil.addConstraintMinMax("o", "invoiceNumber", paymentVo.getInvoiceNumberMin(), paymentVo.getInvoiceNumberMax());
        query += SearchUtil.addConstraintMinMax("o", "amount", paymentVo.getAmountMin(), paymentVo.getAmountMax());
        query += SearchUtil.addConstraintMinMaxDate("o", "paymentDate", paymentVo.getPaymentDateMin(), paymentVo.getPaymentDateMax());
        if (paymentVo.getTypePaymentVo() != null) {
            query += SearchUtil.addConstraint("o", "typePayment.id", "=", paymentVo.getTypePaymentVo().getId());
            query += SearchUtil.addConstraint("o", "typePayment.code", "LIKE", paymentVo.getTypePaymentVo().getCode());
        }

        if (paymentVo.getBookingVo() != null) {
            query += SearchUtil.addConstraint("o", "booking.id", "=", paymentVo.getBookingVo().getId());
        }

        return entityManager.createQuery(query).getResultList();
    }

    private void findTypePayment(Payment payment) {
        TypePayment loadedTypePayment = typePaymentService.findByIdOrCode(payment.getTypePayment());

        if (loadedTypePayment == null) {
            return;
        }
        payment.setTypePayment(loadedTypePayment);
    }

    private void findBooking(Payment payment) {
        Booking loadedBooking = null;
        if (payment.getBooking() != null && payment.getBooking().getId() != null)
            loadedBooking = bookingService.findById(payment.getBooking().getId());

        if (loadedBooking == null) {
            return;
        }
        payment.setBooking(loadedBooking);
    }

    @Override
    @Transactional
    public void delete(List<Payment> payments) {
        if (ListUtil.isNotEmpty(payments)) {
            payments.forEach(e -> paymentDao.delete(e));
        }
    }

    @Override
    public void update(List<Payment> payments) {
        if (ListUtil.isNotEmpty(payments)) {
            payments.forEach(e -> paymentDao.save(e));
        }
    }


}
